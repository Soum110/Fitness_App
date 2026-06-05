package com.fitquest.rpg.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fitquest.rpg.core.domain.model.*

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Long = 1L,
    val name: String = "",
    val age: Int = 25,
    val gender: String = Gender.PREFER_NOT_TO_SAY.name,
    val heightCm: Float = 170f,
    val weightKg: Float = 70f,
    val fitnessLevel: String = FitnessLevel.BEGINNER.name,
    val primaryGoal: String = FitnessGoal.GENERAL_FITNESS.name,
    val dietaryStyle: String = DietaryStyle.OMNIVORE.name,
    val workoutDaysPerWeek: Int = 4,
    val wakeTimeHour: Int = 7,
    val sleepTimeHour: Int = 23,
    val onboardingComplete: Boolean = false,
    val joinDateMs: Long = System.currentTimeMillis()
) {
    fun toDomain() = UserProfile(
        id = id, name = name, age = age,
        gender = Gender.valueOf(gender),
        heightCm = heightCm, weightKg = weightKg,
        fitnessLevel = FitnessLevel.valueOf(fitnessLevel),
        primaryGoal = FitnessGoal.valueOf(primaryGoal),
        dietaryStyle = DietaryStyle.valueOf(dietaryStyle),
        workoutDaysPerWeek = workoutDaysPerWeek,
        wakeTimeHour = wakeTimeHour, sleepTimeHour = sleepTimeHour,
        onboardingComplete = onboardingComplete, joinDateMs = joinDateMs
    )
}

fun UserProfile.toEntity() = UserProfileEntity(
    id = id, name = name, age = age,
    gender = gender.name,
    heightCm = heightCm, weightKg = weightKg,
    fitnessLevel = fitnessLevel.name,
    primaryGoal = primaryGoal.name,
    dietaryStyle = dietaryStyle.name,
    workoutDaysPerWeek = workoutDaysPerWeek,
    wakeTimeHour = wakeTimeHour, sleepTimeHour = sleepTimeHour,
    onboardingComplete = onboardingComplete, joinDateMs = joinDateMs
)

@Entity(tableName = "attributes")
data class AttributeEntity(
    @PrimaryKey val type: String,
    val level: Int = 1,
    val currentXp: Long = 0L,
    val totalXpEarned: Long = 0L
) {
    fun toDomain() = Attribute(
        type = AttributeType.valueOf(type),
        level = level, currentXp = currentXp, totalXpEarned = totalXpEarned
    )
}

fun Attribute.toEntity() = AttributeEntity(
    type = type.name, level = level, currentXp = currentXp, totalXpEarned = totalXpEarned
)

@Entity(tableName = "daily_tasks")
data class DailyTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val description: String = "",
    val taskType: String,
    val targetAttribute: String,
    val xpReward: Long,
    val apReward: Int = 0,
    val sets: Int? = null,
    val reps: Int? = null,
    val durationMinutes: Int? = null,
    val isCompleted: Boolean = false,
    val completedAtMs: Long? = null,
    val dateMs: Long = System.currentTimeMillis(),
    val difficultyMultiplier: Float = 1.0f
) {
    fun toDomain() = DailyTask(
        id = id, title = title, description = description,
        taskType = TaskType.valueOf(taskType),
        targetAttribute = AttributeType.valueOf(targetAttribute),
        xpReward = xpReward, apReward = apReward,
        sets = sets, reps = reps, durationMinutes = durationMinutes,
        isCompleted = isCompleted, completedAtMs = completedAtMs,
        dateMs = dateMs, difficultyMultiplier = difficultyMultiplier
    )
}

fun DailyTask.toEntity() = DailyTaskEntity(
    id = id, title = title, description = description,
    taskType = taskType.name, targetAttribute = targetAttribute.name,
    xpReward = xpReward, apReward = apReward,
    sets = sets, reps = reps, durationMinutes = durationMinutes,
    isCompleted = isCompleted, completedAtMs = completedAtMs,
    dateMs = dateMs, difficultyMultiplier = difficultyMultiplier
)

@Entity(tableName = "reward_cards")
data class RewardCardEntity(
    @PrimaryKey val id: Long = 0L,
    val title: String,
    val description: String = "",
    val apCost: Int,
    val emoji: String = "🎁",
    val isPredefined: Boolean = true,
    val isRedeemed: Boolean = false,
    val redeemedAtMs: Long? = null,
    val hasTask: Boolean = false,
    val taskType: String = "NONE",
    val taskProgress: Int = 0,
    val taskTarget: Int = 1,
    val taskCompleted: Boolean = false,
    val targetAttribute: String = "INTELLIGENCE",
    val bonusXp: Long = 0L,
    val bonusAp: Int = 0,
    val overachieveXpPerCount: Long = 0L,
    val overachieveApPerCount: Int = 0
) {
    fun toDomain() = RewardCard(
        id = id, title = title, description = description,
        apCost = apCost, emoji = emoji,
        isPredefined = isPredefined, isRedeemed = isRedeemed,
        redeemedAtMs = redeemedAtMs,
        hasTask = hasTask,
        taskType = taskType,
        taskProgress = taskProgress,
        taskTarget = taskTarget,
        taskCompleted = taskCompleted,
        targetAttribute = try { AttributeType.valueOf(targetAttribute) } catch(e: Exception) { AttributeType.INTELLIGENCE },
        bonusXp = bonusXp,
        bonusAp = bonusAp,
        overachieveXpPerCount = overachieveXpPerCount,
        overachieveApPerCount = overachieveApPerCount
    )
}

fun RewardCard.toEntity() = RewardCardEntity(
    id = id, title = title, description = description,
    apCost = apCost, emoji = emoji,
    isPredefined = isPredefined, isRedeemed = isRedeemed,
    redeemedAtMs = redeemedAtMs,
    hasTask = hasTask,
    taskType = taskType,
    taskProgress = taskProgress,
    taskTarget = taskTarget,
    taskCompleted = taskCompleted,
    targetAttribute = targetAttribute.name,
    bonusXp = bonusXp,
    bonusAp = bonusAp,
    overachieveXpPerCount = overachieveXpPerCount,
    overachieveApPerCount = overachieveApPerCount
)

@Entity(tableName = "economy")
data class EconomyEntity(
    @PrimaryKey val id: Long = 1L,
    val totalActionPoints: Int = 0,
    val availableActionPoints: Int = 0,
    val totalSpent: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val lastCompletionDateMs: Long? = null
) {
    fun toDomain() = com.fitquest.rpg.core.domain.model.Economy(
        totalActionPoints = totalActionPoints,
        availableActionPoints = availableActionPoints,
        totalSpent = totalSpent,
        currentStreak = currentStreak,
        longestStreak = longestStreak,
        lastCompletionDateMs = lastCompletionDateMs
    )
}
