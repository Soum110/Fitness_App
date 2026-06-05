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
import com.google.firebase.auth.FirebaseAuth;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002\u00a8\u0006\n"}, d2 = {"RoadmapScreen", "", "onBack", "Lkotlin/Function0;", "viewModel", "Lcom/fitquest/rpg/features/roadmap/RoadmapViewModel;", "getRankDesc", "", "rank", "Lcom/fitquest/rpg/core/domain/model/Rank;", "app_debug"})
public final class RoadmapScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void RoadmapScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.features.roadmap.RoadmapViewModel viewModel) {
    }
    
    private static final java.lang.String getRankDesc(com.fitquest.rpg.core.domain.model.Rank rank) {
        return null;
    }
}