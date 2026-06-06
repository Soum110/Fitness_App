package com.fitquest.rpg.features.profile;

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
import com.fitquest.rpg.core.data.repository.TaskRepository;
import com.fitquest.rpg.core.data.repository.RewardCardRepository;
import com.fitquest.rpg.core.data.remote.FirestoreRepository;
import com.fitquest.rpg.core.data.local.FitQuestDatabase;
import com.fitquest.rpg.core.domain.model.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;
import com.fitquest.rpg.ui.components.*;
import com.fitquest.rpg.ui.theme.*;
import android.widget.Toast;
import kotlinx.coroutines.Dispatchers;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ(\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00150\u0019R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/fitquest/rpg/features/profile/ProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "userRepo", "Lcom/fitquest/rpg/core/data/repository/UserRepository;", "taskRepo", "Lcom/fitquest/rpg/core/data/repository/TaskRepository;", "rewardCardRepo", "Lcom/fitquest/rpg/core/data/repository/RewardCardRepository;", "firestoreRepo", "Lcom/fitquest/rpg/core/data/remote/FirestoreRepository;", "db", "Lcom/fitquest/rpg/core/data/local/FitQuestDatabase;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "(Lcom/fitquest/rpg/core/data/repository/UserRepository;Lcom/fitquest/rpg/core/data/repository/TaskRepository;Lcom/fitquest/rpg/core/data/repository/RewardCardRepository;Lcom/fitquest/rpg/core/data/remote/FirestoreRepository;Lcom/fitquest/rpg/core/data/local/FitQuestDatabase;Lcom/google/firebase/auth/FirebaseAuth;)V", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/fitquest/rpg/features/profile/ProfileUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteAccount", "", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.repository.UserRepository userRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.repository.TaskRepository taskRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.repository.RewardCardRepository rewardCardRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.remote.FirestoreRepository firestoreRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.fitquest.rpg.core.data.local.FitQuestDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.fitquest.rpg.features.profile.ProfileUiState> uiState = null;
    
    @javax.inject.Inject()
    public ProfileViewModel(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.repository.UserRepository userRepo, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.repository.TaskRepository taskRepo, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.repository.RewardCardRepository rewardCardRepo, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.remote.FirestoreRepository firestoreRepo, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.data.local.FitQuestDatabase db, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.fitquest.rpg.features.profile.ProfileUiState> getUiState() {
        return null;
    }
    
    public final void deleteAccount(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onFailure) {
    }
}