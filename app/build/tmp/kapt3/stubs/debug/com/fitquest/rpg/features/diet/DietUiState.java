package com.fitquest.rpg.features.diet;

import androidx.lifecycle.ViewModel;
import com.fitquest.rpg.core.data.repository.UserRepository;
import com.fitquest.rpg.core.domain.model.*;
import com.fitquest.rpg.core.data.remote.SupabaseAuth;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\fJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\nH\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003JI\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\bH\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010\u00a8\u0006\""}, d2 = {"Lcom/fitquest/rpg/features/diet/DietUiState;", "", "profile", "Lcom/fitquest/rpg/core/domain/model/UserProfile;", "phase", "Lcom/fitquest/rpg/core/domain/model/TransformationPhase;", "mealPlan", "", "", "macroTargets", "Lcom/fitquest/rpg/features/diet/MacroTargets;", "tips", "(Lcom/fitquest/rpg/core/domain/model/UserProfile;Lcom/fitquest/rpg/core/domain/model/TransformationPhase;Ljava/util/List;Lcom/fitquest/rpg/features/diet/MacroTargets;Ljava/util/List;)V", "getMacroTargets", "()Lcom/fitquest/rpg/features/diet/MacroTargets;", "getMealPlan", "()Ljava/util/List;", "getPhase", "()Lcom/fitquest/rpg/core/domain/model/TransformationPhase;", "getProfile", "()Lcom/fitquest/rpg/core/domain/model/UserProfile;", "getTips", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class DietUiState {
    @org.jetbrains.annotations.Nullable()
    private final com.fitquest.rpg.core.domain.model.UserProfile profile = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.domain.model.TransformationPhase phase = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> mealPlan = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.features.diet.MacroTargets macroTargets = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> tips = null;
    
    public DietUiState(@org.jetbrains.annotations.Nullable()
    com.fitquest.rpg.core.domain.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.TransformationPhase phase, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> mealPlan, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.features.diet.MacroTargets macroTargets, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> tips) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.fitquest.rpg.core.domain.model.UserProfile getProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.TransformationPhase getPhase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getMealPlan() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.features.diet.MacroTargets getMacroTargets() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTips() {
        return null;
    }
    
    public DietUiState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.fitquest.rpg.core.domain.model.UserProfile component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.TransformationPhase component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.features.diet.MacroTargets component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.features.diet.DietUiState copy(@org.jetbrains.annotations.Nullable()
    com.fitquest.rpg.core.domain.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.TransformationPhase phase, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> mealPlan, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.features.diet.MacroTargets macroTargets, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> tips) {
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