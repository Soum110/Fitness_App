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
                Text(
                    text = attribute.type.emoji,
                    fontSize = 22.sp
                )
                Text(
                    text = "${attribute.level}",
                    style = MaterialTheme.typography.labelLarge,
                    color = ringColor,
                    fontWeight = FontWeight.Black
                )
            }
        }

        Spacer(Modifier.height(4.dp))
        Text(
            text = attribute.type.displayName,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * RPG-styled card with glow border effect.
 */
@Composable
fun RpgCard(
    modifier: Modifier = Modifier,
    glowColor: Color = NeonPurple,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val cardModifier = if (onClick != null) {
        modifier.clickable(onClick = onClick)
    } else modifier

    Card(
        modifier = cardModifier
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = glowColor.copy(alpha = 0.3f),
                spotColor = glowColor.copy(alpha = 0.2f)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardNavy),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderNavy)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = content
        )
    }
}

/**
 * Rank badge with animated shimmer.
 */
@Composable
fun RankBadge(rank: Rank, modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "rankShimmer")
    val shimmerAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f, targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmerAlpha"
    )

    val rankColor = Color(rank.colorHex)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                brush = Brush.radialGradient(
                    listOf(rankColor.copy(alpha = shimmerAlpha * 0.2f), Color.Transparent)
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(listOf(rankColor.copy(alpha = shimmerAlpha), rankColor.copy(0.3f))),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(text = rank.emoji, fontSize = 16.sp)
            Text(
                text = rank.title.uppercase(),
                style = MaterialTheme.typography.labelLarge,
                color = rankColor.copy(alpha = shimmerAlpha),
                fontWeight = FontWeight.Black,
                letterSpacing = 2.sp
            )
        }
    }
}

/**
 * Task list item with completion checkbox and animated strikethrough.
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
        glowColor = if (task.isCompleted) SuccessGreen else attributeColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Attribute color indicator
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(52.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(attributeColor, attributeColor.copy(0.3f))
                        )
                    )
            )

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = task.taskType.icon, fontSize = 14.sp)
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (task.isCompleted)
                            MaterialTheme.colorScheme.onSurfaceVariant
                        else
                            MaterialTheme.colorScheme.onSurface,
                        maxLines = 1
                    )
                }
                Spacer(Modifier.height(2.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (task.formattedVolume().isNotEmpty()) {
                        Text(
                            text = task.formattedVolume(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = attributeColor
                        )
                    }
                    Text(
                        text = "+${task.xpReward} XP  +${task.apReward} AP",
                        style = MaterialTheme.typography.bodyMedium,
                        color = GoldAP.copy(alpha = 0.8f)
                    )
                }
            }

            // Completion button
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        if (task.isCompleted) SuccessGreen.copy(alpha = checkAnim)
                        else SurfaceNavy
                    )
                    .border(
                        width = 2.dp,
                        color = if (task.isCompleted) SuccessGreen else BorderNavy,
                        shape = CircleShape
                    )
                    .clickable(enabled = !task.isCompleted) { onComplete() }
            ) {
                if (task.isCompleted) {
                    Text("✓", color = Color.White, fontWeight = FontWeight.Black)
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
    val infiniteTransition = rememberInfiniteTransition(label = "apGlow")
    val glow by infiniteTransition.animateFloat(
        initialValue = 0.7f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse),
        label = "apGlowAlpha"
    )

    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFF5C4800), Color(0xFF7A5C00))
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .border(1.dp, GoldAP.copy(alpha = glow), RoundedCornerShape(20.dp))
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("⚡", fontSize = 16.sp)
            Text(
                text = "$points AP",
                style = MaterialTheme.typography.titleMedium,
                color = GoldAP.copy(alpha = glow),
                fontWeight = FontWeight.Black
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
            .background(Color(0xFF3E1A00), RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFFF6D00).copy(alpha = 0.5f), RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text("🔥", fontSize = 14.sp)
        Text(
            text = "$streak day${if (streak != 1) "s" else ""}",
            style = MaterialTheme.typography.labelLarge,
            color = Color(0xFFFF8A65)
        )
    }
}
