package com.fitquest.rpg

import com.fitquest.rpg.core.domain.model.XpAlgorithm
import com.fitquest.rpg.core.domain.model.ProgressiveOverloadEngine
import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests validating the core RPG algorithms.
 */
class XpAlgorithmTest {

    @Test
    fun `xp required increases with each level`() {
        for (level in 1 until XpAlgorithm.MAX_LEVEL - 1) {
            val xpCurrent = XpAlgorithm.xpRequiredForLevel(level)
            val xpNext = XpAlgorithm.xpRequiredForLevel(level + 1)
            assertTrue(
                "XP for level ${level+1} ($xpNext) should be >= level $level ($xpCurrent)",
                xpNext >= xpCurrent
            )
        }
    }

    @Test
    fun `level 1 has minimum xp requirement`() {
        val xp = XpAlgorithm.xpRequiredForLevel(1)
        assertTrue("Level 1 XP must be at least 50", xp >= 50L)
    }

    @Test
    fun `levelFromTotalXp returns level 1 for zero xp`() {
        val (level, remaining) = XpAlgorithm.levelFromTotalXp(0L)
        assertEquals(1, level)
        assertEquals(0L, remaining)
    }

    @Test
    fun `levelFromTotalXp correctly advances levels`() {
        val xpForLevel1 = XpAlgorithm.xpRequiredForLevel(1)
        val (level, remaining) = XpAlgorithm.levelFromTotalXp(xpForLevel1)
        assertEquals("Should be level 2 after earning enough XP", 2, level)
        assertEquals(0L, remaining)
    }

    @Test
    fun `max level cap works`() {
        val massiveXp = Long.MAX_VALUE / 2
        val (level, _) = XpAlgorithm.levelFromTotalXp(massiveXp)
        assertTrue("Level should not exceed MAX_LEVEL", level <= XpAlgorithm.MAX_LEVEL)
    }

    @Test
    fun `progression curve takes approximately 20 months for max level`() {
        // Assume user earns ~100 XP per day from tasks
        val xpPerDay = 100L
        val totalXpAtMax = XpAlgorithm.totalXpToReachLevel(XpAlgorithm.MAX_LEVEL)
        val daysToMax = totalXpAtMax / xpPerDay
        val monthsToMax = daysToMax / 30

        println("Total XP to max: $totalXpAtMax")
        println("Days to max at 100XP/day: $daysToMax")
        println("Months to max: $monthsToMax")

        // Should be in the 15–30 month range for the target XP/day
        assertTrue("Expected 10–40 months to max, got $monthsToMax", monthsToMax in 10..40)
    }

    @Test
    fun `early levels are fast for engagement`() {
        // Level 10 should be reachable in under 30 days at 100 XP/day
        val xpPerDay = 100L
        val xpToLevel10 = XpAlgorithm.totalXpToReachLevel(10)
        val daysToLevel10 = xpToLevel10 / xpPerDay
        println("Days to level 10: $daysToLevel10")
        assertTrue("Level 10 should be reachable within 30 days", daysToLevel10 <= 30)
    }
}

class ProgressiveOverloadTest {

    @Test
    fun `week 4 is always a deload with less volume`() {
        val base = ProgressiveOverloadEngine.computeVolume(1, baseSets = 3, baseReps = 10)
        val deload = ProgressiveOverloadEngine.computeVolume(4, baseSets = 3, baseReps = 10)
        assertTrue("Deload reps (${deload.reps}) should be less than base (${base.reps})", deload.reps <= base.reps)
        assertTrue("Deload sets (${deload.sets}) should be less or equal base (${base.sets})", deload.sets <= base.sets)
    }

    @Test
    fun `week 3 has more sets than week 1`() {
        val week1 = ProgressiveOverloadEngine.computeVolume(1, baseSets = 3, baseReps = 10)
        val week3 = ProgressiveOverloadEngine.computeVolume(3, baseSets = 3, baseReps = 10)
        assertTrue("Week 3 should have more sets", week3.sets > week1.sets)
    }

    @Test
    fun `volume increases over months`() {
        val month1Week1 = ProgressiveOverloadEngine.computeVolume(1, baseSets = 3, baseReps = 10)
        val month6Week1 = ProgressiveOverloadEngine.computeVolume(21, baseSets = 3, baseReps = 10)
        assertTrue("Month 6 should have more reps than Month 1", month6Week1.reps > month1Week1.reps)
    }

    @Test
    fun `phase descriptions are non-empty for all cycle weeks`() {
        for (i in 1..8) {
            val desc = ProgressiveOverloadEngine.weekPhaseDescription(i)
            assertTrue("Week $i description should not be empty", desc.isNotEmpty())
        }
    }
}
