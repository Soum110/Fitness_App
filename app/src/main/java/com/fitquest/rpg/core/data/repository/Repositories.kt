package com.fitquest.rpg.core.data.repository

import android.content.Context
import com.fitquest.rpg.core.data.local.dao.*
import com.fitquest.rpg.core.data.local.entity.*
import com.fitquest.rpg.core.data.remote.WgerApiService
import com.fitquest.rpg.core.domain.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val profileDao: UserProfileDao,
    private val attributeDao: AttributeDao,
    private val economyDao: EconomyDao,
    @ApplicationContext private val context: Context
) {
    fun observeProfile(): Flow<UserProfile?> =
        profileDao.observeProfile().map { it?.toDomain() }

    suspend fun getProfile(): UserProfile? = profileDao.getProfile()?.toDomain()

    suspend fun saveProfile(profile: UserProfile) {
        profileDao.upsertProfile(profile.toEntity())
        // Initialize attributes if first time
        if (attributeDao.getAttribute(AttributeType.STRENGTH.name) == null) {
            attributeDao.upsertAll(AttributeType.values().map { AttributeEntity(type = it.name) })
        }
        // Initialize economy
        if (economyDao.getEconomy() == null) {
            economyDao.upsert(EconomyEntity())
        }
    }

    fun observeAttributes(): Flow<List<Attribute>> =
        attributeDao.observeAll().map { entities -> entities.map { it.toDomain() } }

    suspend fun addXpToAttribute(type: AttributeType, xpAmount: Long) {
        val existing = attributeDao.getAttribute(type.name) ?: AttributeEntity(type = type.name)
        val newTotalXp = existing.totalXpEarned + xpAmount
        val (newLevel, newCurrentXp) = XpAlgorithm.levelFromTotalXp(newTotalXp)
        attributeDao.upsert(existing.copy(
            level = newLevel,
            currentXp = newCurrentXp,
            totalXpEarned = newTotalXp
        ))
    }

    fun observeEconomy(): Flow<Economy?> =
        economyDao.observeEconomy().map { it?.toDomain() }

    suspend fun addActionPoints(amount: Int) {
        val e = economyDao.getEconomy() ?: EconomyEntity()
        economyDao.upsert(e.copy(
            totalActionPoints = e.totalActionPoints + amount,
            availableActionPoints = e.availableActionPoints + amount
        ))
    }

    suspend fun spendActionPoints(amount: Int): Boolean {
        val e = economyDao.getEconomy() ?: return false
        if (e.availableActionPoints < amount) return false
        economyDao.upsert(e.copy(
            availableActionPoints = e.availableActionPoints - amount,
            totalSpent = e.totalSpent + amount
        ))
        return true
    }

    suspend fun updateStreak() {
        val e = economyDao.getEconomy() ?: EconomyEntity()
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0)
        }.timeInMillis
        val yesterday = today - 86_400_000L

        val newStreak = when {
            e.lastCompletionDateMs == null -> 1
            e.lastCompletionDateMs >= today -> e.currentStreak // Already counted today
            e.lastCompletionDateMs >= yesterday -> e.currentStreak + 1 // Consecutive
            else -> 1 // Streak broken
        }
        economyDao.upsert(e.copy(
            currentStreak = newStreak,
            longestStreak = maxOf(e.longestStreak, newStreak),
            lastCompletionDateMs = System.currentTimeMillis()
        ))
    }
}

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: DailyTaskDao,
    private val wgerApi: WgerApiService,
    @ApplicationContext private val context: Context
) {
    fun observeTodaysTasks(): Flow<List<DailyTask>> {
        val (start, end) = todayRange()
        return taskDao.observeTasksForDay(start, end).map { it.map { e -> e.toDomain() } }
    }

    suspend fun completeTask(taskId: Long): DailyTask? {
        val entity = taskDao.getTask(taskId) ?: return null
        val updated = entity.copy(isCompleted = true, completedAtMs = System.currentTimeMillis())
        taskDao.update(updated)
        return updated.toDomain()
    }

    suspend fun generateDailyTasks(profile: UserProfile) {
        val (start, end) = todayRange()
        val existing = taskDao.countTotalTasksForDay(start, end)
        if (existing > 0) return // Already generated today

        val weekNum = profile.trainingWeekNumber
        val tasks = mutableListOf<DailyTaskEntity>()
        val now = System.currentTimeMillis()

        // Try to fetch from Wger API, fallback to bundled JSON
        val exercises = fetchExercisesOrFallback(profile)

        // Workout tasks (3 exercises)
        val workoutExercises = exercises.take(3)
        workoutExercises.forEachIndexed { idx, exercise ->
            val volume = ProgressiveOverloadEngine.computeVolume(
                weekNum,
                baseSets = if (profile.fitnessLevel == FitnessLevel.BEGINNER) 2 else 3,
                baseReps = when (profile.primaryGoal) {
                    FitnessGoal.BUILD_MUSCLE -> 8
                    FitnessGoal.LOSE_FAT -> 15
                    else -> 12
                }
            )
            tasks.add(DailyTaskEntity(
                title = exercise.name.ifBlank { "Exercise ${idx + 1}" },
                description = exercise.description.take(200).ifBlank { "Complete all sets with proper form." },
                taskType = TaskType.WORKOUT.name,
                targetAttribute = AttributeType.STRENGTH.name,
                xpReward = XpAlgorithm.xpForTask(profile.fitnessLevel.multiplier),
                apReward = 15,
                sets = volume.sets,
                reps = volume.reps,
                dateMs = now,
                difficultyMultiplier = profile.fitnessLevel.multiplier
            ))
        }

        // Cardio task
        val cardioMins = when (profile.primaryGoal) {
            FitnessGoal.LOSE_FAT -> 30
            FitnessGoal.ATHLETIC_PERFORMANCE -> 25
            else -> 20
        }
        tasks.add(DailyTaskEntity(
            title = "Morning Run / Cardio",
            description = "Maintain a comfortable pace. Focus on breathing.",
            taskType = TaskType.CARDIO.name,
            targetAttribute = AttributeType.STAMINA.name,
            xpReward = XpAlgorithm.xpForTask(1.2f),
            apReward = 10,
            durationMinutes = cardioMins,
            dateMs = now
        ))

        // Stretch task
        tasks.add(DailyTaskEntity(
            title = "Full-Body Stretch",
            description = "Dynamic warm-up + 10 min post-workout static stretching.",
            taskType = TaskType.STRETCH.name,
            targetAttribute = AttributeType.FLEXIBILITY.name,
            xpReward = XpAlgorithm.xpForTask(0.8f),
            apReward = 5,
            durationMinutes = 10,
            dateMs = now
        ))

        // Reading / Intelligence task
        tasks.add(DailyTaskEntity(
            title = "Read or Learn",
            description = "30 minutes of reading, course, or skill development.",
            taskType = TaskType.READING.name,
            targetAttribute = AttributeType.INTELLIGENCE.name,
            xpReward = XpAlgorithm.xpForTask(1.0f),
            apReward = 8,
            durationMinutes = 30,
            dateMs = now
        ))

        // Diet task
        val dietTitle = when (profile.transformationPhase) {
            TransformationPhase.CUT -> "Follow Caloric Deficit Today"
            TransformationPhase.BULK -> "Hit Protein & Calorie Goals"
            TransformationPhase.RECOMP -> "Eat at Maintenance Calories"
        }
        tasks.add(DailyTaskEntity(
            title = dietTitle,
            description = getDietDescription(profile),
            taskType = TaskType.DIET.name,
            targetAttribute = AttributeType.ENERGY.name,
            xpReward = XpAlgorithm.xpForTask(1.0f),
            apReward = 12,
            dateMs = now
        ))

        taskDao.insertAll(tasks)
    }

    private suspend fun fetchExercisesOrFallback(profile: UserProfile): List<ExerciseData> {
        return try {
            val categoryId = when (profile.primaryGoal) {
                FitnessGoal.BUILD_MUSCLE -> 11 // Chest (cycle through categories)
                FitnessGoal.LOSE_FAT -> 10 // Abs
                else -> 12 // Back
            }
            val response = wgerApi.getExercisesByCategory(categoryId = categoryId, limit = 5)
            response.results.map { ExerciseData(it.name, it.description) }
        } catch (e: Exception) {
            loadBundledExercises(context)
        }
    }

    private fun getDietDescription(profile: UserProfile): String {
        val base = when (profile.dietaryStyle) {
            DietaryStyle.VEGAN -> "Focus on legumes, tofu, tempeh, and whole grains."
            DietaryStyle.VEGETARIAN -> "Include eggs, dairy, legumes, and plenty of vegetables."
            DietaryStyle.KETO -> "Keep carbs under 25g. Focus on fats and proteins."
            DietaryStyle.OMNIVORE -> "Lean meats, vegetables, complex carbs, and healthy fats."
        }
        val phase = when (profile.transformationPhase) {
            TransformationPhase.CUT -> " Eat 300–500 kcal below maintenance."
            TransformationPhase.BULK -> " Eat 300–500 kcal above maintenance with high protein."
            TransformationPhase.RECOMP -> " Hit at least 1.6g protein per kg bodyweight."
        }
        return base + phase
    }

    private fun todayRange(): Pair<Long, Long> {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0); cal.set(Calendar.MILLISECOND, 0)
        val start = cal.timeInMillis
        return Pair(start, start + 86_400_000L)
    }
}

data class ExerciseData(val name: String, val description: String)

fun loadBundledExercises(context: Context): List<ExerciseData> {
    return try {
        val json = context.assets.open("exercises.json").bufferedReader().readText()
        val type = object : TypeToken<List<ExerciseData>>() {}.type
        Gson().fromJson(json, type)
    } catch (e: Exception) {
        // Ultimate fallback
        listOf(
            ExerciseData("Push-Ups", "Standard push-ups targeting chest, shoulders, and triceps."),
            ExerciseData("Bodyweight Squats", "Full range of motion squats for legs and glutes."),
            ExerciseData("Pull-Ups", "Overhand grip pull-ups for back and biceps.")
        )
    }
}

@Singleton
class RewardCardRepository @Inject constructor(
    private val cardDao: RewardCardDao,
    @ApplicationContext private val context: Context
) {
    fun observeAvailableCards(): Flow<List<RewardCard>> =
        cardDao.observeAvailableCards().map { it.map { e -> e.toDomain() } }

    fun observeAllCards(): Flow<List<RewardCard>> =
        cardDao.observeAllCards().map { it.map { e -> e.toDomain() } }

    suspend fun initializePredefinedCards() {
        if (cardDao.countPredefinedCards() > 0) return
        val predefined = listOf(
            RewardCardEntity(title = "Cheat Meal", description = "Go out and enjoy a full cheat meal guilt-free!", apCost = 150, emoji = "🍕", isPredefined = true),
            RewardCardEntity(title = "Approach One Stranger", description = "Push your social comfort zone. Start a genuine conversation with someone new.", apCost = 100, emoji = "🗣️", isPredefined = true),
            RewardCardEntity(title = "2-Hour App Build Session", description = "Spend 2 uninterrupted hours working on your personal project or app idea.", apCost = 80, emoji = "💡", isPredefined = true),
            RewardCardEntity(title = "Gaming Session", description = "3 hours of your favorite game, completely guilt-free.", apCost = 120, emoji = "🎮", isPredefined = true),
            RewardCardEntity(title = "Buy Something You Wanted", description = "Purchase that item you've been eyeing. You earned it.", apCost = 300, emoji = "🛍️", isPredefined = true),
            RewardCardEntity(title = "Movie Night", description = "Pick a film and enjoy a full movie night with snacks.", apCost = 90, emoji = "🎬", isPredefined = true),
            RewardCardEntity(title = "Extra Sleep Day", description = "Sleep in 2 hours extra tomorrow. Rest is progress.", apCost = 60, emoji = "😴", isPredefined = true),
            RewardCardEntity(title = "Take a Long Walk", description = "1 hour of mindful walking in nature — phone-free.", apCost = 50, emoji = "🌿", isPredefined = true),
            RewardCardEntity(title = "New Book / Game", description = "Buy a new book, game, or creative tool for yourself.", apCost = 200, emoji = "📚", isPredefined = true),
            RewardCardEntity(title = "Day Trip", description = "Plan and go on a day trip to somewhere nearby.", apCost = 400, emoji = "🗺️", isPredefined = true),
        )
        cardDao.insertAll(predefined)
    }

    suspend fun addCustomCard(card: RewardCard): Long = cardDao.insert(card.toEntity().copy(isPredefined = false))

    suspend fun redeemCard(card: RewardCard) {
        cardDao.update(card.toEntity().copy(isRedeemed = true, redeemedAtMs = System.currentTimeMillis()))
    }

    suspend fun deleteCard(card: RewardCard) = cardDao.delete(card.toEntity())
}
