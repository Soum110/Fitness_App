package com.fitquest.rpg.core.domain.model;

/**
 * Reward Card in the Action Points store.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b5\b\u0086\b\u0018\u00002\u00020\u0001B\u00b7\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\b\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\b\u00a2\u0006\u0002\u0010\u0019J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0005H\u00c6\u0003J\t\u00103\u001a\u00020\bH\u00c6\u0003J\t\u00104\u001a\u00020\bH\u00c6\u0003J\t\u00105\u001a\u00020\u000bH\u00c6\u0003J\t\u00106\u001a\u00020\u0014H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\bH\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\bH\u00c6\u0003J\t\u0010;\u001a\u00020\u0005H\u00c6\u0003J\t\u0010<\u001a\u00020\u0005H\u00c6\u0003J\t\u0010=\u001a\u00020\bH\u00c6\u0003J\t\u0010>\u001a\u00020\u0005H\u00c6\u0003J\t\u0010?\u001a\u00020\u000bH\u00c6\u0003J\t\u0010@\u001a\u00020\u000bH\u00c6\u0003J\u0010\u0010A\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010(J\t\u0010B\u001a\u00020\u000bH\u00c6\u0003J\u00c4\u0001\u0010C\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\b2\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0002\u0010DJ\u0013\u0010E\u001a\u00020\u000b2\b\u0010F\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010G\u001a\u00020\bH\u00d6\u0001J\t\u0010H\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0016\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010#R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010#R\u0011\u0010\u0018\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001bR\u0011\u0010\u0017\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0015\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010)\u001a\u0004\b\'\u0010(R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0012\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010#R\u0011\u0010\u0010\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001bR\u0011\u0010\u0011\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010 \u00a8\u0006I"}, d2 = {"Lcom/fitquest/rpg/core/domain/model/RewardCard;", "", "id", "", "title", "", "description", "apCost", "", "emoji", "isPredefined", "", "isRedeemed", "redeemedAtMs", "hasTask", "taskType", "taskProgress", "taskTarget", "taskCompleted", "targetAttribute", "Lcom/fitquest/rpg/core/domain/model/AttributeType;", "bonusXp", "bonusAp", "overachieveXpPerCount", "overachieveApPerCount", "(JLjava/lang/String;Ljava/lang/String;ILjava/lang/String;ZZLjava/lang/Long;ZLjava/lang/String;IIZLcom/fitquest/rpg/core/domain/model/AttributeType;JIJI)V", "getApCost", "()I", "getBonusAp", "getBonusXp", "()J", "getDescription", "()Ljava/lang/String;", "getEmoji", "getHasTask", "()Z", "getId", "getOverachieveApPerCount", "getOverachieveXpPerCount", "getRedeemedAtMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTargetAttribute", "()Lcom/fitquest/rpg/core/domain/model/AttributeType;", "getTaskCompleted", "getTaskProgress", "getTaskTarget", "getTaskType", "getTitle", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;ILjava/lang/String;ZZLjava/lang/Long;ZLjava/lang/String;IIZLcom/fitquest/rpg/core/domain/model/AttributeType;JIJI)Lcom/fitquest/rpg/core/domain/model/RewardCard;", "equals", "other", "hashCode", "toString", "app_debug"})
public final class RewardCard {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    private final int apCost = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String emoji = null;
    private final boolean isPredefined = false;
    private final boolean isRedeemed = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long redeemedAtMs = null;
    private final boolean hasTask = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String taskType = null;
    private final int taskProgress = 0;
    private final int taskTarget = 0;
    private final boolean taskCompleted = false;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.domain.model.AttributeType targetAttribute = null;
    private final long bonusXp = 0L;
    private final int bonusAp = 0;
    private final long overachieveXpPerCount = 0L;
    private final int overachieveApPerCount = 0;
    
    public RewardCard(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, int apCost, @org.jetbrains.annotations.NotNull()
    java.lang.String emoji, boolean isPredefined, boolean isRedeemed, @org.jetbrains.annotations.Nullable()
    java.lang.Long redeemedAtMs, boolean hasTask, @org.jetbrains.annotations.NotNull()
    java.lang.String taskType, int taskProgress, int taskTarget, boolean taskCompleted, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.AttributeType targetAttribute, long bonusXp, int bonusAp, long overachieveXpPerCount, int overachieveApPerCount) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    public final int getApCost() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEmoji() {
        return null;
    }
    
    public final boolean isPredefined() {
        return false;
    }
    
    public final boolean isRedeemed() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getRedeemedAtMs() {
        return null;
    }
    
    public final boolean getHasTask() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTaskType() {
        return null;
    }
    
    public final int getTaskProgress() {
        return 0;
    }
    
    public final int getTaskTarget() {
        return 0;
    }
    
    public final boolean getTaskCompleted() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.AttributeType getTargetAttribute() {
        return null;
    }
    
    public final long getBonusXp() {
        return 0L;
    }
    
    public final int getBonusAp() {
        return 0;
    }
    
    public final long getOverachieveXpPerCount() {
        return 0L;
    }
    
    public final int getOverachieveApPerCount() {
        return 0;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final boolean component13() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.AttributeType component14() {
        return null;
    }
    
    public final long component15() {
        return 0L;
    }
    
    public final int component16() {
        return 0;
    }
    
    public final long component17() {
        return 0L;
    }
    
    public final int component18() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component8() {
        return null;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.RewardCard copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, int apCost, @org.jetbrains.annotations.NotNull()
    java.lang.String emoji, boolean isPredefined, boolean isRedeemed, @org.jetbrains.annotations.Nullable()
    java.lang.Long redeemedAtMs, boolean hasTask, @org.jetbrains.annotations.NotNull()
    java.lang.String taskType, int taskProgress, int taskTarget, boolean taskCompleted, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.AttributeType targetAttribute, long bonusXp, int bonusAp, long overachieveXpPerCount, int overachieveApPerCount) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}