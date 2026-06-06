package com.fitquest.rpg.features.attributes

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.fitquest.rpg.core.domain.model.*
import com.fitquest.rpg.ui.components.*
import com.fitquest.rpg.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttributesScreen(
    onBack: () -> Unit,
    onNavigateToRoadmap: () -> Unit,
    viewModel: AttributesViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val overallRank = state.overallRank ?: Rank.BRONZE_RECRUIT
    val rankColor = Color(overallRank.colorHex)

    // Upgrade rim glow animation state
    var lastLevel by remember { mutableStateOf<Int?>(null) }
    var showGlow by remember { mutableStateOf(false) }
    val glowAlpha = remember { Animatable(0f) }

    LaunchedEffect(state.globalLevel) {
        val prev = lastLevel
        lastLevel = state.globalLevel
        if (prev != null && state.globalLevel > prev) {
            showGlow = true
            repeat(4) {
                glowAlpha.animateTo(1f, tween(400, easing = FastOutSlowInEasing))
                glowAlpha.animateTo(0.2f, tween(400, easing = FastOutSlowInEasing))
            }
            glowAlpha.animateTo(0f, tween(400, easing = FastOutSlowInEasing))
            showGlow = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(containerColor = Color.Black) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("ATTRIBUTES", style = MaterialTheme.typography.headlineLarge, letterSpacing = 2.sp)
                        Text("Track your growth across all dimensions.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                // Symmetrical Tier Emblem & Constant Fill Global Level Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(130.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Left Box: Symmetrical Tier Symbol/SVG (Tapping opens the roadmap)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(8.dp))
                            .background(CardNavy)
                            .border(1.dp, BorderNavy, RoundedCornerShape(8.dp))
                            .clickable { onNavigateToRoadmap() }
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = overallRank.iconResId()),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = overallRank.title.uppercase(),
                                style = MaterialTheme.typography.labelSmall,
                                color = rankColor,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    // Right Box: Constant Filling Global Level
                    val animatedProgress by animateFloatAsState(
                        targetValue = state.globalProgressFraction,
                        animationSpec = tween(1000, easing = FastOutSlowInEasing),
                        label = "globalXpProgress"
                    )

                    Box(
                        modifier = Modifier
                            .weight(1.2f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(8.dp))
                            .background(CardNavy)
                            .border(1.dp, BorderNavy, RoundedCornerShape(8.dp))
                    ) {
                        // Constant filling background container
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(animatedProgress)
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(rankColor.copy(alpha = 0.15f), rankColor.copy(alpha = 0.35f))
                                    )
                                )
                        )

                        // Level Texts
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "GLOBAL LEVEL",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF888888),
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "${state.globalLevel}",
                                style = MaterialTheme.typography.displayMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Black
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = "${(state.globalProgressFraction * 100).toInt()}% XP",
                                style = MaterialTheme.typography.labelSmall,
                                color = rankColor,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                // Detailed attribute cards
                Text(
                    "ATTRIBUTE DETAILS",
                    style = MaterialTheme.typography.labelLarge,
                    letterSpacing = 3.sp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(Modifier.height(12.dp))

                state.attributes.forEach { attribute ->
                    DetailedAttributeCard(attribute = attribute, modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp))
                }

                Spacer(Modifier.height(80.dp))
            }
        }

        // Phone rim upgrade glow overlay
        if (showGlow && glowAlpha.value > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 8.dp,
                        brush = Brush.sweepGradient(
                            colors = listOf(
                                rankColor.copy(alpha = glowAlpha.value),
                                Color.White.copy(alpha = glowAlpha.value),
                                rankColor.copy(alpha = glowAlpha.value)
                            )
                        ),
                        shape = RectangleShape
                    )
            )
        }
    }
}

@Composable
private fun DetailedAttributeCard(attribute: Attribute, modifier: Modifier = Modifier) {
    val attrColor = Color(attribute.type.color)
    val animProgress = rememberProgressFraction(
        level = attribute.level,
        fraction = attribute.progressFraction
    )

    // Pulsating glow based on level (pulses faster at higher levels)
    val levelFactor = (attribute.level.toFloat() / 100f).coerceIn(0.1f, 1f)
    val infiniteTransition = rememberInfiniteTransition(label = "attributeGlow")
    val pulseGlow by infiniteTransition.animateFloat(
        initialValue = 0.4f * levelFactor,
        targetValue = 1.0f * levelFactor,
        animationSpec = infiniteRepeatable(
            animation = tween((1200 + (100 - attribute.level) * 15).coerceAtLeast(800), easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    val goldGlowTransition = rememberInfiniteTransition(label = "goldGlow")
    val goldOffset by goldGlowTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(3000), RepeatMode.Restart),
        label = "goldOffset"
    )

    // Tier based glowing colors and border brushes
    val currentGlowColor = when {
        attribute.level >= 90 -> Color(0xFFFFD700) // Golden Arise
        attribute.level >= 70 -> Color(0xFF7E57C2) // Violet Monarch
        attribute.level >= 50 -> GoldAP          // Gold Shadow
        attribute.level >= 40 -> Color(0xFFAB47BC) // Diamond Sentinel (Purple)
        attribute.level >= 30 -> Color(0xFF42A5F5) // Crystal Knight (Blue)
        attribute.level >= 20 -> Color(0xFF66BB6A) // Steel Warrior (Green)
        attribute.level >= 10 -> Color(0xFFB0BEC5) // Iron Soldier (Silver)
        else -> attrColor                         // Base attribute color
    }

    val borderBrush = when {
        attribute.level >= 90 -> Brush.linearGradient(
            colors = listOf(Color(0xFFFFD700), Color(0xFFFFFFFF), Color(0xFFFFD700)),
            start = Offset(0f, 0f),
            end = Offset(goldOffset * 1000f, goldOffset * 1000f)
        )
        attribute.level >= 70 -> Brush.linearGradient(
            colors = listOf(Color(0xFF7E57C2), Color(0xFFEF5350), Color(0xFF7E57C2)),
            start = Offset(0f, 0f),
            end = Offset(goldOffset * 1000f, goldOffset * 1000f)
        )
        attribute.level >= 50 -> Brush.linearGradient(
            colors = listOf(GoldAP, Color(0xFFFFD700), GoldAP),
            start = Offset(0f, 0f),
            end = Offset(goldOffset * 800f, goldOffset * 800f)
        )
        attribute.level >= 40 -> SolidColor(Color(0xFFAB47BC))
        attribute.level >= 30 -> SolidColor(Color(0xFF42A5F5))
        attribute.level >= 20 -> SolidColor(Color(0xFF66BB6A))
        attribute.level >= 10 -> SolidColor(Color(0xFFB0BEC5))
        else -> SolidColor(attrColor.copy(alpha = 0.3f))
    }

    val borderWidth = when {
        attribute.level >= 90 -> 3.dp
        attribute.level >= 50 -> 2.dp
        attribute.level >= 10 -> 1.5.dp
        else -> 1.dp
    }
    val cardBorder = BorderStroke(borderWidth, borderBrush)

    // Dynamic shadow elevation: grows larger with every single level increase
    val baseElevation = 2.dp + (attribute.level.toFloat() * 0.25f).dp
    val glowElevation = (baseElevation * pulseGlow).coerceIn(1.dp, 28.dp)

    // Dynamic shadow opacity: intensifies slightly with every single level increase
    val baseAlpha = 0.1f + (attribute.level.toFloat() / 200f)
    val glowAlpha = (baseAlpha * pulseGlow).coerceIn(0.08f, 0.6f)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = if (attribute.level >= 10) glowElevation.coerceAtMost(12.dp) else 0.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = currentGlowColor.copy(alpha = glowAlpha * 0.4f),
                spotColor = currentGlowColor.copy(alpha = glowAlpha * 0.3f)
            ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = CardNavy),
        border = cardBorder
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Header Row: Emoji Circle, Display Name, Level
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Emoji circle (compact for clean layout)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Black, RoundedCornerShape(20.dp))
                        .border(1.dp, attrColor.copy(0.4f), RoundedCornerShape(20.dp))
                ) {
                    Icon(
                        painter = painterResource(id = attribute.type.iconResId()),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Name of attribute
                Text(
                    text = attribute.type.displayName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                // Level text
                Text(
                    text = "Lv.${attribute.level}",
                    style = MaterialTheme.typography.titleMedium,
                    color = when {
                        attribute.level >= 90 -> Color(0xFFFFD700)
                        attribute.level >= 70 -> Color(0xFFB39DDB)
                        attribute.level >= 50 -> GoldAP
                        attribute.level >= 40 -> Color(0xFFE040FB)
                        attribute.level >= 30 -> Color(0xFF40C4FF)
                        attribute.level >= 20 -> Color(0xFF69F0AE)
                        attribute.level >= 10 -> Color(0xFFECEFF1)
                        else -> attrColor
                    },
                    fontWeight = FontWeight.Bold
                )
            }

            // Progress bar - spans full width of the card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(Color(0xFF141414), RoundedCornerShape(5.dp))
                    .border(0.5.dp, Color(0xFF2E2E2E), RoundedCornerShape(5.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(animProgress)
                        .fillMaxHeight()
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(attrColor, currentGlowColor)
                            ),
                            shape = RoundedCornerShape(5.dp)
                        )
                )
            }

            // Details Row: XP and Rank Title
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${attribute.currentXp} / ${attribute.xpForNextLevel} XP",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = attribute.rank.title,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(attribute.rank.colorHex)
                )
            }
        }
    }
}
