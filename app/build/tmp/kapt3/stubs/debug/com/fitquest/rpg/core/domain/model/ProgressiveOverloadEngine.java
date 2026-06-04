package com.fitquest.rpg.core.domain.model;

/**
 * Progressive Overload Engine.
 *
 * Follows a 4-week cycle:
 * Week 1: Base volume
 * Week 2: +5% reps (same sets)
 * Week 3: +1 set (same reps as week 1)
 * Week 4: Deload (–20% volume for recovery)
 * Each month, the base is incremented to ensure long-term progression.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\f"}, d2 = {"Lcom/fitquest/rpg/core/domain/model/ProgressiveOverloadEngine;", "", "()V", "computeVolume", "Lcom/fitquest/rpg/core/domain/model/ProgressiveOverloadEngine$WorkoutVolume;", "weekNumber", "", "baseSets", "baseReps", "weekPhaseDescription", "", "WorkoutVolume", "app_debug"})
public final class ProgressiveOverloadEngine {
    @org.jetbrains.annotations.NotNull()
    public static final com.fitquest.rpg.core.domain.model.ProgressiveOverloadEngine INSTANCE = null;
    
    private ProgressiveOverloadEngine() {
        super();
    }
    
    /**
     * Returns the target volume for a given exercise on a given training week (1-indexed).
     * @param weekNumber    The current training week since app start.
     * @param baseSets      The starting number of sets for this exercise.
     * @param baseReps      The starting number of reps for this exercise.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.ProgressiveOverloadEngine.WorkoutVolume computeVolume(int weekNumber, int baseSets, int baseReps) {
        return null;
    }
    
    /**
     * Returns a human-readable description of the current week's phase.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String weekPhaseDescription(int weekNumber) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u0015"}, d2 = {"Lcom/fitquest/rpg/core/domain/model/ProgressiveOverloadEngine$WorkoutVolume;", "", "sets", "", "reps", "restSeconds", "(III)V", "getReps", "()I", "getRestSeconds", "getSets", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
    public static final class WorkoutVolume {
        private final int sets = 0;
        private final int reps = 0;
        private final int restSeconds = 0;
        
        public WorkoutVolume(int sets, int reps, int restSeconds) {
            super();
        }
        
        public final int getSets() {
            return 0;
        }
        
        public final int getReps() {
            return 0;
        }
        
        public final int getRestSeconds() {
            return 0;
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int component3() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.fitquest.rpg.core.domain.model.ProgressiveOverloadEngine.WorkoutVolume copy(int sets, int reps, int restSeconds) {
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
}