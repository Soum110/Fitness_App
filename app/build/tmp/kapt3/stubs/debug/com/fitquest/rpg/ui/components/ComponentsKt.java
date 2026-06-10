package com.fitquest.rpg.ui.components;

import androidx.compose.animation.core.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.*;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.*;
import com.fitquest.rpg.core.domain.model.*;
import com.fitquest.rpg.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000v\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a.\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\u001a,\u0010\r\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007\u001a0\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a\u001a\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001aV\u0010\u001a\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00142\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001d2\u001c\u0010\u001e\u001a\u0018\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00010\u001f\u00a2\u0006\u0002\b!\u00a2\u0006\u0002\b\"H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010$\u001a\u001a\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a(\u0010\'\u001a\u00020\u00012\u0006\u0010(\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00010\u001d2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00032\u0006\u0010.\u001a\u00020,H\u0007\u001a\n\u0010/\u001a\u00020\u0003*\u000200\u001a\n\u0010/\u001a\u00020\u0003*\u00020\u0019\u001a\n\u0010/\u001a\u00020\u0003*\u000201\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00062"}, d2 = {"ActionPointsChip", "", "points", "", "modifier", "Landroidx/compose/ui/Modifier;", "AttributeXpRing", "attribute", "Lcom/fitquest/rpg/core/domain/model/Attribute;", "size", "Landroidx/compose/ui/unit/Dp;", "AttributeXpRing-wH6b6FI", "(Lcom/fitquest/rpg/core/domain/model/Attribute;Landroidx/compose/ui/Modifier;F)V", "AttributeXpSlider", "potentialXp", "", "showName", "", "FitQuestLoadingSpinner", "accentColor", "Landroidx/compose/ui/graphics/Color;", "FitQuestLoadingSpinner-H2RKhps", "(Landroidx/compose/ui/Modifier;FJ)V", "RankBadge", "rank", "Lcom/fitquest/rpg/core/domain/model/Rank;", "RpgCard", "glowColor", "onClick", "Lkotlin/Function0;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "RpgCard-RPmYEkk", "(Landroidx/compose/ui/Modifier;JLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "StreakBadge", "streak", "TaskItem", "task", "Lcom/fitquest/rpg/core/domain/model/DailyTask;", "onComplete", "rememberProgressFraction", "", "level", "fraction", "iconResId", "Lcom/fitquest/rpg/core/domain/model/AttributeType;", "Lcom/fitquest/rpg/core/domain/model/TaskType;", "app_debug"})
public final class ComponentsKt {
    
    /**
     * Rank badge with clean Vercel border and flat background.
     */
    @androidx.compose.runtime.Composable()
    public static final void RankBadge(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Rank rank, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Task list item with Vercel styling and clean completion.
     */
    @androidx.compose.runtime.Composable()
    public static final void TaskItem(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.DailyTask task, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Action Points chip display.
     */
    @androidx.compose.runtime.Composable()
    public static final void ActionPointsChip(int points, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StreakBadge(int streak, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    /**
     * Helper to animate progress forward and skip backward visual demotion on level up.
     */
    @androidx.compose.runtime.Composable()
    public static final float rememberProgressFraction(int level, float fraction) {
        return 0.0F;
    }
    
    /**
     * Horizontal XP slider representing an attribute, highlighting potential XP increase from today's active tasks.
     */
    @androidx.compose.runtime.Composable()
    public static final void AttributeXpSlider(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Attribute attribute, long potentialXp, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, boolean showName) {
    }
    
    public static final int iconResId(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.AttributeType $this$iconResId) {
        return 0;
    }
    
    public static final int iconResId(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.TaskType $this$iconResId) {
        return 0;
    }
    
    public static final int iconResId(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.core.domain.model.Rank $this$iconResId) {
        return 0;
    }
}