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
import java.util.Calendar;
import java.util.Locale;
import java.text.SimpleDateFormat;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/fitquest/rpg/features/dashboard/AnalysisPeriod;", "", "(Ljava/lang/String;I)V", "DAY", "WEEK", "MONTH", "app_debug"})
enum AnalysisPeriod {
    /*public static final*/ DAY /* = new DAY() */,
    /*public static final*/ WEEK /* = new WEEK() */,
    /*public static final*/ MONTH /* = new MONTH() */;
    
    AnalysisPeriod() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.fitquest.rpg.features.dashboard.AnalysisPeriod> getEntries() {
        return null;
    }
}