package com.fitquest.rpg.core.data.remote;

import com.google.gson.annotations.SerializedName;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0011JV\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u00c6\u0001\u00a2\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\u0005H\u00d6\u0001J\t\u0010%\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006&"}, d2 = {"Lcom/fitquest/rpg/core/data/remote/SupabaseEconomy;", "", "uid", "", "totalActionPoints", "", "availableActionPoints", "totalSpent", "currentStreak", "longestStreak", "lastCompletionDateMs", "", "(Ljava/lang/String;IIIIILjava/lang/Long;)V", "getAvailableActionPoints", "()I", "getCurrentStreak", "getLastCompletionDateMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getLongestStreak", "getTotalActionPoints", "getTotalSpent", "getUid", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;IIIIILjava/lang/Long;)Lcom/fitquest/rpg/core/data/remote/SupabaseEconomy;", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class SupabaseEconomy {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String uid = null;
    @com.google.gson.annotations.SerializedName(value = "total_action_points")
    private final int totalActionPoints = 0;
    @com.google.gson.annotations.SerializedName(value = "available_action_points")
    private final int availableActionPoints = 0;
    @com.google.gson.annotations.SerializedName(value = "total_spent")
    private final int totalSpent = 0;
    @com.google.gson.annotations.SerializedName(value = "current_streak")
    private final int currentStreak = 0;
    @com.google.gson.annotations.SerializedName(value = "longest_streak")
    private final int longestStreak = 0;
    @com.google.gson.annotations.SerializedName(value = "last_completion_date_ms")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long lastCompletionDateMs = null;
    
    public SupabaseEconomy(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, int totalActionPoints, int availableActionPoints, int totalSpent, int currentStreak, int longestStreak, @org.jetbrains.annotations.Nullable()
    java.lang.Long lastCompletionDateMs) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUid() {
        return null;
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
    public final java.lang.String component1() {
        return null;
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
    public final com.fitquest.rpg.core.data.remote.SupabaseEconomy copy(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, int totalActionPoints, int availableActionPoints, int totalSpent, int currentStreak, int longestStreak, @org.jetbrains.annotations.Nullable()
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