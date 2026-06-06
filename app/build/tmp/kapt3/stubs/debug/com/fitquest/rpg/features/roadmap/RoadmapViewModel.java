package com.fitquest.rpg.features.roadmap;

import androidx.compose.animation.core.*;
import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.*;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.*;
import androidx.lifecycle.ViewModel;
import com.fitquest.rpg.core.data.repository.UserRepository;
import com.fitquest.rpg.core.domain.model.*;
import com.fitquest.rpg.ui.components.*;
import com.fitquest.rpg.ui.theme.*;
import com.fitquest.rpg.core.data.remote.SupabaseAuth;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001\fB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/fitquest/rpg/features/roadmap/RoadmapViewModel;", "Landroidx/lifecycle/ViewModel;", "userRepo", "Lcom/fitquest/rpg/core/data/repository/UserRepository;", "auth", "Lcom/fitquest/rpg/core/data/remote/SupabaseAuth;", "(Lcom/fitquest/rpg/core/data/repository/UserRepository;Lcom/fitquest/rpg/core/data/remote/SupabaseAuth;)V", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/fitquest/rpg/features/roadmap/RoadmapViewModel$RoadmapUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "RoadmapUiState", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RoadmapViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.repository.UserRepository userRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.remote.SupabaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.fitquest.rpg.features.roadmap.RoadmapViewModel.RoadmapUiState> uiState = null;
    
    @javax.inject.Inject()
    public RoadmapViewModel(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.repository.UserRepository userRepo, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.SupabaseAuth auth) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.fitquest.rpg.features.roadmap.RoadmapViewModel.RoadmapUiState> getUiState() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0018"}, d2 = {"Lcom/fitquest/rpg/features/roadmap/RoadmapViewModel$RoadmapUiState;", "", "overallRank", "Lcom/fitquest/rpg/core/domain/model/Rank;", "globalLevel", "", "loaded", "", "(Lcom/fitquest/rpg/core/domain/model/Rank;IZ)V", "getGlobalLevel", "()I", "getLoaded", "()Z", "getOverallRank", "()Lcom/fitquest/rpg/core/domain/model/Rank;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "app_debug"})
    public static final class RoadmapUiState {
        @org.jetbrains.annotations.NotNull()
        private final com.fitquest.rpg.core.domain.model.Rank overallRank = null;
        private final int globalLevel = 0;
        private final boolean loaded = false;
        
        public RoadmapUiState(@org.jetbrains.annotations.NotNull()
        com.fitquest.rpg.core.domain.model.Rank overallRank, int globalLevel, boolean loaded) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.fitquest.rpg.core.domain.model.Rank getOverallRank() {
            return null;
        }
        
        public final int getGlobalLevel() {
            return 0;
        }
        
        public final boolean getLoaded() {
            return false;
        }
        
        public RoadmapUiState() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.fitquest.rpg.core.domain.model.Rank component1() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final boolean component3() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.fitquest.rpg.features.roadmap.RoadmapViewModel.RoadmapUiState copy(@org.jetbrains.annotations.NotNull()
        com.fitquest.rpg.core.domain.model.Rank overallRank, int globalLevel, boolean loaded) {
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