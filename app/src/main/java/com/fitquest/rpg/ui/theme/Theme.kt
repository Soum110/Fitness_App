package com.fitquest.rpg.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ─── RPG Color Palette (Adapted to Vercel Style) ───────────────────────────

val DeepNavy       = Color(0xFF000000) // Pure Black Background
val DarkNavy       = Color(0xFF000000) // Pure Black Background
val CardNavy       = Color(0xFF0A0A0A) // Vercel Card Surface
val SurfaceNavy    = Color(0xFF121212) // Soft Black Surface
val BorderNavy     = Color(0xFF1F1F1F) // Vercel Border Gray

val NeonPurple     = Color(0xFFFFFFFF) // Primary action becomes White
val NeonPurpleLight= Color(0xFF888888) // Secondary text becomes cool Gray
val NeonBlue       = Color(0xFF0070F3) // Vercel Blue
val NeonGold       = Color(0xFFFFD700) // Sleek Gold
val NeonCyan       = Color(0xFF00E5FF)

val StrengthRed    = Color(0xFFEF5350)
val FlexGreen      = Color(0xFF66BB6A)
val StaminaBlue    = Color(0xFF42A5F5)
val EnergyYellow   = Color(0xFFFFEE58)
val IntelPurple    = Color(0xFFAB47BC)

val SuccessGreen   = Color(0xFF4CAF50)
val GoldAP         = Color(0xFFFFD700)

// ─── Dark RPG Color Scheme (Vercel Style) ───────────────────────────────────

val FitQuestDarkColorScheme = darkColorScheme(
    primary          = Color.White,
    onPrimary        = Color.Black,
    primaryContainer = Color(0xFF1A1A1A),
    onPrimaryContainer = Color.White,
    secondary        = Color(0xFF888888),
    onSecondary      = Color.White,
    secondaryContainer = Color(0xFF111111),
    onSecondaryContainer = Color(0xFF888888),
    tertiary         = NeonBlue,
    background       = Color.Black,
    onBackground     = Color(0xFFFAFAFA),
    surface          = CardNavy,
    onSurface        = Color(0xFFFAFAFA),
    surfaceVariant   = SurfaceNavy,
    onSurfaceVariant = Color(0xFF888888),
    outline          = BorderNavy,
    error            = Color(0xFFEF5350),
)

// ─── Typography ────────────────────────────────────────────────────────────

val FitQuestTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Black,
        fontSize = 57.sp,
        letterSpacing = (-1.5).sp
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        letterSpacing = (-1).sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = (-0.5).sp
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        letterSpacing = (-0.25).sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        letterSpacing = (-0.25).sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.sp
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
