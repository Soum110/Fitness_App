package com.fitquest.rpg.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.res.painterResource
import com.fitquest.rpg.core.domain.model.*
import com.fitquest.rpg.ui.theme.*
import kotlin.math.cos
import kotlin.math.sin

/**
 * Animated circular XP ring for a single attribute.
 */
@Composable
fun AttributeXpRing(
    attribute: Attribute,
    modifier: Modifier = Modifier,
    size: Dp = 90.dp
) {
    val animatedProgress by animateFloatAsState(
        targetValue = attribute.progressFraction,
        animationSpec = tween(1200, easing = FastOutSlowInEasing),
        label = "xpRingProgress"
    )

    val ringColor = Color(attribute.type.color)
    val glowColor = ringColor.copy(alpha = 0.3f)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(size)
        ) {
            Canvas(modifier = Modifier.size(size)) {
                val strokeWidth = 8.dp.toPx()
                val inset = strokeWidth / 2
                val arcSize = Size(this.size.width - strokeWidth, this.size.height - strokeWidth)

                // Background track
                drawArc(
                    color = Color.White.copy(alpha = 0.08f),
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    topLeft = Offset(inset, inset),
                    size = arcSize,
                    style = Stroke(strokeWidth, cap = StrokeCap.Round)
                )

                // Glow layer
                if (animatedProgress > 0f) {
                    drawArc(
                        color = glowColor,
                        startAngle = -90f,
                        sweepAngle = 360f * animatedProgress,
                        useCenter = false,
                        topLeft = Offset(inset - 2, inset - 2),
                        size = Size(arcSize.width + 4, arcSize.height + 4),
                        style = Stroke(strokeWidth + 4, cap = StrokeCap.Round)
                    )
                }

                // Progress arc
                drawArc(
                    brush = Brush.sweepGradient(
                        listOf(ringColor.copy(alpha = 0.6f), ringColor)
                    ),
                    startAngle = -90f,
                    sweepAngle = 360f * animatedProgress,
                    useCenter = false,
                    topLeft = Offset(inset, inset),
                    size = arcSize,
                    style = Stroke(strokeWidth, cap = StrokeCap.Round)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = attribute.type.iconResId()),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "${attribute.level}",
                    style = MaterialTheme.typography.labelLarge,
                    color = ringColor,
                    fontWeight = FontWeight.Black
                )
            }
        }

        val displayNameAbbr = when (attribute.type) {
            AttributeType.STRENGTH -> "STR"
            AttributeType.STAMINA -> "STA"
            AttributeType.FLEXIBILITY -> "FLEX"
            AttributeType.INTELLIGENCE -> "INT"
            AttributeType.ENERGY -> "ENG"
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = displayNameAbbr,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}

/**
 * RPG-styled card with clean Vercel borders and flat background.
 */
@Composable
fun RpgCard(
    modifier: Modifier = Modifier,
    glowColor: Color = BorderNavy,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val cardModifier = if (onClick != null) {
        modifier.clickable(onClick = onClick)
    } else modifier

    Card(
        modifier = cardModifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = CardNavy),
        border = androidx.compose.foundation.BorderStroke(1.dp, glowColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = content
        )
    }
}

/**
 * Rank badge with clean Vercel border and flat background.
 */
@Composable
fun RankBadge(rank: Rank, modifier: Modifier = Modifier) {
    val rankColor = Color(rank.colorHex)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color.Black, RoundedCornerShape(6.dp))
            .border(
                width = 1.dp,
                color = rankColor.copy(alpha = 0.5f),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Icon(
                painter = painterResource(id = rank.iconResId()),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = rank.title.uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = rankColor,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )
        }
    }
}

/**
 * Task list item with Vercel styling and clean completion.
 */
@Composable
fun TaskItem(
    task: com.fitquest.rpg.core.domain.model.DailyTask,
    onComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val attributeColor = Color(task.targetAttribute.color)

    val checkAnim by animateFloatAsState(
        targetValue = if (task.isCompleted) 1f else 0f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "checkAnim"
    )

    RpgCard(
        modifier = modifier.fillMaxWidth(),
        glowColor = if (task.isCompleted) BorderNavy else BorderNavy
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Attribute color indicator bar (flat, no alpha gradient)
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(44.dp)
                    .clip(RoundedCornerShape(1.5.dp))
                    .background(attributeColor)
            )

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        painter = painterResource(id = task.taskType.iconResId()),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (task.isCompleted)
                            MaterialTheme.colorScheme.onSurfaceVariant
                        else
                            MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(2.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (task.formattedVolume().isNotEmpty()) {
                        Text(
                            text = task.formattedVolume(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = attributeColor,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Text(
                        text = "+${task.xpReward} XP  +${task.apReward} AP",
                        style = MaterialTheme.typography.bodyMedium,
                        color = GoldAP.copy(alpha = 0.8f)
                    )
                }
            }

            // Completion check circle button
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        if (task.isCompleted) SuccessGreen.copy(alpha = checkAnim)
                        else Color.Transparent
                    )
                    .border(
                        width = 1.5.dp,
                        color = if (task.isCompleted) SuccessGreen else BorderNavy,
                        shape = CircleShape
                    )
                    .clickable(enabled = !task.isCompleted) { onComplete() }
            ) {
                if (task.isCompleted) {
                    Text("✓", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}

/**
 * Action Points chip display.
 */
@Composable
fun ActionPointsChip(points: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color(0xFF0F0F00), RoundedCornerShape(6.dp))
            .border(1.dp, GoldAP.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Icon(
                painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_energy),
                contentDescription = null,
                tint = GoldAP,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = "$points AP",
                style = MaterialTheme.typography.labelLarge,
                color = GoldAP,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * Streak fire indicator.
 */
@Composable
fun StreakBadge(streak: Int, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .background(Color(0xFF140700), RoundedCornerShape(6.dp))
            .border(1.dp, Color(0xFFFF6D00).copy(alpha = 0.3f), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Icon(
            painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_streak),
            contentDescription = null,
            tint = Color(0xFFFF8A65),
            modifier = Modifier.size(12.dp)
        )
        Text(
            text = "$streak day${if (streak != 1) "s" else ""}",
            style = MaterialTheme.typography.labelLarge,
            color = Color(0xFFFF8A65),
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Helper to animate progress forward and skip backward visual demotion on level up.
 */
@Composable
fun rememberProgressFraction(level: Int, fraction: Float): Float {
    var lastLevel by remember { mutableStateOf(level) }
    val animatable = remember { Animatable(fraction) }

    LaunchedEffect(level, fraction) {
        if (level > lastLevel) {
            animatable.animateTo(1.0f, tween(400, easing = FastOutSlowInEasing))
            animatable.snapTo(0.0f)
            lastLevel = level
        } else if (level < lastLevel) {
            animatable.snapTo(fraction)
            lastLevel = level
        }
        animatable.animateTo(fraction, tween(800, easing = FastOutSlowInEasing))
    }
    return animatable.value
}

/**
 * Horizontal XP slider representing an attribute, highlighting potential XP increase from today's active tasks.
 */
@Composable
fun AttributeXpSlider(
    attribute: Attribute,
    potentialXp: Long,
    modifier: Modifier = Modifier,
    showName: Boolean = true
) {
    val attrColor = Color(attribute.type.color)

    // Animate current progress
    val currentProgress = attribute.progressFraction
    val animCurrentProgress = rememberProgressFraction(
        level = attribute.level,
        fraction = currentProgress
    )

    // Calculate total potential progress (current + potential)
    val nextLevelXp = attribute.xpForNextLevel
    val potentialFraction = if (nextLevelXp > 0) potentialXp.toFloat() / nextLevelXp.toFloat() else 0f
    val totalPotentialProgress = (currentProgress + potentialFraction).coerceIn(0f, 1f)
    val animPotentialProgress = rememberProgressFraction(
        level = attribute.level,
        fraction = totalPotentialProgress
    )

    // Pulsing alpha for the potential gain extension
    val infiniteTransition = rememberInfiniteTransition(label = "potentialPulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.25f,
        targetValue = 0.55f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.fillMaxWidth().padding(vertical = 6.dp)
    ) {
        // Emoji and label
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.width(if (showName) 80.dp else 24.dp)
        ) {
            Icon(
                painter = painterResource(id = attribute.type.iconResId()),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(16.dp)
            )
            if (showName) {
                Text(
                    text = attribute.type.displayName.take(8),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // The Stacked Progress Bar (Background, Potential Gain, Current XP)
        Box(
            modifier = Modifier
                .weight(1f)
                .height(6.dp)
                .background(Color(0xFF141414), RoundedCornerShape(3.dp))
                .border(0.5.dp, Color(0xFF2E2E2E), RoundedCornerShape(3.dp))
        ) {
            // 1. Potential Gain Layer (pulsing/flashing preview, behind current XP but stretching further)
            if (totalPotentialProgress > currentProgress) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(animPotentialProgress)
                        .fillMaxHeight()
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(attrColor.copy(alpha = pulseAlpha), attrColor.copy(alpha = pulseAlpha * 0.3f))
                            ),
                            shape = RoundedCornerShape(3.dp)
                        )
                )
            }

            // 2. Current XP Layer (solid color)
            Box(
                modifier = Modifier
                    .fillMaxWidth(animCurrentProgress)
                    .fillMaxHeight()
                    .background(attrColor, RoundedCornerShape(3.dp))
            )
        }

        // Level and Potential Gain Indicator
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.width(80.dp)
        ) {
            Text(
                text = "Lv.${attribute.level}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = attrColor
            )
            if (potentialXp > 0) {
                Text(
                    text = " (+${potentialXp})",
                    style = MaterialTheme.typography.labelSmall,
                    color = NeonGold,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

fun AttributeType.iconResId(): Int = when (this) {
    AttributeType.STRENGTH -> com.fitquest.rpg.R.drawable.ic_strength
    AttributeType.FLEXIBILITY -> com.fitquest.rpg.R.drawable.ic_flexibility
    AttributeType.STAMINA -> com.fitquest.rpg.R.drawable.ic_stamina
    AttributeType.ENERGY -> com.fitquest.rpg.R.drawable.ic_energy
    AttributeType.INTELLIGENCE -> com.fitquest.rpg.R.drawable.ic_intelligence
}

fun TaskType.iconResId(): Int = when (this) {
    TaskType.WORKOUT -> com.fitquest.rpg.R.drawable.ic_strength
    TaskType.CARDIO -> com.fitquest.rpg.R.drawable.ic_stamina
    TaskType.STRETCH -> com.fitquest.rpg.R.drawable.ic_flexibility
    TaskType.DIET -> com.fitquest.rpg.R.drawable.ic_diet
    TaskType.HABIT -> com.fitquest.rpg.R.drawable.ic_energy
    TaskType.READING -> com.fitquest.rpg.R.drawable.ic_reading
    TaskType.MEDITATION -> com.fitquest.rpg.R.drawable.ic_meditation
}

fun Rank.iconResId(): Int = when (this) {
    Rank.BRONZE_RECRUIT -> com.fitquest.rpg.R.drawable.ic_tier_bronze
    Rank.IRON_SOLDIER -> com.fitquest.rpg.R.drawable.ic_tier_iron
    Rank.STEEL_WARRIOR -> com.fitquest.rpg.R.drawable.ic_tier_steel
    Rank.CRYSTAL_KNIGHT -> com.fitquest.rpg.R.drawable.ic_tier_crystal
    Rank.DIAMOND_SENTINEL -> com.fitquest.rpg.R.drawable.ic_tier_diamond
    Rank.GOLD_SHADOW -> com.fitquest.rpg.R.drawable.ic_tier_gold
    Rank.PLATINUM_HUNTER -> com.fitquest.rpg.R.drawable.ic_tier_platinum
    Rank.MYTHIC_RAIDER -> com.fitquest.rpg.R.drawable.ic_tier_mythic
    Rank.SHADOW_MONARCH -> com.fitquest.rpg.R.drawable.ic_tier_monarch
    Rank.ARISE -> com.fitquest.rpg.R.drawable.ic_tier_arise
}
