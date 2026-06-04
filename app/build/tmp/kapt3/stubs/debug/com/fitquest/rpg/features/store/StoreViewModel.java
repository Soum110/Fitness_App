package com.fitquest.rpg.features.store;

import androidx.lifecycle.ViewModel;
import com.fitquest.rpg.core.data.repository.*;
import com.fitquest.rpg.core.domain.model.*;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J&\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u000fJ\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u000fJ\u000e\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001c\u001a\u00020\u000fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/fitquest/rpg/features/store/StoreViewModel;", "Landroidx/lifecycle/ViewModel;", "cardRepo", "Lcom/fitquest/rpg/core/data/repository/RewardCardRepository;", "userRepo", "Lcom/fitquest/rpg/core/data/repository/UserRepository;", "(Lcom/fitquest/rpg/core/data/repository/RewardCardRepository;Lcom/fitquest/rpg/core/data/repository/UserRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/fitquest/rpg/features/store/StoreUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addCustomCard", "", "title", "", "description", "emoji", "apCost", "", "clearMessages", "deleteCard", "card", "Lcom/fitquest/rpg/core/domain/model/RewardCard;", "hideAddDialog", "redeemCard", "showAddDialog", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class StoreViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.repository.RewardCardRepository cardRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.repository.UserRepository userRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.fitquest.rpg.features.store.StoreUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.fitquest.rpg.features.store.StoreUiState> uiState = null;
    
    @javax.inject.Inject()
    public StoreViewModel(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.repository.RewardCardRepository cardRepo, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.repository.UserRepository userRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.fitquest.rpg.features.store.StoreUiState> getUiState() {
        return null;
    }
    
    public final void redeemCard(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.RewardCard card) {
    }
    
    public final void addCustomCard(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String emoji, int apCost) {
    }
    
    public final void deleteCard(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.RewardCard card) {
    }
    
    public final void showAddDialog() {
    }
    
    public final void hideAddDialog() {
    }
    
    public final void clearMessages() {
    }
}