package com.fitquest.rpg.features.profile;

import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.*;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.*;
import androidx.lifecycle.ViewModel;
import com.fitquest.rpg.core.data.repository.UserRepository;
import com.fitquest.rpg.core.domain.model.*;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;
import com.fitquest.rpg.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\bH\u00c6\u0003J/\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lcom/fitquest/rpg/features/profile/ProfileUiState;", "", "profile", "Lcom/fitquest/rpg/core/domain/model/UserProfile;", "attributes", "", "Lcom/fitquest/rpg/core/domain/model/Attribute;", "economy", "Lcom/fitquest/rpg/core/domain/model/Economy;", "(Lcom/fitquest/rpg/core/domain/model/UserProfile;Ljava/util/List;Lcom/fitquest/rpg/core/domain/model/Economy;)V", "getAttributes", "()Ljava/util/List;", "getEconomy", "()Lcom/fitquest/rpg/core/domain/model/Economy;", "getProfile", "()Lcom/fitquest/rpg/core/domain/model/UserProfile;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class ProfileUiState {
    @org.jetbrains.annotations.Nullable()
    private final com.fitquest.rpg.core.domain.model.UserProfile profile = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.fitquest.rpg.core.domain.model.Attribute> attributes = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.domain.model.Economy economy = null;
    
    public ProfileUiState(@org.jetbrains.annotations.Nullable()
    com.fitquest.rpg.core.domain.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.domain.model.Attribute> attributes, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Economy economy) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.fitquest.rpg.core.domain.model.UserProfile getProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.fitquest.rpg.core.domain.model.Attribute> getAttributes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.Economy getEconomy() {
        return null;
    }
    
    public ProfileUiState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.fitquest.rpg.core.domain.model.UserProfile component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.fitquest.rpg.core.domain.model.Attribute> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.core.domain.model.Economy component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.fitquest.rpg.features.profile.ProfileUiState copy(@org.jetbrains.annotations.Nullable()
    com.fitquest.rpg.core.domain.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    java.util.List<com.fitquest.rpg.core.domain.model.Attribute> attributes, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Economy economy) {
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