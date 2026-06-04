package com.fitquest.rpg.core.domain.model

/**
 * User profile created during onboarding.
 */
data class UserProfile(
    val id: Long = 1L,
    val name: String = "",
    val age: Int = 25,
    val gender: Gender = Gender.PREFER_NOT_TO_SAY,
    val heightCm: Float = 170f,
    val weightKg: Float = 70f,
    val fitnessLevel: FitnessLevel = FitnessLevel.BEGINNER,
    val primaryGoal: FitnessGoal = FitnessGoal.GENERAL_FITNESS,
    val dietaryStyle: DietaryStyle = DietaryStyle.OMNIVORE,
    val workoutDaysPerWeek: Int = 4,
    val wakeTimeHour: Int = 7,
    val sleepTimeHour: Int = 23,
    val onboardingComplete: Boolean = false,
    val joinDateMs: Long = System.currentTimeMillis()
) {
    val trainingWeekNumber: Int
        get() {
            val daysSinceJoin = ((System.currentTimeMillis() - joinDateMs) / 86_400_000L).toInt()
            return (daysSinceJoin / 7) + 1
        }

    val bmi: Float get() = weightKg / ((heightCm / 100f) * (heightCm / 100f))

    val transformationPhase: TransformationPhase get() = when {
        primaryGoal == FitnessGoal.LOSE_FAT -> TransformationPhase.CUT
        primaryGoal == FitnessGoal.BUILD_MUSCLE && bmi < 22f -> TransformationPhase.BULK
        else -> TransformationPhase.RECOMP
    }
}

enum class Gender(val displayName: String) {
    MALE("Male"), FEMALE("Female"), PREFER_NOT_TO_SAY("Prefer not to say")
}

enum class FitnessLevel(val displayName: String, val multiplier: Float) {
    BEGINNER("Beginner 🌱", 0.7f),
    INTERMEDIATE("Intermediate ⚔️", 1.0f),
    ADVANCED("Advanced 🔥", 1.3f)
}

enum class FitnessGoal(val displayName: String) {
    LOSE_FAT("Lose Fat 🔥"),
    BUILD_MUSCLE("Build Muscle 💪"),
    GENERAL_FITNESS("General Fitness ✨"),
    ATHLETIC_PERFORMANCE("Athletic Performance 🏆")
}

enum class DietaryStyle(val displayName: String) {
    OMNIVORE("Omnivore 🥩"),
    VEGETARIAN("Vegetarian 🥗"),
    VEGAN("Vegan 🌱"),
    KETO("Keto 🥑")
}

enum class TransformationPhase(val displayName: String, val description: String) {
    CUT("Cutting Phase", "Caloric deficit to lose fat while preserving muscle"),
    BULK("Bulking Phase", "Caloric surplus to maximize muscle growth"),
    RECOMP("Recomposition", "Maintain calories to simultaneously build muscle and lose fat")
}
