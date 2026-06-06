package com.fitquest.rpg.core.domain.model

/**
 * A single daily task (workout exercise, diet goal, or life habit).
 */
data class DailyTask(
    val id: Long = 0L,
    val title: String,
    val description: String = "",
    val taskType: TaskType,
    val targetAttribute: AttributeType,
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
    fun formattedVolume(): String = when {
        sets != null && reps != null -> "$sets sets × $reps reps"
        durationMinutes != null -> "$durationMinutes min"
        else -> ""
    }
}

enum class TaskType(val displayName: String, val icon: String) {
    WORKOUT("Workout", "🏋️"),
    CARDIO("Cardio", "🏃"),
    STRETCH("Stretch", "🤸"),
    DIET("Diet", "🥗"),
    HABIT("Habit", "⚡"),
    READING("Reading", "📚"),
    MEDITATION("Meditation", "🧘")
}

/**
 * Reward Card in the Action Points store.
 */
data class RewardCard(
    val id: Long = 0L,
    val title: String,
    val description: String = "",
    val apCost: Int,
    val emoji: String = "🎁",
    val isPredefined: Boolean = true,
    val isRedeemed: Boolean = false,
    val redeemedAtMs: Long? = null,
    val lastRedeemedAtMs: Long? = null,
    val timesRedeemed: Int = 0,
    val hasTask: Boolean = false,
    val taskType: String = "NONE", // NONE, COUNTER, CHEAT_DAY_ROUTINE
    val taskProgress: Int = 0,
    val taskTarget: Int = 1,
    val taskCompleted: Boolean = false,
    val targetAttribute: AttributeType = AttributeType.INTELLIGENCE,
    val bonusXp: Long = 0L,
    val bonusAp: Int = 0,
    val overachieveXpPerCount: Long = 0L,
    val overachieveApPerCount: Int = 0
)

/**
 * User's economy state.
 */
data class Economy(
    val totalActionPoints: Int = 0,
    val availableActionPoints: Int = 0,
    val totalSpent: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val lastCompletionDateMs: Long? = null
)
