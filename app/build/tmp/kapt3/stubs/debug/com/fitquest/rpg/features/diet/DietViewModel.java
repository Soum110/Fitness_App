package com.fitquest.rpg.features.diet;

import androidx.lifecycle.ViewModel;
import com.fitquest.rpg.core.data.repository.UserRepository;
import com.fitquest.rpg.core.domain.model.*;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\f\u001a\u00020\rH\u0002R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/fitquest/rpg/features/diet/DietViewModel;", "Landroidx/lifecycle/ViewModel;", "userRepo", "Lcom/fitquest/rpg/core/data/repository/UserRepository;", "(Lcom/fitquest/rpg/core/data/repository/UserRepository;)V", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/fitquest/rpg/features/diet/DietUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "computeMacros", "Lcom/fitquest/rpg/features/diet/MacroTargets;", "profile", "Lcom/fitquest/rpg/core/domain/model/UserProfile;", "getDietTips", "", "", "getMealPlan", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DietViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.repository.UserRepository userRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.fitquest.rpg.features.diet.DietUiState> uiState = null;
    
    @javax.inject.Inject()
    public DietViewModel(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.repository.UserRepository userRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.fitquest.rpg.features.diet.DietUiState> getUiState() {
        return null;
    }
    
    private final com.fitquest.rpg.features.diet.MacroTargets computeMacros(com.fitquest.rpg.core.domain.model.UserProfile profile) {
        return null;
    }
    
    private final java.util.List<java.lang.String> getMealPlan(com.fitquest.rpg.core.domain.model.UserProfile profile) {
        return null;
    }
    
    private final java.util.List<java.lang.String> getDietTips(com.fitquest.rpg.core.domain.model.UserProfile profile) {
        return null;
    }
}