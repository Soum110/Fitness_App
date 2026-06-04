package com.fitquest.rpg.features.dashboard;

import androidx.compose.animation.*;
import androidx.compose.animation.core.*;
import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.*;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.*;
import com.fitquest.rpg.core.domain.model.*;
import com.fitquest.rpg.ui.components.*;
import com.fitquest.rpg.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0003\u001a \u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0003\u001a\u001e\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0003\u001a.\u0010\u0011\u001a\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007\u001a\u0012\u0010\u0015\u001a\u00020\u00012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0003\u001a\u0010\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001aH\u0003\u001a\b\u0010\u001b\u001a\u00020\u001aH\u0002\u00a8\u0006\u001c"}, d2 = {"AttributesSection", "", "attributes", "", "Lcom/fitquest/rpg/core/domain/model/Attribute;", "onViewAll", "Lkotlin/Function0;", "DailyProgressRing", "progress", "", "done", "", "total", "DashboardHeader", "state", "Lcom/fitquest/rpg/features/dashboard/DashboardUiState;", "onNavigateToStore", "DashboardScreen", "onNavigateToAttributes", "viewModel", "Lcom/fitquest/rpg/features/dashboard/DashboardViewModel;", "MotivationalQuote", "profile", "Lcom/fitquest/rpg/core/domain/model/UserProfile;", "WeekPhaseBanner", "description", "", "greeting", "app_debug"})
public final class DashboardScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToStore, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToAttributes, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.features.dashboard.DashboardViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DashboardHeader(com.fitquest.rpg.features.dashboard.DashboardUiState state, kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToStore) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AttributesSection(java.util.List<com.fitquest.rpg.core.domain.model.Attribute> attributes, kotlin.jvm.functions.Function0<kotlin.Unit> onViewAll) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void WeekPhaseBanner(java.lang.String description) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DailyProgressRing(float progress, int done, int total) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MotivationalQuote(com.fitquest.rpg.core.domain.model.UserProfile profile) {
    }
    
    private static final java.lang.String greeting() {
        return null;
    }
}