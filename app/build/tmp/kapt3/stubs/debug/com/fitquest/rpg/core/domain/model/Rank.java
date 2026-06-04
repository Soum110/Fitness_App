package com.fitquest.rpg.core.domain.model;

/**
 * Overall user rank based on average level across all attributes.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0014\b\u0086\u0081\u0002\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001bB\'\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rj\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001a\u00a8\u0006\u001c"}, d2 = {"Lcom/fitquest/rpg/core/domain/model/Rank;", "", "title", "", "emoji", "minLevel", "", "colorHex", "", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;IJ)V", "getColorHex", "()J", "getEmoji", "()Ljava/lang/String;", "getMinLevel", "()I", "getTitle", "BRONZE_RECRUIT", "IRON_SOLDIER", "STEEL_WARRIOR", "CRYSTAL_KNIGHT", "DIAMOND_SENTINEL", "GOLD_SHADOW", "PLATINUM_HUNTER", "MYTHIC_RAIDER", "SHADOW_MONARCH", "ARISE", "Companion", "app_debug"})
public enum Rank {
    /*public static final*/ BRONZE_RECRUIT /* = new BRONZE_RECRUIT(null, null, 0, 0L) */,
    /*public static final*/ IRON_SOLDIER /* = new IRON_SOLDIER(null, null, 0, 0L) */,
    /*public static final*/ STEEL_WARRIOR /* = new STEEL_WARRIOR(null, null, 0, 0L) */,
    /*public static final*/ CRYSTAL_KNIGHT /* = new CRYSTAL_KNIGHT(null, null, 0, 0L) */,
    /*public static final*/ DIAMOND_SENTINEL /* = new DIAMOND_SENTINEL(null, null, 0, 0L) */,
    /*public static final*/ GOLD_SHADOW /* = new GOLD_SHADOW(null, null, 0, 0L) */,
    /*public static final*/ PLATINUM_HUNTER /* = new PLATINUM_HUNTER(null, null, 0, 0L) */,
    /*public static final*/ MYTHIC_RAIDER /* = new MYTHIC_RAIDER(null, null, 0, 0L) */,
    /*public static final*/ SHADOW_MONARCH /* = new SHADOW_MONARCH(null, null, 0, 0L) */,
    /*public static final*/ ARISE /* = new ARISE(null, null, 0, 0L) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String emoji = null;
    private final int minLevel = 0;
    private final long colorHex = 0L;
    @org.jetbrains.annotations.NotNull()
    public static final com.fitquest.rpg.core.domain.model.Rank.Companion Companion = null;
    
    Rank(java.lang.String title, java.lang.String emoji, int minLevel, long colorHex) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEmoji() {
        return null;
    }
    
    public final int getMinLevel() {
        return 0;
    }
    
    public final long getColorHex() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.fitquest.rpg.core.domain.model.Rank> getEntries() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/fitquest/rpg/core/domain/model/Rank$Companion;", "", "()V", "fromLevel", "Lcom/fitquest/rpg/core/domain/model/Rank;", "level", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.fitquest.rpg.core.domain.model.Rank fromLevel(int level) {
            return null;
        }
    }
}