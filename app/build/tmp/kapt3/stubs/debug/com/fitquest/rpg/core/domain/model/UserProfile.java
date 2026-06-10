package com.fitquest.rpg.core.domain.model;

/**
 * User profile created during onboarding.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0086\b\u0018\u00002\u00020\u0001B\u00a5\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u001b\u00a2\u0006\u0002\u0010\u001cJ\t\u0010?\u001a\u00020\u0003H\u00c6\u0003J\t\u0010@\u001a\u00020\u0007H\u00c6\u0003J\t\u0010A\u001a\u00020\u0007H\u00c6\u0003J\t\u0010B\u001a\u00020\u0007H\u00c6\u0003J\t\u0010C\u001a\u00020\u0017H\u00c6\u0003J\t\u0010D\u001a\u00020\u0003H\u00c6\u0003J\t\u0010E\u001a\u00020\u0007H\u00c6\u0003J\t\u0010F\u001a\u00020\u001bH\u00c6\u0003J\t\u0010G\u001a\u00020\u0005H\u00c6\u0003J\t\u0010H\u001a\u00020\u0007H\u00c6\u0003J\t\u0010I\u001a\u00020\tH\u00c6\u0003J\t\u0010J\u001a\u00020\u000bH\u00c6\u0003J\t\u0010K\u001a\u00020\u000bH\u00c6\u0003J\t\u0010L\u001a\u00020\u000eH\u00c6\u0003J\t\u0010M\u001a\u00020\u0010H\u00c6\u0003J\t\u0010N\u001a\u00020\u0012H\u00c6\u0003J\u00a9\u0001\u0010O\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u00c6\u0001J\u0013\u0010P\u001a\u00020\u00172\b\u0010Q\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010R\u001a\u00020\u0007H\u00d6\u0001J\t\u0010S\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b \u0010!R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010!R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0018\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010*R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u0015\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001eR\u0011\u00103\u001a\u00020\u00078F\u00a2\u0006\u0006\u001a\u0004\b4\u0010\u001eR\u0011\u0010\u0019\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001eR\u0011\u00106\u001a\u0002078F\u00a2\u0006\u0006\u001a\u0004\b8\u00109R\u0011\u0010\u0014\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010\u001eR\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010!R\u0011\u0010\u0013\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010\u001eR\u0011\u0010\u001a\u001a\u00020\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010>\u00a8\u0006T"}, d2 = {"Lcom/fitquest/rpg/core/domain/model/UserProfile;", "", "id", "", "name", "", "age", "", "gender", "Lcom/fitquest/rpg/core/domain/model/Gender;", "heightCm", "", "weightKg", "fitnessLevel", "Lcom/fitquest/rpg/core/domain/model/FitnessLevel;", "primaryGoal", "Lcom/fitquest/rpg/core/domain/model/FitnessGoal;", "dietaryStyle", "Lcom/fitquest/rpg/core/domain/model/DietaryStyle;", "workoutDaysPerWeek", "wakeTimeHour", "sleepTimeHour", "onboardingComplete", "", "joinDateMs", "transformationMonths", "workoutLocation", "Lcom/fitquest/rpg/core/domain/model/WorkoutLocation;", "(JLjava/lang/String;ILcom/fitquest/rpg/core/domain/model/Gender;FFLcom/fitquest/rpg/core/domain/model/FitnessLevel;Lcom/fitquest/rpg/core/domain/model/FitnessGoal;Lcom/fitquest/rpg/core/domain/model/DietaryStyle;IIIZJILcom/fitquest/rpg/core/domain/model/WorkoutLocation;)V", "getAge", "()I", "bmi", "getBmi", "()F", "getDietaryStyle", "()Lcom/fitquest/rpg/core/domain/model/DietaryStyle;", "getFitnessLevel", "()Lcom/fitquest/rpg/core/domain/model/FitnessLevel;", "getGender", "()Lcom/fitquest/rpg/core/domain/model/Gender;", "getHeightCm", "getId", "()J", "getJoinDateMs", "getName", "()Ljava/lang/String;", "getOnboardingComplete", "()Z", "getPrimaryGoal", "()Lcom/fitquest/rpg/core/domain/model/FitnessGoal;", "getSleepTimeHour", "trainingWeekNumber", "getTrainingWeekNumber", "getTransformationMonths", "transformationPhase", "Lcom/fitquest/rpg/core/domain/model/TransformationPhase;", "getTransformationPhase", "()Lcom/fitquest/rpg/core/domain/model/TransformationPhase;", "getWakeTimeHour", "getWeightKg", "getWorkoutDaysPerWeek", "getWorkoutLocation", "()Lcom/fitquest/rpg/core/domain/model/WorkoutLocation;", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class UserProfile {
    private final long id = 0L;
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
    private final boolean onboardingComplete = false;
    private final long joinDateMs = 0L;
    private final int transformationMonths = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.domain.model.WorkoutLocation workoutLocation = null;
    
    public UserProfile(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, int age, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Gender gender, float heightCm, float weightKg, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.FitnessLevel fitnessLevel, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.FitnessGoal primaryGoal, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.DietaryStyle dietaryStyle, int workoutDaysPerWeek, int wakeTimeHour, int sleepTimeHour, boolean onboardingComplete, long joinDateMs, int transformationMonths, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.WorkoutLocation workoutLocation) {
        super();
    }
    
    public final long getId() {
        return 0L;
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
    
    public final boolean getOnboardingComplete() {
        return false;
    }
    
    public final long getJoinDateMs() {
        return 0L;
    }
    
    public final int getTransformationMonths() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.WorkoutLocation getWorkoutLocation() {
        return null;
    }
    
    public final int getTrainingWeekNumber() {
        return 0;
    }
    
    public final float getBmi() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.TransformationPhase getTransformationPhase() {
        return null;
    }
    
    public UserProfile() {
        super();
    }
    
    public final long component1() {
        return 0L;
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
    
    public final long component14() {
        return 0L;
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
    public final com.fitquest.rpg.core.domain.model.UserProfile copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, int age, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Gender gender, float heightCm, float weightKg, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.FitnessLevel fitnessLevel, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.FitnessGoal primaryGoal, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.DietaryStyle dietaryStyle, int workoutDaysPerWeek, int wakeTimeHour, int sleepTimeHour, boolean onboardingComplete, long joinDateMs, int transformationMonths, @org.jetbrains.annotations.NotNull()
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