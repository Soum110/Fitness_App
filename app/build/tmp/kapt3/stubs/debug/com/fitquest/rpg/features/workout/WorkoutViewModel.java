package com.fitquest.rpg.features.workout;

import androidx.compose.animation.*;
import androidx.compose.animation.core.*;
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
import com.fitquest.rpg.core.data.repository.TaskRepository;
import com.fitquest.rpg.core.data.repository.UserRepository;
import com.fitquest.rpg.core.domain.model.*;
import com.fitquest.rpg.ui.theme.*;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;
import com.google.firebase.auth.FirebaseAuth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/fitquest/rpg/features/workout/WorkoutViewModel;", "Landroidx/lifecycle/ViewModel;", "taskRepo", "Lcom/fitquest/rpg/core/data/repository/TaskRepository;", "userRepo", "Lcom/fitquest/rpg/core/data/repository/UserRepository;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "(Lcom/fitquest/rpg/core/data/repository/TaskRepository;Lcom/fitquest/rpg/core/data/repository/UserRepository;Lcom/google/firebase/auth/FirebaseAuth;)V", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/fitquest/rpg/features/workout/WorkoutUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class WorkoutViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.repository.TaskRepository taskRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.repository.UserRepository userRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.fitquest.rpg.features.workout.WorkoutUiState> uiState = null;
    
    @javax.inject.Inject()
    public WorkoutViewModel(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.repository.TaskRepository taskRepo, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.repository.UserRepository userRepo, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.fitquest.rpg.features.workout.WorkoutUiState> getUiState() {
        return null;
    }
}