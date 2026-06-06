package com.fitquest.rpg.features.dashboard;

import androidx.compose.animation.*;
import androidx.compose.animation.core.*;
import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.lazy.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.*;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.*;
import com.fitquest.rpg.core.domain.model.*;
import com.fitquest.rpg.ui.components.*;
import com.fitquest.rpg.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a2\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a,\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a.\u0010\u000e\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007\u001a&\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a\b\u0010\u0016\u001a\u00020\u0001H\u0003\u001a\u0018\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0003\u001a\u0010\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001dH\u0003\u001a\b\u0010\u001e\u001a\u00020\u001dH\u0002\u00a8\u0006\u001f"}, d2 = {"AttributesSection", "", "attributes", "", "Lcom/fitquest/rpg/core/domain/model/Attribute;", "todaysTasks", "Lcom/fitquest/rpg/core/domain/model/DailyTask;", "onViewAll", "Lkotlin/Function0;", "DashboardHeader", "state", "Lcom/fitquest/rpg/features/dashboard/DashboardUiState;", "onNavigateToStore", "onNavigateToAttributes", "DashboardScreen", "viewModel", "Lcom/fitquest/rpg/features/dashboard/DashboardViewModel;", "ModularAttributeCard", "attribute", "potentialXp", "", "onClick", "MotivationalQuote", "QuestTrackerCard", "done", "", "total", "WeekPhaseBanner", "description", "", "greeting", "app_debug"})
public final class DashboardScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToStore, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToAttributes, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.features.dashboard.DashboardViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DashboardHeader(com.fitquest.rpg.features.dashboard.DashboardUiState state, kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToStore, kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateToAttributes) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AttributesSection(java.util.List<com.fitquest.rpg.core.domain.model.Attribute> attributes, java.util.List<com.fitquest.rpg.core.domain.model.DailyTask> todaysTasks, kotlin.jvm.functions.Function0<kotlin.Unit> onViewAll) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ModularAttributeCard(com.fitquest.rpg.core.domain.model.Attribute attribute, long potentialXp, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void WeekPhaseBanner(java.lang.String description) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QuestTrackerCard(int done, int total) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MotivationalQuote() {
    }
    
    private static final java.lang.String greeting() {
        return null;
    }
}