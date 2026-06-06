package com.fitquest.rpg.features.diet;

import androidx.compose.animation.core.*;
import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.*;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.*;
import com.fitquest.rpg.core.domain.model.TransformationPhase;
import com.fitquest.rpg.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a<\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000e\u001a*\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012\u001a,\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a@\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00010 H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b!\u0010\"\u001a\u0010\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u0006H\u0003\u001a\u0010\u0010%\u001a\u00020&2\u0006\u0010\u001a\u001a\u00020\u0006H\u0002\u001a\u0010\u0010\'\u001a\u00020(2\u0006\u0010$\u001a\u00020\u0006H\u0002\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006)"}, d2 = {"DietScreen", "", "viewModel", "Lcom/fitquest/rpg/features/diet/DietViewModel;", "MacroColChip", "label", "", "value", "kcal", "color", "Landroidx/compose/ui/graphics/Color;", "modifier", "Landroidx/compose/ui/Modifier;", "MacroColChip-42QJj7c", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLandroidx/compose/ui/Modifier;)V", "MacroLegend", "percent", "MacroLegend-mxwnekA", "(Ljava/lang/String;Ljava/lang/String;J)V", "MacroTargetsCard", "macros", "Lcom/fitquest/rpg/features/diet/MacroTargets;", "phaseColor", "MacroTargetsCard-bw27NRU", "(Lcom/fitquest/rpg/features/diet/MacroTargets;JLandroidx/compose/ui/Modifier;)V", "MealCard", "meal", "index", "", "isConsumed", "", "onToggleConsume", "Lkotlin/Function0;", "MealCard-XO-JAsU", "(Ljava/lang/String;IJZLkotlin/jvm/functions/Function0;)V", "TipCard", "tip", "parseMeal", "Lcom/fitquest/rpg/features/diet/ParsedMeal;", "parseTip", "Lcom/fitquest/rpg/features/diet/ParsedTip;", "app_debug"})
public final class DietScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void DietScreen(@org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.features.diet.DietViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TipCard(java.lang.String tip) {
    }
    
    private static final com.fitquest.rpg.features.diet.ParsedMeal parseMeal(java.lang.String meal) {
        return null;
    }
    
    private static final com.fitquest.rpg.features.diet.ParsedTip parseTip(java.lang.String tip) {
        return null;
    }
}