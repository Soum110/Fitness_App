package com.fitquest.rpg.ui.components;

import android.content.Context;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.shape.GenericShape;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.*;
import androidx.compose.ui.graphics.*;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.PopupProperties;
import androidx.compose.ui.graphics.CompositingStrategy;
import com.fitquest.rpg.ui.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bJ\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/fitquest/rpg/ui/components/TutorialManager;", "", "()V", "PREFS_NAME", "", "isTutorialCompleted", "", "context", "Landroid/content/Context;", "screenName", "resetAllTutorials", "", "setTutorialCompleted", "completed", "app_debug"})
public final class TutorialManager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "fitquest_tutorial_prefs";
    @org.jetbrains.annotations.NotNull()
    public static final com.fitquest.rpg.ui.components.TutorialManager INSTANCE = null;
    
    private TutorialManager() {
        super();
    }
    
    public final boolean isTutorialCompleted(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String screenName) {
        return false;
    }
    
    public final void setTutorialCompleted(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String screenName, boolean completed) {
    }
    
    public final void resetAllTutorials(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}