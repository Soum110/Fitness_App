package com.fitquest.rpg.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ─── RPG Color Palette ─────────────────────────────────────────────────────

val DeepNavy       = Color(0xFF080C1A)
val DarkNavy       = Color(0xFF0D1225)
val CardNavy       = Color(0xFF141A30)
val SurfaceNavy    = Color(0xFF1A2240)
val BorderNavy     = Color(0xFF252D45)

val NeonPurple     = Color(0xFF9C27B0)
val NeonPurpleLight= Color(0xFFCE93D8)
val NeonBlue       = Color(0xFF3D5AFE)
val NeonGold       = Color(0xFFFFD54F)
val NeonCyan       = Color(0xFF00E5FF)

val StrengthRed    = Color(0xFFEF5350)
val FlexGreen      = Color(0xFF66BB6A)
val StaminaBlue    = Color(0xFF42A5F5)
val EnergyYellow   = Color(0xFFFFEE58)
val IntelPurple    = Color(0xFFAB47BC)

val SuccessGreen   = Color(0xFF4CAF50)
val GoldAP         = Color(0xFFFFD700)

// ─── Dark RPG Color Scheme ─────────────────────────────────────────────────

val FitQuestDarkColorScheme = darkColorScheme(
    primary          = NeonPurple,
    onPrimary        = Color.White,
    primaryContainer = Color(0xFF4A0080),
    onPrimaryContainer = NeonPurpleLight,
    secondary        = NeonGold,
    onSecondary      = DeepNavy,
    secondaryContainer = Color(0xFF5C4800),
    onSecondaryContainer = NeonGold,
    tertiary         = NeonCyan,
    background       = DeepNavy,
    onBackground     = Color(0xFFE8EAFF),
    surface          = CardNavy,
    onSurface        = Color(0xFFE8EAFF),
    surfaceVariant   = SurfaceNavy,
    onSurfaceVariant = Color(0xFFB0BEC5),
    outline          = BorderNavy,
    error            = Color(0xFFCF6679),
)

// ─── Typography ────────────────────────────────────────────────────────────

val FitQuestTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Black,
        fontSize = 57.sp,
        letterSpacing = (-0.25).sp
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 0.5.sp
    )
)

// ─── Theme ─────────────────────────────────────────────────────────────────

@Composable
fun FitQuestTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = FitQuestDarkColorScheme,
        typography = FitQuestTypography,
        content = content
    )
}
