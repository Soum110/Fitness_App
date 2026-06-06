package com.fitquest.rpg.core.data.remote

import com.fitquest.rpg.core.domain.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SupabaseRepository @Inject constructor(
    private val apiService: SupabaseApiService,
    private val auth: SupabaseAuth
) {
    private val gson = Gson()

    private fun getAuthHeader() = auth.getAuthHeader()
    private fun todayKey() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    // ── Profile ─────────────────────────────────────────────────────────────
    suspend fun saveProfile(uid: String, profile: UserProfile) {
        withContext(Dispatchers.IO) {
            val dto = SupabaseProfile(
                uid = uid,
                name = profile.name,
                age = profile.age,
                gender = profile.gender.name,
                heightCm = profile.heightCm,
                weightKg = profile.weightKg,
                fitnessLevel = profile.fitnessLevel.name,
                primaryGoal = profile.primaryGoal.name,
                dietaryStyle = profile.dietaryStyle.name,
                workoutDaysPerWeek = profile.workoutDaysPerWeek,
                wakeTimeHour = profile.wakeTimeHour,
                sleepTimeHour = profile.sleepTimeHour,
                onboardingComplete = profile.onboardingComplete,
                joinDateMs = profile.joinDateMs
            )
            apiService.upsertProfile(getAuthHeader(), profile = dto)
        }
    }

    fun observeProfile(uid: String): Flow<UserProfile?> = flow {
        emit(getProfile(uid))
    }

    suspend fun getProfile(uid: String): UserProfile? {
        return withContext(Dispatchers.IO) {
            try {
                val list = apiService.getProfile(getAuthHeader(), "uid.eq.$uid")
                if (list.isEmpty()) return@withContext null
                val snap = list.first()
                UserProfile(
                    id = 1L,
                    name = snap.name,
                    age = snap.age,
                    gender = Gender.valueOf(snap.gender),
                    heightCm = snap.heightCm,
                    weightKg = snap.weightKg,
                    fitnessLevel = FitnessLevel.valueOf(snap.fitnessLevel),
                    primaryGoal = FitnessGoal.valueOf(snap.primaryGoal),
                    dietaryStyle = DietaryStyle.valueOf(snap.dietaryStyle),
                    workoutDaysPerWeek = snap.workoutDaysPerWeek,
                    wakeTimeHour = snap.wakeTimeHour,
                    sleepTimeHour = snap.sleepTimeHour,
                    onboardingComplete = snap.onboardingComplete,
                    joinDateMs = snap.joinDateMs
                )
            } catch (e: Exception) {
                null
            }
        }
    }

    // ── Economy ─────────────────────────────────────────────────────────────
    suspend fun saveEconomy(uid: String, economy: Economy) {
        withContext(Dispatchers.IO) {
            val dto = SupabaseEconomy(
                uid = uid,
                totalActionPoints = economy.totalActionPoints,
                availableActionPoints = economy.availableActionPoints,
                totalSpent = economy.totalSpent,
                currentStreak = economy.currentStreak,
                longestStreak = economy.longestStreak,
                lastCompletionDateMs = economy.lastCompletionDateMs
            )
            apiService.upsertEconomy(getAuthHeader(), economy = dto)
        }
    }

    fun observeEconomy(uid: String): Flow<Economy?> = flow {
        emit(getEconomy(uid))
    }

    suspend fun getEconomy(uid: String): Economy {
        return withContext(Dispatchers.IO) {
            try {
                val list = apiService.getEconomy(getAuthHeader(), "uid.eq.$uid")
                if (list.isEmpty()) return@withContext Economy()
                val snap = list.first()
                Economy(
                    totalActionPoints = snap.totalActionPoints,
                    availableActionPoints = snap.availableActionPoints,
                    totalSpent = snap.totalSpent,
                    currentStreak = snap.currentStreak,
                    longestStreak = snap.longestStreak,
                    lastCompletionDateMs = snap.lastCompletionDateMs
                )
            } catch (e: Exception) {
                Economy()
            }
        }
    }

    // ── Attributes ──────────────────────────────────────────────────────────
    suspend fun saveAttribute(uid: String, attribute: Attribute) {
        withContext(Dispatchers.IO) {
            val dto = SupabaseAttribute(
                uid = uid,
                type = attribute.type.name,
                level = attribute.level,
                currentXp = attribute.currentXp,
                totalXpEarned = attribute.totalXpEarned
            )
            apiService.upsertAttribute(getAuthHeader(), attribute = dto)
        }
    }

    suspend fun saveAttributes(uid: String, attributes: List<Attribute>) {
        withContext(Dispatchers.IO) {
            val dtos = attributes.map { attribute ->
                SupabaseAttribute(
                    uid = uid,
                    type = attribute.type.name,
                    level = attribute.level,
                    currentXp = attribute.currentXp,
                    totalXpEarned = attribute.totalXpEarned
                )
            }
            apiService.upsertAttributes(getAuthHeader(), attributes = dtos)
        }
    }

    fun observeAttributes(uid: String): Flow<List<Attribute>> = flow {
        emit(getAttributes(uid))
    }

    suspend fun getAttributes(uid: String): List<Attribute> {
        return withContext(Dispatchers.IO) {
            try {
                val list = apiService.getAttributes(getAuthHeader(), "uid.eq.$uid")
                list.map { snap ->
                    Attribute(
                        type = AttributeType.valueOf(snap.type),
                        level = snap.level,
                        currentXp = snap.currentXp,
                        totalXpEarned = snap.totalXpEarned
                    )
                }
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    // ── Tasks ────────────────────────────────────────────────────────────────
    suspend fun saveTasks(uid: String, tasks: List<DailyTask>) {
        withContext(Dispatchers.IO) {
            val taskMaps = tasks.map { t ->
                mapOf(
                    "id" to t.id,
                    "title" to t.title,
                    "description" to t.description,
                    "taskType" to t.taskType.name,
                    "targetAttribute" to t.targetAttribute.name,
                    "xpReward" to t.xpReward,
                    "apReward" to t.apReward,
                    "sets" to t.sets,
                    "reps" to t.reps,
                    "durationMinutes" to t.durationMinutes,
                    "isCompleted" to t.isCompleted,
                    "completedAtMs" to t.completedAtMs,
                    "dateMs" to t.dateMs,
                    "difficultyMultiplier" to t.difficultyMultiplier
                )
            }
            val json = gson.toJson(taskMaps)
            apiService.upsertTasks(
                getAuthHeader(),
                taskRow = SupabaseTaskRow(uid = uid, dateKey = todayKey(), tasksJson = json)
            )
        }
    }

    fun observeTodaysTasks(uid: String): Flow<List<DailyTask>> = flow {
        emit(getTodaysTasks(uid))
    }

    suspend fun getTodaysTasks(uid: String): List<DailyTask> {
        return withContext(Dispatchers.IO) {
            try {
                val list = apiService.getTasks(getAuthHeader(), "uid.eq.$uid", "date_key.eq.${todayKey()}")
                if (list.isEmpty()) return@withContext emptyList()
                val json = list.first().tasksJson
                val type = object : TypeToken<List<Map<String, Any>>>() {}.type
                val rawTasks = gson.fromJson<List<Map<String, Any>>>(json, type) ?: emptyList()
                rawTasks.mapNotNull { map ->
                    try {
                        DailyTask(
                            id = (map["id"] as? Double)?.toLong() ?: (map["id"] as? Long) ?: 0L,
                            title = map["title"] as? String ?: "",
                            description = map["description"] as? String ?: "",
                            taskType = TaskType.valueOf(map["taskType"] as? String ?: TaskType.WORKOUT.name),
                            targetAttribute = AttributeType.valueOf(map["targetAttribute"] as? String ?: AttributeType.STRENGTH.name),
                            xpReward = (map["xpReward"] as? Double)?.toLong() ?: (map["xpReward"] as? Long) ?: 0L,
                            apReward = ((map["apReward"] as? Double)?.toInt() ?: (map["apReward"] as? Long)?.toInt() ?: 0),
                            sets = (map["sets"] as? Double)?.toInt() ?: (map["sets"] as? Long)?.toInt(),
                            reps = (map["reps"] as? Double)?.toInt() ?: (map["reps"] as? Long)?.toInt(),
                            durationMinutes = (map["durationMinutes"] as? Double)?.toInt() ?: (map["durationMinutes"] as? Long)?.toInt(),
                            isCompleted = map["isCompleted"] as? Boolean ?: false,
                            completedAtMs = (map["completedAtMs"] as? Double)?.toLong() ?: (map["completedAtMs"] as? Long),
                            dateMs = (map["dateMs"] as? Double)?.toLong() ?: (map["dateMs"] as? Long) ?: System.currentTimeMillis(),
                            difficultyMultiplier = ((map["difficultyMultiplier"] as? Double) ?: 1.0).toFloat()
                        )
                    } catch (e: Exception) {
                        null
                    }
                }
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    // ── Reward Cards ─────────────────────────────────────────────────────────
    suspend fun saveRewardCards(uid: String, cards: List<RewardCard>) {
        withContext(Dispatchers.IO) {
            val rows = cards.map { card ->
                val cardJson = gson.toJson(card)
                SupabaseRewardCardRow(uid = uid, cardId = card.id.toString(), cardJson = cardJson)
            }
            apiService.upsertRewardCards(getAuthHeader(), cards = rows)
        }
    }

    fun observeRewardCards(uid: String): Flow<List<RewardCard>> = flow {
        emit(getRewardCards(uid))
    }

    suspend fun getRewardCards(uid: String): List<RewardCard> {
        return withContext(Dispatchers.IO) {
            try {
                val list = apiService.getRewardCards(getAuthHeader(), "uid.eq.$uid")
                list.mapNotNull { row ->
                    try {
                        gson.fromJson(row.cardJson, RewardCard::class.java)
                    } catch (e: Exception) {
                        null
                    }
                }
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    suspend fun deleteCard(uid: String, card: RewardCard) {
        withContext(Dispatchers.IO) {
            try {
                apiService.deleteRewardCard(getAuthHeader(), "uid.eq.$uid", "card_id.eq.${card.id}")
            } catch (e: Exception) {
                // ignore
            }
        }
    }

    suspend fun deleteUserData(uid: String) {
        withContext(Dispatchers.IO) {
            try { apiService.deleteProfile(getAuthHeader(), "uid.eq.$uid") } catch (e: Exception) {}
            try { apiService.deleteEconomy(getAuthHeader(), "uid.eq.$uid") } catch (e: Exception) {}
            try { apiService.deleteAttributes(getAuthHeader(), "uid.eq.$uid") } catch (e: Exception) {}
            try { apiService.deleteTasks(getAuthHeader(), "uid.eq.$uid") } catch (e: Exception) {}
            try { apiService.deleteRewardCards(getAuthHeader(), "uid.eq.$uid") } catch (e: Exception) {}
            // Do not swallow the RPC account deletion error so we can bubble it up to the UI/logs.
            apiService.deleteUserAccount(getAuthHeader())
        }
    }
}
