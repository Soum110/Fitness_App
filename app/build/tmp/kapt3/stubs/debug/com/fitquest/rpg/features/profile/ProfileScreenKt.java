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
import com.fitquest.rpg.core.data.remote.SupabaseRepository;
import com.fitquest.rpg.core.data.local.FitQuestDatabase;
import com.fitquest.rpg.core.domain.model.*;
import com.fitquest.rpg.core.data.remote.SupabaseAuth;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;
import com.fitquest.rpg.ui.components.*;
import com.fitquest.rpg.ui.theme.*;
import android.widget.Toast;
import kotlinx.coroutines.Dispatchers;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a6\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a\"\u0010\u000b\u001a\u00020\u00012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a6\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a@\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001a"}, d2 = {"CharacterRow", "", "label", "", "value", "valueColor", "Landroidx/compose/ui/graphics/Color;", "iconResId", "", "CharacterRow-9LQNqLg", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/Integer;)V", "ProfileScreen", "onLogout", "Lkotlin/Function0;", "viewModel", "Lcom/fitquest/rpg/features/profile/ProfileViewModel;", "SpecTile", "accentColor", "modifier", "Landroidx/compose/ui/Modifier;", "SpecTile-9LQNqLg", "(Ljava/lang/String;Ljava/lang/String;JLandroidx/compose/ui/Modifier;)V", "StatCard", "color", "StatCard-XO-JAsU", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/Integer;Landroidx/compose/ui/Modifier;)V", "app_debug"})
public final class ProfileScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void ProfileScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onLogout, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.features.profile.ProfileViewModel viewModel) {
    }
}