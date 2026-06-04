package com.fitquest.rpg.core.domain.model

/**
 * Core RPG Attribute types. Each maps to specific workout/task categories.
 */
enum class AttributeType(val displayName: String, val emoji: String, val color: Long) {
    STRENGTH("Strength", "💪", 0xFFE53935),
    FLEXIBILITY("Flexibility", "🤸", 0xFF43A047),
    STAMINA("Stamina", "🏃", 0xFF1E88E5),
    ENERGY("Energy", "⚡", 0xFFFDD835),
    INTELLIGENCE("Intelligence", "🧠", 0xFFAB47BC)
}

/**
 * A single attribute with its current level, XP, and rank.
 */
data class Attribute(
    val type: AttributeType,
    val level: Int = 1,
    val currentXp: Long = 0L,
    val totalXpEarned: Long = 0L
) {
    val xpForNextLevel: Long get() = XpAlgorithm.xpRequiredForLevel(level)
    val progressFraction: Float get() = (currentXp.toFloat() / xpForNextLevel.toFloat()).coerceIn(0f, 1f)
    val rank: Rank get() = Rank.fromLevel(level)
}

/**
 * Overall user rank based on average level across all attributes.
 */
enum class Rank(val title: String, val emoji: String, val minLevel: Int, val colorHex: Long) {
    BRONZE_RECRUIT("Bronze Recruit", "🔘", 1, 0xFFCD7F32),
    IRON_SOLDIER("Iron Soldier", "⚪", 10, 0xFFB0BEC5),
    STEEL_WARRIOR("Steel Warrior", "🟢", 20, 0xFF66BB6A),
    CRYSTAL_KNIGHT("Crystal Knight", "🔵", 30, 0xFF42A5F5),
    DIAMOND_SENTINEL("Diamond Sentinel", "🟣", 40, 0xFFAB47BC),
    GOLD_SHADOW("Gold Shadow", "🟡", 50, 0xFFFFD54F),
    PLATINUM_HUNTER("Platinum Hunter", "🟠", 60, 0xFFFF8A65),
    MYTHIC_RAIDER("Mythic Raider", "🔴", 70, 0xFFEF5350),
    SHADOW_MONARCH("Shadow Monarch", "⚡", 80, 0xFF7E57C2),
    ARISE("Arise", "👑", 90, 0xFFFFD700);

    companion object {
        fun fromLevel(level: Int): Rank =
            values().reversed().firstOrNull { level >= it.minLevel } ?: BRONZE_RECRUIT
    }
}
