package com.fitquest.rpg.core.data.remote

import com.fitquest.rpg.core.domain.model.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * All Firestore documents are scoped under users/{uid}/...
 * This ensures each user's data is completely isolated.
 */
@Singleton
class FirestoreRepository @Inject constructor(
    private val db: FirebaseFirestore
) {
    // ── Path helpers ────────────────────────────────────────────────────────
    private fun userDoc(uid: String) = db.collection("users").document(uid)
    private fun profileDoc(uid: String) = userDoc(uid).collection("data").document("profile")
    private fun economyDoc(uid: String) = userDoc(uid).collection("data").document("economy")
    private fun attributesCol(uid: String) = userDoc(uid).collection("attributes")
    private fun tasksCol(uid: String) = userDoc(uid).collection("tasks")
    private fun cardsCol(uid: String) = userDoc(uid).collection("rewardCards")

    private fun todayKey() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    // ── Profile ─────────────────────────────────────────────────────────────
    suspend fun saveProfile(uid: String, profile: UserProfile) {
        val data = mapOf(
            "name" to profile.name,
            "age" to profile.age,
            "gender" to profile.gender.name,
            "heightCm" to profile.heightCm,
            "weightKg" to profile.weightKg,
            "fitnessLevel" to profile.fitnessLevel.name,
            "primaryGoal" to profile.primaryGoal.name,
            "dietaryStyle" to profile.dietaryStyle.name,
            "workoutDaysPerWeek" to profile.workoutDaysPerWeek,
            "wakeTimeHour" to profile.wakeTimeHour,
            "sleepTimeHour" to profile.sleepTimeHour,
            "onboardingComplete" to profile.onboardingComplete,
            "joinDateMs" to profile.joinDateMs
        )
        profileDoc(uid).set(data, SetOptions.merge()).await()
    }

    fun observeProfile(uid: String): Flow<UserProfile?> = callbackFlow {
        val listener = profileDoc(uid).addSnapshotListener { snap, error ->
            if (error != null) { trySend(null); return@addSnapshotListener }
            if (snap == null || !snap.exists()) { trySend(null); return@addSnapshotListener }
            try {
                val profile = UserProfile(
                    id = 1L,
                    name = snap.getString("name") ?: "",
                    age = (snap.getLong("age") ?: 25).toInt(),
                    gender = Gender.valueOf(snap.getString("gender") ?: Gender.PREFER_NOT_TO_SAY.name),
                    heightCm = (snap.getDouble("heightCm") ?: 170.0).toFloat(),
                    weightKg = (snap.getDouble("weightKg") ?: 70.0).toFloat(),
                    fitnessLevel = FitnessLevel.valueOf(snap.getString("fitnessLevel") ?: FitnessLevel.BEGINNER.name),
                    primaryGoal = FitnessGoal.valueOf(snap.getString("primaryGoal") ?: FitnessGoal.GENERAL_FITNESS.name),
                    dietaryStyle = DietaryStyle.valueOf(snap.getString("dietaryStyle") ?: DietaryStyle.OMNIVORE.name),
                    workoutDaysPerWeek = (snap.getLong("workoutDaysPerWeek") ?: 4).toInt(),
                    wakeTimeHour = (snap.getLong("wakeTimeHour") ?: 7).toInt(),
                    sleepTimeHour = (snap.getLong("sleepTimeHour") ?: 23).toInt(),
                    onboardingComplete = snap.getBoolean("onboardingComplete") ?: false,
                    joinDateMs = snap.getLong("joinDateMs") ?: System.currentTimeMillis()
                )
                trySend(profile)
            } catch (e: Exception) {
                trySend(null)
            }
        }
        awaitClose { listener.remove() }
    }

    suspend fun getProfile(uid: String): UserProfile? {
        return try {
            val snap = profileDoc(uid).get().await()
            if (!snap.exists()) return null
            UserProfile(
                id = 1L,
                name = snap.getString("name") ?: "",
                age = (snap.getLong("age") ?: 25).toInt(),
                gender = Gender.valueOf(snap.getString("gender") ?: Gender.PREFER_NOT_TO_SAY.name),
                heightCm = (snap.getDouble("heightCm") ?: 170.0).toFloat(),
                weightKg = (snap.getDouble("weightKg") ?: 70.0).toFloat(),
                fitnessLevel = FitnessLevel.valueOf(snap.getString("fitnessLevel") ?: FitnessLevel.BEGINNER.name),
                primaryGoal = FitnessGoal.valueOf(snap.getString("primaryGoal") ?: FitnessGoal.GENERAL_FITNESS.name),
                dietaryStyle = DietaryStyle.valueOf(snap.getString("dietaryStyle") ?: DietaryStyle.OMNIVORE.name),
                workoutDaysPerWeek = (snap.getLong("workoutDaysPerWeek") ?: 4).toInt(),
                wakeTimeHour = (snap.getLong("wakeTimeHour") ?: 7).toInt(),
                sleepTimeHour = (snap.getLong("sleepTimeHour") ?: 23).toInt(),
                onboardingComplete = snap.getBoolean("onboardingComplete") ?: false,
                joinDateMs = snap.getLong("joinDateMs") ?: System.currentTimeMillis()
            )
        } catch (e: Exception) { null }
    }

    // ── Economy ─────────────────────────────────────────────────────────────
    suspend fun saveEconomy(uid: String, economy: Economy) {
        val data = mapOf(
            "totalActionPoints" to economy.totalActionPoints,
            "availableActionPoints" to economy.availableActionPoints,
            "totalSpent" to economy.totalSpent,
            "currentStreak" to economy.currentStreak,
            "longestStreak" to economy.longestStreak,
            "lastCompletionDateMs" to economy.lastCompletionDateMs
        )
        economyDoc(uid).set(data, SetOptions.merge()).await()
    }

    fun observeEconomy(uid: String): Flow<Economy?> = callbackFlow {
        val listener = economyDoc(uid).addSnapshotListener { snap, error ->
            if (error != null || snap == null || !snap.exists()) {
                trySend(Economy()); return@addSnapshotListener
            }
            trySend(Economy(
                totalActionPoints = (snap.getLong("totalActionPoints") ?: 0).toInt(),
                availableActionPoints = (snap.getLong("availableActionPoints") ?: 0).toInt(),
                totalSpent = (snap.getLong("totalSpent") ?: 0).toInt(),
                currentStreak = (snap.getLong("currentStreak") ?: 0).toInt(),
                longestStreak = (snap.getLong("longestStreak") ?: 0).toInt(),
                lastCompletionDateMs = snap.getLong("lastCompletionDateMs")
            ))
        }
        awaitClose { listener.remove() }
    }

    suspend fun getEconomy(uid: String): Economy {
        return try {
            val snap = economyDoc(uid).get().await()
            if (!snap.exists()) return Economy()
            Economy(
                totalActionPoints = (snap.getLong("totalActionPoints") ?: 0).toInt(),
                availableActionPoints = (snap.getLong("availableActionPoints") ?: 0).toInt(),
                totalSpent = (snap.getLong("totalSpent") ?: 0).toInt(),
                currentStreak = (snap.getLong("currentStreak") ?: 0).toInt(),
                longestStreak = (snap.getLong("longestStreak") ?: 0).toInt(),
                lastCompletionDateMs = snap.getLong("lastCompletionDateMs")
            )
        } catch (e: Exception) { Economy() }
    }

    // ── Attributes ──────────────────────────────────────────────────────────
    suspend fun saveAttribute(uid: String, attribute: Attribute) {
        val data = mapOf(
            "level" to attribute.level,
            "currentXp" to attribute.currentXp,
            "totalXpEarned" to attribute.totalXpEarned
        )
        attributesCol(uid).document(attribute.type.name).set(data, SetOptions.merge()).await()
    }

    fun observeAttributes(uid: String): Flow<List<Attribute>> = callbackFlow {
        val listener = attributesCol(uid).addSnapshotListener { snap, error ->
            if (error != null || snap == null) { trySend(emptyList()); return@addSnapshotListener }
            val attrs = snap.documents.mapNotNull { doc ->
                try {
                    Attribute(
                        type = AttributeType.valueOf(doc.id),
                        level = (doc.getLong("level") ?: 1).toInt(),
                        currentXp = doc.getLong("currentXp") ?: 0L,
                        totalXpEarned = doc.getLong("totalXpEarned") ?: 0L
                    )
                } catch (e: Exception) { null }
            }
            trySend(attrs)
        }
        awaitClose { listener.remove() }
    }

    // ── Tasks ────────────────────────────────────────────────────────────────
    suspend fun saveTasks(uid: String, tasks: List<DailyTask>) {
        val dateKey = todayKey()
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
        tasksCol(uid).document(dateKey).set(mapOf("tasks" to taskMaps), SetOptions.merge()).await()
    }

    fun observeTodaysTasks(uid: String): Flow<List<DailyTask>> = callbackFlow {
        val dateKey = todayKey()
        val listener = tasksCol(uid).document(dateKey).addSnapshotListener { snap, error ->
            if (error != null || snap == null || !snap.exists()) {
                trySend(emptyList()); return@addSnapshotListener
            }
            @Suppress("UNCHECKED_CAST")
            val rawTasks = snap.get("tasks") as? List<Map<String, Any>> ?: emptyList()
            val tasks = rawTasks.mapNotNull { map ->
                try {
                    DailyTask(
                        id = (map["id"] as? Long) ?: 0L,
                        title = map["title"] as? String ?: "",
                        description = map["description"] as? String ?: "",
                        taskType = TaskType.valueOf(map["taskType"] as? String ?: TaskType.WORKOUT.name),
                        targetAttribute = AttributeType.valueOf(map["targetAttribute"] as? String ?: AttributeType.STRENGTH.name),
                        xpReward = (map["xpReward"] as? Long) ?: 0L,
                        apReward = ((map["apReward"] as? Long) ?: 0L).toInt(),
                        sets = (map["sets"] as? Long)?.toInt(),
                        reps = (map["reps"] as? Long)?.toInt(),
                        durationMinutes = (map["durationMinutes"] as? Long)?.toInt(),
                        isCompleted = map["isCompleted"] as? Boolean ?: false,
                        completedAtMs = map["completedAtMs"] as? Long,
                        dateMs = (map["dateMs"] as? Long) ?: System.currentTimeMillis(),
                        difficultyMultiplier = ((map["difficultyMultiplier"] as? Double) ?: 1.0).toFloat()
                    )
                } catch (e: Exception) { null }
            }
            trySend(tasks)
        }
        awaitClose { listener.remove() }
    }

    suspend fun getTodaysTasks(uid: String): List<DailyTask> {
        return try {
            val snap = tasksCol(uid).document(todayKey()).get().await()
            if (!snap.exists()) return emptyList()
            @Suppress("UNCHECKED_CAST")
            val rawTasks = snap.get("tasks") as? List<Map<String, Any>> ?: return emptyList()
            rawTasks.mapNotNull { map ->
                try {
                    DailyTask(
                        id = (map["id"] as? Long) ?: 0L,
                        title = map["title"] as? String ?: "",
                        description = map["description"] as? String ?: "",
                        taskType = TaskType.valueOf(map["taskType"] as? String ?: TaskType.WORKOUT.name),
                        targetAttribute = AttributeType.valueOf(map["targetAttribute"] as? String ?: AttributeType.STRENGTH.name),
                        xpReward = (map["xpReward"] as? Long) ?: 0L,
                        apReward = ((map["apReward"] as? Long) ?: 0L).toInt(),
                        sets = (map["sets"] as? Long)?.toInt(),
                        reps = (map["reps"] as? Long)?.toInt(),
                        durationMinutes = (map["durationMinutes"] as? Long)?.toInt(),
                        isCompleted = map["isCompleted"] as? Boolean ?: false,
                        completedAtMs = map["completedAtMs"] as? Long,
                        dateMs = (map["dateMs"] as? Long) ?: System.currentTimeMillis(),
                        difficultyMultiplier = ((map["difficultyMultiplier"] as? Double) ?: 1.0).toFloat()
                    )
                } catch (e: Exception) { null }
            }
        } catch (e: Exception) { emptyList() }
    }

    // ── Reward Cards ─────────────────────────────────────────────────────────
    suspend fun saveRewardCards(uid: String, cards: List<RewardCard>) {
        val batch = db.batch()
        cards.forEach { card ->
            val ref = cardsCol(uid).document(card.id.toString())
            val data = mapOf(
                "id" to card.id,
                "title" to card.title,
                "description" to card.description,
                "apCost" to card.apCost,
                "emoji" to card.emoji,
                "isPredefined" to card.isPredefined,
                "isRedeemed" to card.isRedeemed,
                "redeemedAtMs" to card.redeemedAtMs,
                "lastRedeemedAtMs" to card.lastRedeemedAtMs,
                "timesRedeemed" to card.timesRedeemed,
                "hasTask" to card.hasTask,
                "taskType" to card.taskType,
                "taskProgress" to card.taskProgress,
                "taskTarget" to card.taskTarget,
                "taskCompleted" to card.taskCompleted,
                "targetAttribute" to card.targetAttribute.name,
                "bonusXp" to card.bonusXp,
                "bonusAp" to card.bonusAp,
                "overachieveXpPerCount" to card.overachieveXpPerCount,
                "overachieveApPerCount" to card.overachieveApPerCount
            )
            batch.set(ref, data, SetOptions.merge())
        }
        batch.commit().await()
    }

    fun observeRewardCards(uid: String): Flow<List<RewardCard>> = callbackFlow {
        val listener = cardsCol(uid).addSnapshotListener { snap, error ->
            if (error != null || snap == null) { trySend(emptyList()); return@addSnapshotListener }
            val cards = snap.documents.mapNotNull { doc ->
                try {
                    RewardCard(
                        id = (doc.getLong("id") ?: 0L),
                        title = doc.getString("title") ?: "",
                        description = doc.getString("description") ?: "",
                        apCost = (doc.getLong("apCost") ?: 0).toInt(),
                        emoji = doc.getString("emoji") ?: "🎁",
                        isPredefined = doc.getBoolean("isPredefined") ?: true,
                        isRedeemed = doc.getBoolean("isRedeemed") ?: false,
                        redeemedAtMs = doc.getLong("redeemedAtMs"),
                        lastRedeemedAtMs = doc.getLong("lastRedeemedAtMs"),
                        timesRedeemed = (doc.getLong("timesRedeemed") ?: 0).toInt(),
                        hasTask = doc.getBoolean("hasTask") ?: false,
                        taskType = doc.getString("taskType") ?: "NONE",
                        taskProgress = (doc.getLong("taskProgress") ?: 0).toInt(),
                        taskTarget = (doc.getLong("taskTarget") ?: 1).toInt(),
                        taskCompleted = doc.getBoolean("taskCompleted") ?: false,
                        targetAttribute = try { AttributeType.valueOf(doc.getString("targetAttribute") ?: "INTELLIGENCE") } catch(e: Exception) { AttributeType.INTELLIGENCE },
                        bonusXp = doc.getLong("bonusXp") ?: 0L,
                        bonusAp = (doc.getLong("bonusAp") ?: 0).toInt(),
                        overachieveXpPerCount = doc.getLong("overachieveXpPerCount") ?: 0L,
                        overachieveApPerCount = (doc.getLong("overachieveApPerCount") ?: 0).toInt()
                    )
                } catch (e: Exception) { null }
            }
            trySend(cards)
        }
        awaitClose { listener.remove() }
    }

    suspend fun deleteCard(uid: String, card: RewardCard) {
        cardsCol(uid).document(card.id.toString()).delete().await()
    }

    suspend fun deleteUserData(uid: String) {
        profileDoc(uid).delete().await()
        economyDoc(uid).delete().await()

        val attributes = attributesCol(uid).get().await()
        for (doc in attributes.documents) {
            doc.reference.delete().await()
        }

        val tasks = tasksCol(uid).get().await()
        for (doc in tasks.documents) {
            doc.reference.delete().await()
        }

        val cards = cardsCol(uid).get().await()
        for (doc in cards.documents) {
            doc.reference.delete().await()
        }

        userDoc(uid).delete().await()
    }
}
