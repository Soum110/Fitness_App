package com.fitquest.rpg.core.data.repository

import android.content.Context
import com.fitquest.rpg.core.data.local.dao.*
import com.fitquest.rpg.core.data.local.entity.*
import com.fitquest.rpg.core.data.remote.SupabaseRepository
import com.fitquest.rpg.core.data.remote.WgerApiService
import com.fitquest.rpg.core.domain.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val profileDao: UserProfileDao,
    private val attributeDao: AttributeDao,
    private val economyDao: EconomyDao,
    private val supabaseRepo: SupabaseRepository,
    @ApplicationContext private val context: Context
) {
    private val syncScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var syncJob: Job? = null
    private var lastSyncedUid: String? = null

    private fun startSync(uid: String) {
        synchronized(this) {
            if (syncJob == null || lastSyncedUid != uid) {
                syncJob?.cancel()
                lastSyncedUid = uid
                syncJob = syncScope.launch {
                    // Pre-populate default attributes locally if database is empty
                    launch {
                        try {
                            val existingAttrs = attributeDao.observeAll().first()
                            if (existingAttrs.isEmpty()) {
                                val defaults = AttributeType.values().map { AttributeEntity(type = it.name) }
                                attributeDao.upsertAll(defaults)
                                try { supabaseRepo.saveAttributes(uid, defaults.map { it.toDomain() }) } catch (e: Exception) {}
                            }
                        } catch (e: Exception) {}
                    }
                    // Pre-populate default economy locally if database is empty
                    launch {
                        try {
                            if (economyDao.getEconomy() == null) {
                                val defaultEco = EconomyEntity()
                                economyDao.upsert(defaultEco)
                                try { supabaseRepo.saveEconomy(uid, Economy()) } catch (e: Exception) {}
                            }
                        } catch (e: Exception) {}
                    }
                    // Sync profile
                    launch {
                        supabaseRepo.observeProfile(uid).collectLatest { remote ->
                            if (remote != null) {
                                profileDao.upsertProfile(remote.toEntity())
                            }
                        }
                    }
                    // Sync attributes
                    launch {
                        supabaseRepo.observeAttributes(uid).collectLatest { remote ->
                            if (remote.isNotEmpty()) {
                                attributeDao.upsertAll(remote.map { it.toEntity() })
                            }
                        }
                    }
                    // Sync economy
                    launch {
                        supabaseRepo.observeEconomy(uid).collectLatest { remote ->
                            if (remote != null) {
                                economyDao.upsert(remote.toEntity())
                            }
                        }
                    }
                }
            }
        }
    }

    // ── Profile ────────────────────────────────────────────────────────────

    /** Real-time stream from local Room cache — syncs Firestore in background. */
    fun observeProfile(uid: String): Flow<UserProfile?> {
        startSync(uid)
        return profileDao.observeProfile().map { it?.toDomain() }
    }

    /** One-shot read — check Firestore first, then Room cache. */
    suspend fun getProfile(uid: String): UserProfile? =
        supabaseRepo.getProfile(uid) ?: profileDao.getProfile()?.toDomain()

    suspend fun saveProfile(uid: String, profile: UserProfile) {
        // Save to local Room cache
        profileDao.upsertProfile(profile.toEntity())
        
        // Save to Firestore in background
        syncScope.launch {
            try { supabaseRepo.saveProfile(uid, profile) } catch (e: Exception) {}
        }

        // Initialize attributes if first time
        if (attributeDao.getAttribute(AttributeType.STRENGTH.name) == null) {
            val defaults = AttributeType.values().map { AttributeEntity(type = it.name) }
            attributeDao.upsertAll(defaults)
            // Push default attributes to Supabase
            syncScope.launch {
                try { supabaseRepo.saveAttributes(uid, defaults.map { it.toDomain() }) } catch (e: Exception) {}
            }
        }
        // Initialize economy
        if (economyDao.getEconomy() == null) {
            economyDao.upsert(EconomyEntity())
            syncScope.launch {
                try { supabaseRepo.saveEconomy(uid, Economy()) } catch (e: Exception) {}
            }
        }
    }

    fun stopSync() {
        syncJob?.cancel()
        syncJob = null
        lastSyncedUid = null
    }

    // ── Attributes ─────────────────────────────────────────────────────────

    fun observeAttributes(uid: String): Flow<List<Attribute>> {
        startSync(uid)
        return attributeDao.observeAll().map { list ->
            list.map { it.toDomain() }
        }
    }

    suspend fun addXpToAttribute(uid: String, type: AttributeType, xpAmount: Long) {
        val existing = attributeDao.getAttribute(type.name) ?: AttributeEntity(type = type.name)
        val newTotalXp = (existing.totalXpEarned + xpAmount).coerceAtLeast(0)
        val (newLevel, newCurrentXp) = XpAlgorithm.levelFromTotalXp(newTotalXp)
        val updated = existing.copy(
            level = newLevel,
            currentXp = newCurrentXp,
            totalXpEarned = newTotalXp
        )
        // Update local cache
        attributeDao.upsert(updated)
        
        // Sync to Supabase
        syncScope.launch {
            try { supabaseRepo.saveAttribute(uid, updated.toDomain()) } catch (e: Exception) {}
        }
    }

    // ── Economy ────────────────────────────────────────────────────────────

    fun observeEconomy(uid: String): Flow<Economy?> {
        startSync(uid)
        return economyDao.observeEconomy().map { it?.toDomain() }
    }

    suspend fun addActionPoints(uid: String, amount: Int) {
        val e = economyDao.getEconomy()?.toDomain() ?: Economy()
        val updated = e.copy(
            totalActionPoints = (e.totalActionPoints + amount).coerceAtLeast(0),
            availableActionPoints = (e.availableActionPoints + amount).coerceAtLeast(0)
        )
        economyDao.upsert(updated.toEntity())
        syncScope.launch {
            try { supabaseRepo.saveEconomy(uid, updated) } catch (e: Exception) {}
        }
    }

    suspend fun spendActionPoints(uid: String, amount: Int): Boolean {
        val e = economyDao.getEconomy()?.toDomain() ?: Economy()
        if (e.availableActionPoints < amount) return false
        val updated = e.copy(
            availableActionPoints = e.availableActionPoints - amount,
            totalSpent = e.totalSpent + amount
        )
        economyDao.upsert(updated.toEntity())
        syncScope.launch {
            try { supabaseRepo.saveEconomy(uid, updated) } catch (e: Exception) {}
        }
        return true
    }

    suspend fun updateStreak(uid: String) {
        val e = economyDao.getEconomy()?.toDomain() ?: Economy()
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0)
        }.timeInMillis
        val yesterday = today - 86_400_000L

        val newStreak = when {
            e.lastCompletionDateMs == null -> 1
            e.lastCompletionDateMs >= today -> e.currentStreak
            e.lastCompletionDateMs >= yesterday -> e.currentStreak + 1
            else -> 1
        }
        val updated = e.copy(
            currentStreak = newStreak,
            longestStreak = maxOf(e.longestStreak, newStreak),
            lastCompletionDateMs = System.currentTimeMillis()
        )
        economyDao.upsert(updated.toEntity())
        syncScope.launch {
            try { supabaseRepo.saveEconomy(uid, updated) } catch (e: Exception) {}
        }
    }
}

// Extension to convert Economy domain model to entity
private fun Economy.toEntity() = EconomyEntity(
    totalActionPoints = totalActionPoints,
    availableActionPoints = availableActionPoints,
    totalSpent = totalSpent,
    currentStreak = currentStreak,
    longestStreak = longestStreak,
    lastCompletionDateMs = lastCompletionDateMs
)

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: DailyTaskDao,
    private val attributeDao: AttributeDao,
    private val supabaseRepo: SupabaseRepository,
    private val wgerApi: WgerApiService,
    @ApplicationContext private val context: Context
) {
    private val syncScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var tasksSyncJob: Job? = null
    private var lastSyncedUid: String? = null

    private fun getTodayRange(): Pair<Long, Long> {
        val cal = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0)
        }
        val start = cal.timeInMillis
        val end = start + 86_400_000L
        return Pair(start, end)
    }

    private fun startTasksSync(uid: String) {
        synchronized(this) {
            if (tasksSyncJob == null || lastSyncedUid != uid) {
                tasksSyncJob?.cancel()
                lastSyncedUid = uid
                tasksSyncJob = syncScope.launch {
                    try {
                        supabaseRepo.observeTodaysTasks(uid).collectLatest { remoteTasks ->
                            val (todayStart, todayEnd) = getTodayRange()
                            taskDao.replaceTasksForDay(todayStart, todayEnd, remoteTasks.map { it.toEntity() })
                        }
                    } catch (e: Exception) {
                        // ignore/handle background sync errors silently
                    }
                }
            }
        }
    }

    fun observeTodaysTasks(uid: String): Flow<List<DailyTask>> {
        startTasksSync(uid)
        val (todayStart, todayEnd) = getTodayRange()
        return taskDao.observeTasksForDay(todayStart, todayEnd).map { list -> list.map { it.toDomain() } }
    }

    suspend fun completeTask(uid: String, taskId: Long): DailyTask? {
        val localTask = taskDao.getTask(taskId) ?: return null
        val updated = localTask.copy(isCompleted = true, completedAtMs = System.currentTimeMillis())
        taskDao.update(updated)
 
        // Sync to Firestore in background
        syncScope.launch {
            try {
                val (start, end) = getTodayRange()
                val allTasks = taskDao.observeTasksForDay(start, end).first().map { it.toDomain() }
                supabaseRepo.saveTasks(uid, allTasks)
            } catch (e: Exception) {}
        }
        return updated.toDomain()
    }

    suspend fun uncompleteTask(uid: String, taskId: Long): DailyTask? {
        val localTask = taskDao.getTask(taskId) ?: return null
        val updated = localTask.copy(isCompleted = false, completedAtMs = null)
        taskDao.update(updated)

        // Sync to Supabase in background
        syncScope.launch {
            try {
                val (start, end) = getTodayRange()
                val allTasks = taskDao.observeTasksForDay(start, end).first().map { it.toDomain() }
                supabaseRepo.saveTasks(uid, allTasks)
            } catch (e: Exception) {}
        }
        return updated.toDomain()
    }

    suspend fun getTask(taskId: Long): DailyTask? {
        return taskDao.getTask(taskId)?.toDomain()
    }

    suspend fun getTodaysTasksDirect(uid: String): List<DailyTask> {
        val (todayStart, todayEnd) = getTodayRange()
        return taskDao.observeTasksForDay(todayStart, todayEnd).first().map { it.toDomain() }
    }

    suspend fun generateDailyTasks(uid: String, profile: UserProfile) {
        val (todayStart, todayEnd) = getTodayRange()
        if (taskDao.countTotalTasksForDay(todayStart, todayEnd) > 0) return

        // Retrieve current attribute levels from Room (defaults to level 1 if empty)
        val attributeEntities = try { attributeDao.observeAll().first() } catch(e: Exception) { emptyList() }
        val attributes = attributeEntities.associate { it.type to it.level }
        val strengthLevel = attributes[AttributeType.STRENGTH.name] ?: 1
        val staminaLevel = attributes[AttributeType.STAMINA.name] ?: 1
        val flexibilityLevel = attributes[AttributeType.FLEXIBILITY.name] ?: 1
        val intelligenceLevel = attributes[AttributeType.INTELLIGENCE.name] ?: 1
        val energyLevel = attributes[AttributeType.ENERGY.name] ?: 1
        val exercises = fetchExercisesOrFallback(profile)

        // Calculate difficulty multiplier based on transformation target timeline
        val timelineMultiplier = when (profile.transformationMonths) {
            3 -> 1.3f
            6 -> 1.15f
            9 -> 1.0f
            12 -> 0.9f
            18 -> 0.8f
            24 -> 0.7f
            else -> 1.0f
        }

        val locationRepScale = if (profile.workoutLocation == WorkoutLocation.GYM) 0.75f else 1.0f
        val locationRewardMultiplier = if (profile.workoutLocation == WorkoutLocation.GYM) 1.2f else 1.0f

        // Workout tasks (3 exercises) - scaled by STRENGTH level and timeline target
        val levelSetBonus = strengthLevel / 15
        val levelRepBonus = strengthLevel / 5
        val baseSets = (if (profile.fitnessLevel == FitnessLevel.BEGINNER) 2 else 3) + levelSetBonus
        val baseReps = (when (profile.primaryGoal) {
            FitnessGoal.BUILD_MUSCLE -> 8
            FitnessGoal.LOSE_FAT -> 15
            else -> 12
        }) + levelRepBonus

        val setsToUse = Math.round(baseSets * timelineMultiplier).toInt().coerceAtLeast(2)
        val repsToUse = Math.round(baseReps * timelineMultiplier * locationRepScale).toInt().coerceAtLeast(5)

        val weekNum = profile.trainingWeekNumber
        val tasks = mutableListOf<DailyTask>()
        val now = System.currentTimeMillis()
        var idCounter = now // Unique timestamp-based IDs for offline safety

        exercises.take(3).forEachIndexed { idx, exercise ->
            val volume = ProgressiveOverloadEngine.computeVolume(
                weekNum,
                baseSets = setsToUse,
                baseReps = repsToUse
            )
            // Scale caps higher as strength level grows (sets cap at 12, reps cap at 60 for hard mode)
            val maxSetsLimit = Math.round((6 + strengthLevel / 15) * timelineMultiplier).toInt().coerceIn(6, 12)
            val maxRepsLimit = Math.round((25 + strengthLevel / 2) * timelineMultiplier * locationRepScale).toInt().coerceIn(25, 60)
            val finalSets = volume.sets.coerceIn(2, maxSetsLimit)
            val finalReps = volume.reps.coerceIn(5, maxRepsLimit)

            tasks.add(DailyTask(
                id = idCounter + idx,
                title = exercise.name.ifBlank { "Exercise ${idx + 1}" },
                description = exercise.description.ifBlank { "Complete all sets with proper form." }.take(200),
                taskType = TaskType.WORKOUT,
                targetAttribute = AttributeType.STRENGTH,
                xpReward = ( (XpAlgorithm.xpForTask(profile.fitnessLevel.multiplier) + strengthLevel / 3L) * timelineMultiplier * locationRewardMultiplier ).toLong(),
                apReward = ( (15 + strengthLevel / 10) * timelineMultiplier * locationRewardMultiplier ).toInt(),
                sets = finalSets,
                reps = finalReps,
                dateMs = now,
                difficultyMultiplier = profile.fitnessLevel.multiplier * timelineMultiplier,
                weight = if (profile.workoutLocation == WorkoutLocation.GYM) {
                    calculateGymWeight(exercise.name, strengthLevel)
                } else null
            ))
        }
        idCounter += 3

        // Cardio task - scaled by STAMINA level and timeline target
        val baseCardioDuration = when (profile.primaryGoal) {
            FitnessGoal.LOSE_FAT -> 30
            FitnessGoal.ATHLETIC_PERFORMANCE -> 25
            else -> 20
        }
        val maxCardioLimit = Math.round((60 + staminaLevel) * timelineMultiplier).toInt().coerceIn(60, 150)
        val levelCardioDuration = Math.round((baseCardioDuration + staminaLevel / 2) * timelineMultiplier).toInt().coerceIn(15, maxCardioLimit)
        tasks.add(DailyTask(
            id = idCounter++,
            title = "Morning Run / Cardio",
            description = "Maintain a comfortable pace. Focus on breathing.",
            taskType = TaskType.CARDIO,
            targetAttribute = AttributeType.STAMINA,
            xpReward = ( (XpAlgorithm.xpForTask(1.2f) + staminaLevel / 3L) * timelineMultiplier ).toLong(),
            apReward = ( (10 + staminaLevel / 10) * timelineMultiplier ).toInt(),
            durationMinutes = levelCardioDuration,
            dateMs = now
        ))

        // Stretch task - scaled by FLEXIBILITY level and timeline target
        val maxStretchLimit = Math.round((30 + flexibilityLevel) * timelineMultiplier).toInt().coerceIn(30, 80)
        val levelStretchDuration = Math.round((10 + flexibilityLevel / 3) * timelineMultiplier).toInt().coerceIn(5, maxStretchLimit)
        tasks.add(DailyTask(
            id = idCounter++,
            title = "Full-Body Stretch",
            description = "Dynamic warm-up + 10 min post-workout static stretching.",
            taskType = TaskType.STRETCH,
            targetAttribute = AttributeType.FLEXIBILITY,
            xpReward = ( (XpAlgorithm.xpForTask(0.8f) + flexibilityLevel / 3L) * timelineMultiplier ).toLong(),
            apReward = ( (5 + flexibilityLevel / 10) * timelineMultiplier ).toInt(),
            durationMinutes = levelStretchDuration,
            dateMs = now
        ))

        // Reading task - scaled by INTELLIGENCE level and timeline target
        val maxReadingLimit = Math.round((90 + intelligenceLevel) * timelineMultiplier).toInt().coerceIn(90, 240)
        val levelReadingDuration = Math.round((30 + intelligenceLevel / 2) * timelineMultiplier).toInt().coerceIn(15, maxReadingLimit)
        tasks.add(DailyTask(
            id = idCounter++,
            title = "Read or Learn",
            description = "30 minutes of reading, course, or skill development.",
            taskType = TaskType.READING,
            targetAttribute = AttributeType.INTELLIGENCE,
            xpReward = ( (XpAlgorithm.xpForTask(1.0f) + intelligenceLevel / 3L) * timelineMultiplier ).toLong(),
            apReward = ( (8 + intelligenceLevel / 10) * timelineMultiplier ).toInt(),
            durationMinutes = levelReadingDuration,
            dateMs = now
        ))

        // Diet task - scaled by ENERGY level and timeline target
        val dietTitle = when (profile.transformationPhase) {
            TransformationPhase.CUT -> "Follow Caloric Deficit Today"
            TransformationPhase.BULK -> "Hit Protein & Calorie Goals"
            TransformationPhase.RECOMP -> "Eat at Maintenance Calories"
        }
        tasks.add(DailyTask(
            id = idCounter,
            title = dietTitle,
            description = getDietDescription(profile),
            taskType = TaskType.DIET,
            targetAttribute = AttributeType.ENERGY,
            xpReward = ( (XpAlgorithm.xpForTask(1.0f) + energyLevel / 3L) * timelineMultiplier ).toLong(),
            apReward = ( (12 + energyLevel / 10) * timelineMultiplier ).toInt(),
            dateMs = now
        ))

        // Insert locally
        taskDao.insertAll(tasks.map { it.toEntity() })

        // Save to Firestore in background
        syncScope.launch {
            try { supabaseRepo.saveTasks(uid, tasks) } catch (e: Exception) {}
        }
    }

    suspend fun regenerateWorkoutTasksForToday(uid: String, profile: UserProfile) {
        val (todayStart, todayEnd) = getTodayRange()
        
        // 1. Fetch current tasks for today to check completed state and size
        val currentTasks = taskDao.observeTasksForDay(todayStart, todayEnd).first().map { it.toDomain() }
        val uncompletedWorkouts = currentTasks.filter { it.taskType == TaskType.WORKOUT && !it.isCompleted }
        
        if (uncompletedWorkouts.isEmpty()) return // No uncompleted workouts to swap out
        
        // 2. Delete today's uncompleted workout tasks
        taskDao.deleteUncompletedTasksForDayByType(todayStart, todayEnd, TaskType.WORKOUT.name)
        
        // 3. Fetch exercise list matching the new profile configuration
        val exercises = fetchExercisesOrFallback(profile)
        val completedNames = currentTasks.filter { it.taskType == TaskType.WORKOUT && it.isCompleted }.map { it.title }.toSet()
        val availableExercises = exercises.filter { it.name !in completedNames }
        
        // 4. Calculate difficulty parameters
        val attributeEntities = try { attributeDao.observeAll().first() } catch(e: Exception) { emptyList() }
        val attributes = attributeEntities.associate { it.type to it.level }
        val strengthLevel = attributes[AttributeType.STRENGTH.name] ?: 1
        
        val timelineMultiplier = when (profile.transformationMonths) {
            3 -> 1.3f
            6 -> 1.15f
            9 -> 1.0f
            12 -> 0.9f
            18 -> 0.8f
            24 -> 0.7f
            else -> 1.0f
        }
        val locationRepScale = if (profile.workoutLocation == WorkoutLocation.GYM) 0.75f else 1.0f
        val locationRewardMultiplier = if (profile.workoutLocation == WorkoutLocation.GYM) 1.2f else 1.0f
        
        val levelSetBonus = strengthLevel / 15
        val levelRepBonus = strengthLevel / 5
        val baseSets = (if (profile.fitnessLevel == FitnessLevel.BEGINNER) 2 else 3) + levelSetBonus
        val baseReps = (when (profile.primaryGoal) {
            FitnessGoal.BUILD_MUSCLE -> 8
            FitnessGoal.LOSE_FAT -> 15
            else -> 12
        }) + levelRepBonus

        val setsToUse = Math.round(baseSets * timelineMultiplier).toInt().coerceAtLeast(2)
        val repsToUse = Math.round(baseReps * timelineMultiplier * locationRepScale).toInt().coerceAtLeast(5)
        
        val now = System.currentTimeMillis()
        var idCounter = now + 10 // Unique timestamp offset to prevent duplicate primary keys
        
        val newWorkoutTasks = mutableListOf<DailyTask>()
        availableExercises.take(uncompletedWorkouts.size).forEachIndexed { idx, exercise ->
            val volume = ProgressiveOverloadEngine.computeVolume(
                profile.trainingWeekNumber,
                baseSets = setsToUse,
                baseReps = repsToUse
            )
            val maxSetsLimit = Math.round((6 + strengthLevel / 15) * timelineMultiplier).toInt().coerceIn(6, 12)
            val maxRepsLimit = Math.round((25 + strengthLevel / 2) * timelineMultiplier * locationRepScale).toInt().coerceIn(25, 60)
            val finalSets = volume.sets.coerceIn(2, maxSetsLimit)
            val finalReps = volume.reps.coerceIn(5, maxRepsLimit)

            newWorkoutTasks.add(DailyTask(
                id = idCounter + idx,
                title = exercise.name.ifBlank { "Exercise ${idx + 1}" },
                description = exercise.description.ifBlank { "Complete all sets with proper form." }.take(200),
                taskType = TaskType.WORKOUT,
                targetAttribute = AttributeType.STRENGTH,
                xpReward = ( (XpAlgorithm.xpForTask(profile.fitnessLevel.multiplier) + strengthLevel / 3L) * timelineMultiplier * locationRewardMultiplier ).toLong(),
                apReward = ( (15 + strengthLevel / 10) * timelineMultiplier * locationRewardMultiplier ).toInt(),
                sets = finalSets,
                reps = finalReps,
                dateMs = now,
                difficultyMultiplier = profile.fitnessLevel.multiplier * timelineMultiplier,
                weight = if (profile.workoutLocation == WorkoutLocation.GYM) {
                    calculateGymWeight(exercise.name, strengthLevel)
                } else null
            ))
        }
        
        // Insert new ones locally
        taskDao.insertAll(newWorkoutTasks.map { it.toEntity() })
        
        // Sync entire daily set to Supabase
        val updatedTasks = taskDao.observeTasksForDay(todayStart, todayEnd).first().map { it.toDomain() }
        syncScope.launch {
            try { supabaseRepo.saveTasks(uid, updatedTasks) } catch (e: Exception) {}
        }
    }

    private fun calculateGymWeight(name: String, strengthLevel: Int): String {
        return when {
            name.contains("bench press", ignoreCase = true) -> "${20 + (strengthLevel - 1) * 2} kg"
            name.contains("barbell squat", ignoreCase = true) -> "${20 + (strengthLevel - 1) * 3} kg"
            name.contains("deadlift", ignoreCase = true) -> "${30 + (strengthLevel - 1) * 4} kg"
            name.contains("overhead press", ignoreCase = true) -> "${15 + (strengthLevel - 1) * 1.5} kg"
            name.contains("dumbbell bicep curl", ignoreCase = true) -> "${6 + (strengthLevel / 10) * 2} kg per DB"
            name.contains("dumbbell row", ignoreCase = true) -> "${10 + (strengthLevel - 1) * 1} kg per DB"
            name.contains("tricep pushdown", ignoreCase = true) -> "${15 + (strengthLevel - 1) * 1.5} kg"
            name.contains("lat pulldown", ignoreCase = true) -> "${25 + (strengthLevel - 1) * 2} kg"
            name.contains("pull-up", ignoreCase = true) || name.contains("dips", ignoreCase = true) || name.contains("inverted row", ignoreCase = true) -> "Bodyweight"
            else -> "Moderate weight"
        }
    }

    private suspend fun fetchExercisesOrFallback(profile: UserProfile): List<ExerciseData> {
        val rawList = try {
            val categoryId = when (profile.primaryGoal) {
                FitnessGoal.BUILD_MUSCLE -> 11
                FitnessGoal.LOSE_FAT -> 10
                else -> 12
            }
            val response = wgerApi.getExercisesByCategory(categoryId = categoryId, limit = 10)
            response.results.map { ExerciseData(it.name, it.description, false) }
        } catch (e: Exception) {
            loadBundledExercises(context)
        }
        
        return if (profile.workoutLocation == WorkoutLocation.HOME) {
            rawList.filter { 
                !it.requiresGym && 
                !it.name.contains("dumbbell", ignoreCase = true) && 
                !it.name.contains("barbell", ignoreCase = true) && 
                !it.name.contains("machine", ignoreCase = true) && 
                !it.name.contains("cable", ignoreCase = true) 
            }.shuffled()
        } else {
            val gymSpecific = rawList.filter { 
                it.requiresGym || 
                it.name.contains("dumbbell", ignoreCase = true) || 
                it.name.contains("barbell", ignoreCase = true) || 
                it.name.contains("machine", ignoreCase = true) || 
                it.name.contains("cable", ignoreCase = true) 
            }.shuffled()
            val other = rawList.filter { it !in gymSpecific }.shuffled()
            gymSpecific + other
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

    fun stopSync() {
        tasksSyncJob?.cancel()
        tasksSyncJob = null
        lastSyncedUid = null
    }
}

data class ExerciseData(
    val name: String,
    val description: String,
    val requiresGym: Boolean = false
)

fun loadBundledExercises(context: Context): List<ExerciseData> {
    return try {
        val json = context.assets.open("exercises.json").bufferedReader().readText()
        val type = object : TypeToken<List<ExerciseData>>() {}.type
        Gson().fromJson(json, type)
    } catch (e: Exception) {
        listOf(
            ExerciseData("Push-Ups", "Standard push-ups targeting chest, shoulders, and triceps.", false),
            ExerciseData("Bodyweight Squats", "Full range of motion squats for legs and glutes.", false),
            ExerciseData("Pull-Ups", "Overhand grip pull-ups for back and biceps.", true)
        )
    }
}

@Singleton
class RewardCardRepository @Inject constructor(
    private val cardDao: RewardCardDao,
    private val supabaseRepo: SupabaseRepository,
    @ApplicationContext private val context: Context
) {
    private val syncScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var cardsSyncJob: Job? = null
    private var lastSyncedUid: String? = null

    private fun startCardsSync(uid: String) {
        synchronized(this) {
            if (cardsSyncJob == null || lastSyncedUid != uid) {
                cardsSyncJob?.cancel()
                lastSyncedUid = uid
                cardsSyncJob = syncScope.launch {
                    try {
                        supabaseRepo.observeRewardCards(uid).collectLatest { remoteCards ->
                            cardDao.insertAll(remoteCards.map { it.toEntity() })
                        }
                    } catch (e: Exception) {
                        // ignore/handle background sync errors
                    }
                }
            }
        }
    }

    fun observeAvailableCards(uid: String): Flow<List<RewardCard>> {
        startCardsSync(uid)
        return cardDao.observeAllCards().map { list -> list.map { it.toDomain() } }
    }

    suspend fun initializePredefinedCards(uid: String) {
        if (cardDao.countPredefinedCards() > 0) return

        val predefined = listOf(
            RewardCard(id = 1L, title = "Cheat Meal", description = "Go out and enjoy a full cheat meal guilt-free! Active quest: if you follow any routine anyway, get extra bonus!", apCost = 150, emoji = "🍕",
                hasTask = true, taskType = "CHEAT_DAY_ROUTINE", taskTarget = 1, bonusXp = 200L, bonusAp = 50, targetAttribute = AttributeType.ENERGY),
            RewardCard(id = 2L, title = "Approach One Stranger", description = "Push your social comfort zone. Start a genuine conversation. Overachieve to get extra rewards!", apCost = 100, emoji = "🗣️",
                hasTask = true, taskType = "COUNTER", taskTarget = 1, bonusXp = 100L, overachieveXpPerCount = 50L, overachieveApPerCount = 10, targetAttribute = AttributeType.INTELLIGENCE),
            RewardCard(id = 3L, title = "2-Hour App Build Session", description = "Spend 2 uninterrupted hours on your personal project.", apCost = 80, emoji = "💡"),
            RewardCard(id = 4L, title = "Gaming Session", description = "3 hours of your favorite game, completely guilt-free.", apCost = 120, emoji = "🎮"),
            RewardCard(id = 5L, title = "Buy Something You Wanted", description = "Purchase that item you've been eyeing. You earned it.", apCost = 300, emoji = "🛍️"),
            RewardCard(id = 6L, title = "Movie Night", description = "Pick a film and enjoy a full movie night with snacks.", apCost = 90, emoji = "🎬"),
            RewardCard(id = 7L, title = "Extra Sleep Day", description = "Sleep in 2 hours extra tomorrow. Rest is progress.", apCost = 60, emoji = "😴"),
            RewardCard(id = 8L, title = "Take a Long Walk", description = "1 hour of mindful walking in nature — phone-free.", apCost = 50, emoji = "🌿"),
            RewardCard(id = 9L, title = "New Book / Game", description = "Buy a new book, game, or creative tool for yourself.", apCost = 200, emoji = "📚"),
            RewardCard(id = 10L, title = "Day Trip", description = "Plan and go on a day trip to somewhere nearby.", apCost = 400, emoji = "🗺️"),
        )
        cardDao.insertAll(predefined.map { it.toEntity() })
        syncScope.launch {
            try { supabaseRepo.saveRewardCards(uid, predefined) } catch (e: Exception) {}
        }
    }

    suspend fun addCustomCard(uid: String, card: RewardCard): RewardCard {
        val withId = card.copy(id = System.currentTimeMillis())
        cardDao.insert(withId.toEntity())
        syncScope.launch {
            try { supabaseRepo.saveRewardCards(uid, listOf(withId)) } catch (e: Exception) {}
        }
        return withId
    }

    suspend fun redeemCard(uid: String, card: RewardCard) {
        val newTimesRedeemed = card.timesRedeemed + 1
        val now = System.currentTimeMillis()
        val updatedTemplate = card.copy(
            lastRedeemedAtMs = now,
            timesRedeemed = newTimesRedeemed
        )
        val redeemedInstance = card.copy(
            id = now, // unique ID for the redeemed copy
            isRedeemed = true,
            redeemedAtMs = now,
            timesRedeemed = newTimesRedeemed
        )

        cardDao.update(updatedTemplate.toEntity())
        cardDao.insert(redeemedInstance.toEntity())

        syncScope.launch {
            try {
                supabaseRepo.saveRewardCards(uid, listOf(updatedTemplate, redeemedInstance))
            } catch (e: Exception) {}
        }
    }

    suspend fun deleteCard(uid: String, card: RewardCard) {
        if (card.isPredefined) return
        cardDao.delete(card.toEntity())
        syncScope.launch {
            try { supabaseRepo.deleteCard(uid, card) } catch (e: Exception) { /* best effort */ }
        }
    }

    suspend fun incrementQuestProgress(uid: String, card: RewardCard, userRepo: UserRepository) {
        val currentProgress = card.taskProgress
        val newProgress = currentProgress + 1
        val isCompletedNow = newProgress >= card.taskTarget && !card.taskCompleted

        var xpToAward = 0L
        var apToAward = 0

        if (isCompletedNow) {
            xpToAward += card.bonusXp
            apToAward += card.bonusAp
        } else if (newProgress > card.taskTarget && card.hasTask && card.taskType == "COUNTER") {
            xpToAward += card.overachieveXpPerCount
            apToAward += card.overachieveApPerCount
        }

        val updated = card.copy(
            taskProgress = newProgress,
            taskCompleted = card.taskCompleted || (newProgress >= card.taskTarget)
        )

        cardDao.update(updated.toEntity())
        syncScope.launch {
            try { supabaseRepo.saveRewardCards(uid, listOf(updated)) } catch (e: Exception) {}
        }

        if (xpToAward > 0) {
            userRepo.addXpToAttribute(uid, card.targetAttribute, xpToAward)
        }
        if (apToAward > 0) {
            userRepo.addActionPoints(uid, apToAward)
        }
    }

    suspend fun claimCheatDayBonus(uid: String, card: RewardCard, userRepo: UserRepository) {
        if (card.taskCompleted) return

        val updated = card.copy(
            taskProgress = 1,
            taskCompleted = true
        )

        cardDao.update(updated.toEntity())
        syncScope.launch {
            try { supabaseRepo.saveRewardCards(uid, listOf(updated)) } catch (e: Exception) {}
        }

        userRepo.addXpToAttribute(uid, card.targetAttribute, card.bonusXp)
        userRepo.addActionPoints(uid, card.bonusAp)
    }

    fun stopSync() {
        cardsSyncJob?.cancel()
        cardsSyncJob = null
        lastSyncedUid = null
    }
}
