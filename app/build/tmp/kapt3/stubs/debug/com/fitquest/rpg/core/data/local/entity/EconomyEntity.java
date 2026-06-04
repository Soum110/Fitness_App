package com.fitquest.rpg.core.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.fitquest.rpg.core.domain.model.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012JV\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020\u0005H\u00d6\u0001J\u0006\u0010$\u001a\u00020%J\t\u0010&\u001a\u00020\'H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\r\u00a8\u0006("}, d2 = {"Lcom/fitquest/rpg/core/data/local/entity/EconomyEntity;", "", "id", "", "totalActionPoints", "", "availableActionPoints", "totalSpent", "currentStreak", "longestStreak", "lastCompletionDateMs", "(JIIIIILjava/lang/Long;)V", "getAvailableActionPoints", "()I", "getCurrentStreak", "getId", "()J", "getLastCompletionDateMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getLongestStreak", "getTotalActionPoints", "getTotalSpent", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(JIIIIILjava/lang/Long;)Lcom/fitquest/rpg/core/data/local/entity/EconomyEntity;", "equals", "", "other", "hashCode", "toDomain", "Lcom/fitquest/rpg/core/domain/model/Economy;", "toString", "", "app_debug"})
@androidx.room.Entity(tableName = "economy")
public final class EconomyEntity {
    @androidx.room.PrimaryKey()
    private final long id = 0L;
    private final int totalActionPoints = 0;
    private final int availableActionPoints = 0;
    private final int totalSpent = 0;
    private final int currentStreak = 0;
    private final int longestStreak = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long lastCompletionDateMs = null;
    
    public EconomyEntity(long id, int totalActionPoints, int availableActionPoints, int totalSpent, int currentStreak, int longestStreak, @org.jetbrains.annotations.Nullable()
    java.lang.Long lastCompletionDateMs) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final int getTotalActionPoints() {
        return 0;
    }
    
    public final int getAvailableActionPoints() {
        return 0;
    }
    
    public final int getTotalSpent() {
        return 0;
    }
    
    public final int getCurrentStreak() {
        return 0;
    }
    
    public final int getLongestStreak() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getLastCompletionDateMs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.Economy toDomain() {
        return null;
    }
    
    public EconomyEntity() {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.data.local.entity.EconomyEntity copy(long id, int totalActionPoints, int availableActionPoints, int totalSpent, int currentStreak, int longestStreak, @org.jetbrains.annotations.Nullable()
    java.lang.Long lastCompletionDateMs) {
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