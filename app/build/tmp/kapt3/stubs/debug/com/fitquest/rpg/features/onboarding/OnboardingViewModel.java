package com.fitquest.rpg.features.onboarding;

import androidx.lifecycle.ViewModel;
import com.fitquest.rpg.core.data.repository.UserRepository;
import com.fitquest.rpg.core.domain.model.*;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013J\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0015\u001a\u00020\u0011J\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\rJ\u000e\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020!J\u000e\u0010\"\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\rJ\u000e\u0010%\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\rJ\u000e\u0010&\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020!J\u000e\u0010\'\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/fitquest/rpg/features/onboarding/OnboardingViewModel;", "Landroidx/lifecycle/ViewModel;", "userRepo", "Lcom/fitquest/rpg/core/data/repository/UserRepository;", "(Lcom/fitquest/rpg/core/data/repository/UserRepository;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/fitquest/rpg/features/onboarding/OnboardingState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "totalSteps", "", "getTotalSteps", "()I", "finishOnboarding", "", "onComplete", "Lkotlin/Function0;", "nextStep", "prevStep", "updateAge", "v", "updateDiet", "Lcom/fitquest/rpg/core/domain/model/DietaryStyle;", "updateFitnessLevel", "Lcom/fitquest/rpg/core/domain/model/FitnessLevel;", "updateGender", "Lcom/fitquest/rpg/core/domain/model/Gender;", "updateGoal", "Lcom/fitquest/rpg/core/domain/model/FitnessGoal;", "updateHeight", "", "updateName", "", "updateSleepTime", "updateWakeTime", "updateWeight", "updateWorkoutDays", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class OnboardingViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.repository.UserRepository userRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.fitquest.rpg.features.onboarding.OnboardingState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.fitquest.rpg.features.onboarding.OnboardingState> state = null;
    private final int totalSteps = 5;
    
    @javax.inject.Inject()
    public OnboardingViewModel(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.repository.UserRepository userRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.fitquest.rpg.features.onboarding.OnboardingState> getState() {
        return null;
    }
    
    public final int getTotalSteps() {
        return 0;
    }
    
    public final void nextStep() {
    }
    
    public final void prevStep() {
    }
    
    public final void updateName(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void updateAge(int v) {
    }
    
    public final void updateGender(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Gender v) {
    }
    
    public final void updateHeight(float v) {
    }
    
    public final void updateWeight(float v) {
    }
    
    public final void updateFitnessLevel(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.FitnessLevel v) {
    }
    
    public final void updateGoal(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.FitnessGoal v) {
    }
    
    public final void updateDiet(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.DietaryStyle v) {
    }
    
    public final void updateWorkoutDays(int v) {
    }
    
    public final void updateWakeTime(int v) {
    }
    
    public final void updateSleepTime(int v) {
    }
    
    public final void finishOnboarding(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete) {
    }
}