package com.fitquest.rpg.features.onboarding;

import androidx.lifecycle.ViewModel;
import com.fitquest.rpg.core.data.repository.UserRepository;
import com.fitquest.rpg.core.domain.model.*;
import com.fitquest.rpg.core.data.remote.SupabaseAuth;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b/\b\u0086\b\u0018\u00002\u00020\u0001B\u00a7\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\u0002\u0010\u001bJ\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\u0016H\u00c6\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\t\u0010;\u001a\u00020\u001aH\u00c6\u0003J\t\u0010<\u001a\u00020\u0005H\u00c6\u0003J\t\u0010=\u001a\u00020\u0003H\u00c6\u0003J\t\u0010>\u001a\u00020\bH\u00c6\u0003J\t\u0010?\u001a\u00020\nH\u00c6\u0003J\t\u0010@\u001a\u00020\nH\u00c6\u0003J\t\u0010A\u001a\u00020\rH\u00c6\u0003J\t\u0010B\u001a\u00020\u000fH\u00c6\u0003J\t\u0010C\u001a\u00020\u0011H\u00c6\u0003J\u00ab\u0001\u0010D\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u00c6\u0001J\u0013\u0010E\u001a\u00020\u00162\b\u0010F\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010G\u001a\u00020\u0003H\u00d6\u0001J\t\u0010H\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0011\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010(R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010!R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001dR\u0011\u0010\u0018\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001dR\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001dR\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\'R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001dR\u0011\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00103\u00a8\u0006I"}, d2 = {"Lcom/fitquest/rpg/features/onboarding/OnboardingState;", "", "step", "", "name", "", "age", "gender", "Lcom/fitquest/rpg/core/domain/model/Gender;", "heightCm", "", "weightKg", "fitnessLevel", "Lcom/fitquest/rpg/core/domain/model/FitnessLevel;", "primaryGoal", "Lcom/fitquest/rpg/core/domain/model/FitnessGoal;", "dietaryStyle", "Lcom/fitquest/rpg/core/domain/model/DietaryStyle;", "workoutDaysPerWeek", "wakeTimeHour", "sleepTimeHour", "isSaving", "", "error", "transformationMonths", "workoutLocation", "Lcom/fitquest/rpg/core/domain/model/WorkoutLocation;", "(ILjava/lang/String;ILcom/fitquest/rpg/core/domain/model/Gender;FFLcom/fitquest/rpg/core/domain/model/FitnessLevel;Lcom/fitquest/rpg/core/domain/model/FitnessGoal;Lcom/fitquest/rpg/core/domain/model/DietaryStyle;IIIZLjava/lang/String;ILcom/fitquest/rpg/core/domain/model/WorkoutLocation;)V", "getAge", "()I", "getDietaryStyle", "()Lcom/fitquest/rpg/core/domain/model/DietaryStyle;", "getError", "()Ljava/lang/String;", "getFitnessLevel", "()Lcom/fitquest/rpg/core/domain/model/FitnessLevel;", "getGender", "()Lcom/fitquest/rpg/core/domain/model/Gender;", "getHeightCm", "()F", "()Z", "getName", "getPrimaryGoal", "()Lcom/fitquest/rpg/core/domain/model/FitnessGoal;", "getSleepTimeHour", "getStep", "getTransformationMonths", "getWakeTimeHour", "getWeightKg", "getWorkoutDaysPerWeek", "getWorkoutLocation", "()Lcom/fitquest/rpg/core/domain/model/WorkoutLocation;", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class OnboardingState {
    private final int step = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    private final int age = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.domain.model.Gender gender = null;
    private final float heightCm = 0.0F;
    private final float weightKg = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.domain.model.FitnessLevel fitnessLevel = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.domain.model.FitnessGoal primaryGoal = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.domain.model.DietaryStyle dietaryStyle = null;
    private final int workoutDaysPerWeek = 0;
    private final int wakeTimeHour = 0;
    private final int sleepTimeHour = 0;
    private final boolean isSaving = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    private final int transformationMonths = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.domain.model.WorkoutLocation workoutLocation = null;
    
    public OnboardingState(int step, @org.jetbrains.annotations.NotNull()
    java.lang.String name, int age, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Gender gender, float heightCm, float weightKg, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.FitnessLevel fitnessLevel, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.FitnessGoal primaryGoal, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.DietaryStyle dietaryStyle, int workoutDaysPerWeek, int wakeTimeHour, int sleepTimeHour, boolean isSaving, @org.jetbrains.annotations.Nullable()
    java.lang.String error, int transformationMonths, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.WorkoutLocation workoutLocation) {
        super();
    }
    
    public final int getStep() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    public final int getAge() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.Gender getGender() {
        return null;
    }
    
    public final float getHeightCm() {
        return 0.0F;
    }
    
    public final float getWeightKg() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.FitnessLevel getFitnessLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.FitnessGoal getPrimaryGoal() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.DietaryStyle getDietaryStyle() {
        return null;
    }
    
    public final int getWorkoutDaysPerWeek() {
        return 0;
    }
    
    public final int getWakeTimeHour() {
        return 0;
    }
    
    public final int getSleepTimeHour() {
        return 0;
    }
    
    public final boolean isSaving() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public final int getTransformationMonths() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.WorkoutLocation getWorkoutLocation() {
        return null;
    }
    
    public OnboardingState() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component10() {
        return 0;
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component14() {
        return null;
    }
    
    public final int component15() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.WorkoutLocation component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.Gender component4() {
        return null;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    public final float component6() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.FitnessLevel component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.FitnessGoal component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.DietaryStyle component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.features.onboarding.OnboardingState copy(int step, @org.jetbrains.annotations.NotNull()
    java.lang.String name, int age, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Gender gender, float heightCm, float weightKg, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.FitnessLevel fitnessLevel, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.FitnessGoal primaryGoal, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.DietaryStyle dietaryStyle, int workoutDaysPerWeek, int wakeTimeHour, int sleepTimeHour, boolean isSaving, @org.jetbrains.annotations.Nullable()
    java.lang.String error, int transformationMonths, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.WorkoutLocation workoutLocation) {
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