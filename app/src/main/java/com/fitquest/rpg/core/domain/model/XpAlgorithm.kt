package com.fitquest.rpg.core.domain.model

import kotlin.math.ln

/**
 * The core XP algorithm powering a 1–2 year slow-burn progression curve.
 *
 * Design Goals:
 *  - Level 10  → ~3 weeks  (early dopamine hits)
 *  - Level 25  → ~2 months
 *  - Level 50  → ~6 months (mid-game)
 *  - Level 75  → ~12 months
 *  - Level 100 → ~20 months (end game "Arise" rank)
 *
 * Formula: xp = 100 * level * ln(level + 1) * 1.5
 * This gives a logarithmic curve that starts fast and gradually steepens,
 * keeping the user engaged early while maintaining long-term challenge.
 */
object XpAlgorithm {

    const val MAX_LEVEL = 100

    /**
     * XP required to advance FROM a given level to the next.
     */
    fun xpRequiredForLevel(level: Int): Long {
        if (level >= MAX_LEVEL) return Long.MAX_VALUE
        val l = level.toDouble()
        return (100.0 * Math.pow(l, 1.4) * ln(l + 1.0)).toLong().coerceAtLeast(50L)
    }

    /**
     * Total cumulative XP needed to reach a given level from Level 1.
     */
    fun totalXpToReachLevel(targetLevel: Int): Long {
        return (1 until targetLevel).sumOf { xpRequiredForLevel(it) }
    }

    /**
     * Given a total XP value, compute the current level and XP within that level.
     */
    fun levelFromTotalXp(totalXp: Long): Pair<Int, Long> {
        var level = 1
        var remaining = totalXp
        while (level < MAX_LEVEL) {
            val needed = xpRequiredForLevel(level)
            if (remaining < needed) break
            remaining -= needed
            level++
        }
        return Pair(level, remaining)
    }

    /**
     * XP required to advance FROM a given global level to the next.
     */
    fun globalXpRequiredForLevel(level: Int): Long {
        if (level >= MAX_LEVEL) return Long.MAX_VALUE
        return 5 * xpRequiredForLevel(level)
    }

    /**
     * Total cumulative XP needed to reach a given global level from Level 1.
     */
    fun globalTotalXpToReachLevel(targetLevel: Int): Long {
        return 5 * totalXpToReachLevel(targetLevel)
    }

    /**
     * Given a total cumulative global XP, compute the global level and progress fraction.
     */
    fun globalLevelFromTotalXp(totalXp: Long): Pair<Int, Float> {
        var level = 1
        var remaining = totalXp
        while (level < MAX_LEVEL) {
            val needed = globalXpRequiredForLevel(level)
            if (remaining < needed) break
            remaining -= needed
            level++
        }
        val neededForNext = globalXpRequiredForLevel(level)
        val progress = if (level >= MAX_LEVEL) 0f else (remaining.toFloat() / neededForNext.toFloat()).coerceIn(0f, 1f)
        return Pair(level, progress)
    }

    /**
     * XP awarded for completing a task, scaled by difficulty and attribute.
     */
    fun xpForTask(difficultyMultiplier: Float = 1.0f): Long {
        return (25 * difficultyMultiplier).toLong().coerceAtLeast(10L)
    }
}

/**
 * Progressive Overload Engine.
 *
 * Follows a 4-week cycle:
 *  Week 1: Base volume
 *  Week 2: +5% reps (same sets)
 *  Week 3: +1 set (same reps as week 1)
 *  Week 4: Deload (–20% volume for recovery)
 * Each month, the base is incremented to ensure long-term progression.
 */
object ProgressiveOverloadEngine {

    data class WorkoutVolume(
        val sets: Int,
        val reps: Int,
        val restSeconds: Int
    )

    /**
     * Returns the target volume for a given exercise on a given training week (1-indexed).
     * @param weekNumber    The current training week since app start.
     * @param baseSets      The starting number of sets for this exercise.
     * @param baseReps      The starting number of reps for this exercise.
     */
    fun computeVolume(weekNumber: Int, baseSets: Int, baseReps: Int): WorkoutVolume {
        val monthIndex = (weekNumber - 1) / 4  // 0-indexed month
        val weekInCycle = ((weekNumber - 1) % 4) + 1  // 1–4

        // Monthly base increment: +1 rep per month, +1 set every 2 months
        val monthlyRepBonus = monthIndex
        val monthlySetBonus = monthIndex / 2

        val adjustedBaseReps = baseReps + monthlyRepBonus
        val adjustedBaseSets = baseSets + monthlySetBonus

        return when (weekInCycle) {
            1 -> WorkoutVolume(adjustedBaseSets, adjustedBaseReps, 90)
            2 -> WorkoutVolume(adjustedBaseSets, (adjustedBaseReps * 1.05f).toInt(), 90)
            3 -> WorkoutVolume(adjustedBaseSets + 1, adjustedBaseReps, 90)
            4 -> { // Deload
                val deloadReps = (adjustedBaseReps * 0.8f).toInt()
                val deloadSets = (adjustedBaseSets * 0.8f).toInt().coerceAtLeast(1)
                WorkoutVolume(deloadSets, deloadReps, 120)
            }
            else -> WorkoutVolume(adjustedBaseSets, adjustedBaseReps, 90)
        }
    }

    /**
     * Returns a human-readable description of the current week's phase.
     */
    fun weekPhaseDescription(weekNumber: Int): String {
        return when (((weekNumber - 1) % 4) + 1) {
            1 -> "📊 Base Volume Week"
            2 -> "⬆️ Intensity Week (+5% reps)"
            3 -> "💥 Volume Overload Week (+1 set)"
            4 -> "🔄 Deload & Recovery Week"
            else -> ""
        }
    }
}
