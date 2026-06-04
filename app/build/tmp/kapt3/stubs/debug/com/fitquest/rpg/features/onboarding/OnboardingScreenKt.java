package com.fitquest.rpg.features.onboarding;

import androidx.compose.animation.*;
import androidx.compose.animation.core.*;
import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.*;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.*;
import com.fitquest.rpg.core.domain.model.*;
import com.fitquest.rpg.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\u001a<\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a \u0010\n\u001a\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u001a&\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\u0003\u001a\u0018\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0003\u001a\u0018\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000eH\u0003\u001a\u0018\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000eH\u0003\u001a\u0018\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000eH\u0003\u001a\u0018\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000eH\u0003\u001a\u0018\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000eH\u0003\u001a\u0010\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001fH\u0002\u00a8\u0006 "}, d2 = {"LabeledSlider", "", "label", "", "value", "", "min", "max", "onChanged", "Lkotlin/Function1;", "OnboardingScreen", "onComplete", "Lkotlin/Function0;", "viewModel", "Lcom/fitquest/rpg/features/onboarding/OnboardingViewModel;", "OptionCard", "selected", "", "title", "onClick", "ProfileSummaryRow", "Step0Welcome", "state", "Lcom/fitquest/rpg/features/onboarding/OnboardingState;", "vm", "Step1PhysicalStats", "Step2Goals", "Step3Diet", "Step4Schedule", "formatHour", "hour", "", "app_debug"})
public final class OnboardingScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void OnboardingScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete, @org.jetbrains.annotations.NotNull()
    com.fitquest.rpg.features.onboarding.OnboardingViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void Step0Welcome(com.fitquest.rpg.features.onboarding.OnboardingState state, com.fitquest.rpg.features.onboarding.OnboardingViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void Step1PhysicalStats(com.fitquest.rpg.features.onboarding.OnboardingState state, com.fitquest.rpg.features.onboarding.OnboardingViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void Step2Goals(com.fitquest.rpg.features.onboarding.OnboardingState state, com.fitquest.rpg.features.onboarding.OnboardingViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void Step3Diet(com.fitquest.rpg.features.onboarding.OnboardingState state, com.fitquest.rpg.features.onboarding.OnboardingViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void Step4Schedule(com.fitquest.rpg.features.onboarding.OnboardingState state, com.fitquest.rpg.features.onboarding.OnboardingViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LabeledSlider(java.lang.String label, float value, float min, float max, kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> onChanged) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void OptionCard(boolean selected, java.lang.String title, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ProfileSummaryRow(java.lang.String label, java.lang.String value) {
    }
    
    private static final java.lang.String formatHour(int hour) {
        return null;
    }
}