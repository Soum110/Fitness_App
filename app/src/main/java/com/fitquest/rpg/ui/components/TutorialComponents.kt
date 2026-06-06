package com.fitquest.rpg.ui.components

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import com.fitquest.rpg.ui.theme.*


// Shared preference manager for tracking completed tutorials per page
object TutorialManager {
    private const val PREFS_NAME = "fitquest_tutorial_prefs"
    
    fun isTutorialCompleted(context: Context, screenName: String): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean("completed_$screenName", false)
    }
    
    fun setTutorialCompleted(context: Context, screenName: String, completed: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean("completed_$screenName", completed).apply()
    }
    
    fun resetAllTutorials(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }
}

data class TutorialStep(
    val anchorKey: String,
    val title: String,
    val description: String
)

@Composable
fun TutorialOverlay(
    step: TutorialStep,
    anchorRect: Rect?,
    onNext: () -> Unit,
    onSkip: () -> Unit,
    currentStepIndex: Int,
    totalSteps: Int
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    
    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }
    val isSpotlightInTopHalf = anchorRect?.let { it.center.y < screenHeightPx / 2 } ?: true
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = true, onClick = {}) // Consume clicks in backdrop
    ) {
            // 1. Darkened spotlight backdrop Canvas
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                
                // Draw dark layer
                drawRect(
                    color = Color.Black.copy(alpha = 0.82f),
                    size = Size(canvasWidth, canvasHeight)
                )
                
                if (anchorRect != null) {
                    // Highlight Cutout Rect (add a small padding)
                    val paddingPx = with(density) { 6.dp.toPx() }
                    val spotlightRect = Rect(
                        left = anchorRect.left - paddingPx,
                        top = anchorRect.top - paddingPx,
                        right = anchorRect.right + paddingPx,
                        bottom = anchorRect.bottom + paddingPx
                    )
                    
                    drawRoundRect(
                        color = Color.Transparent,
                        topLeft = Offset(spotlightRect.left, spotlightRect.top),
                        size = Size(spotlightRect.width, spotlightRect.height),
                        cornerRadius = CornerRadius(with(density) { 8.dp.toPx() }),
                        blendMode = BlendMode.Clear
                    )
                    
                    // Neon gold border outline around the spotlight
                    drawRoundRect(
                        color = NeonGold.copy(alpha = 0.85f),
                        topLeft = Offset(spotlightRect.left, spotlightRect.top),
                        size = Size(spotlightRect.width, spotlightRect.height),
                        cornerRadius = CornerRadius(with(density) { 8.dp.toPx() }),
                        style = androidx.compose.ui.graphics.drawscope.Stroke(width = with(density) { 2.dp.toPx() })
                    )
                }
            }
            
            // 2. Tooltip Card layout
            val alignModifier = if (anchorRect == null) {
                Modifier.align(Alignment.Center)
            } else if (isSpotlightInTopHalf) {
                Modifier.align(Alignment.BottomCenter).padding(bottom = 56.dp)
            } else {
                Modifier.align(Alignment.TopCenter).padding(top = 56.dp)
            }
            
            Box(
                modifier = alignModifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                // Draw sliding pointer arrow towards the target X coordinate
                val spotlightCenterX = anchorRect?.center?.x
                val tooltipLeftPx = with(density) { 24.dp.toPx() }
                val tooltipRightPx = with(density) { (configuration.screenWidthDp.dp - 24.dp).toPx() }
                val arrowXPx = spotlightCenterX?.coerceIn(
                    tooltipLeftPx + with(density) { 24.dp.toPx() },
                    tooltipRightPx - with(density) { 24.dp.toPx() }
                )
                val arrowXDp = arrowXPx?.let { with(density) { (it - tooltipLeftPx).toDp() } }
                
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // If spotlight is in bottom half, arrow points DOWN (placed at the bottom of the tooltip box)
                    if (anchorRect != null && isSpotlightInTopHalf) {
                        // Arrow pointing UP, placed at the top of the tooltip box
                        ArrowIndicator(arrowXDp = arrowXDp ?: 0.dp, pointingUp = true)
                    }
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF0C0C0E)),
                        border = BorderStroke(1.dp, BorderNavy)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Title & Step Indicators
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = step.title.uppercase(),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = NeonGold,
                                    fontWeight = FontWeight.Black,
                                    letterSpacing = 1.sp
                                )
                                Text(
                                    text = "${currentStepIndex + 1} / $totalSteps",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            
                            // Description body text
                            Text(
                                text = step.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFFDDDDDD),
                                lineHeight = 18.sp
                            )
                            
                            // Action row buttons
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(
                                    onClick = onSkip,
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(
                                        "SKIP GUIDE",
                                        color = Color.Gray,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.labelMedium,
                                        letterSpacing = 0.5.sp
                                    )
                                }
                                
                                Button(
                                    onClick = onNext,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.White,
                                        contentColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(4.dp),
                                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = if (currentStepIndex == totalSteps - 1) "FINISH" else "NEXT",
                                        fontWeight = FontWeight.Black,
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                }
                            }
                        }
                    }
                    
                    if (anchorRect != null && !isSpotlightInTopHalf) {
                        // Arrow pointing DOWN, placed at the bottom of the tooltip box
                        ArrowIndicator(arrowXDp = arrowXDp ?: 0.dp, pointingUp = false)
                    }
                }
            }
        }
    }

@Composable
private fun ArrowIndicator(arrowXDp: Dp = 0.dp, pointingUp: Boolean) {
    val arrowColor = Color(0xFF0C0C0E)
    val strokeColor = BorderNavy
    val arrowHeight = 8.dp
    val arrowWidth = 16.dp
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(arrowHeight)
    ) {
        Canvas(
            modifier = Modifier
                .offset(x = arrowXDp - (arrowWidth / 2))
                .size(arrowWidth, arrowHeight)
        ) {
            val path = Path().apply {
                if (pointingUp) {
                    moveTo(size.width / 2, 0f)
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                } else {
                    moveTo(0f, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width / 2, size.height)
                }
                close()
            }
            
            // Draw background fill
            drawPath(path = path, color = arrowColor)
            
            // Draw outline borders
            if (pointingUp) {
                drawLine(color = strokeColor, start = Offset(0f, size.height), end = Offset(size.width / 2, 0f), strokeWidth = 2f)
                drawLine(color = strokeColor, start = Offset(size.width / 2, 0f), end = Offset(size.width, size.height), strokeWidth = 2f)
            } else {
                drawLine(color = strokeColor, start = Offset(0f, 0f), end = Offset(size.width / 2, size.height), strokeWidth = 2f)
                drawLine(color = strokeColor, start = Offset(size.width / 2, size.height), end = Offset(size.width, 0f), strokeWidth = 2f)
            }
        }
    }
}

// Extension modifier to easily capture bounds of targeted views in root view space
fun Modifier.tutorialAnchor(key: String, map: MutableMap<String, Rect>): Modifier = this.onGloballyPositioned { coords ->
    val position = coords.positionInRoot()
    val size = coords.size
    map[key] = Rect(position.x, position.y, position.x + size.width, position.y + size.height)
}

fun calculateLocalRect(anchorRect: Rect?, rootRect: Rect?): Rect? {
    if (anchorRect == null || rootRect == null) return anchorRect
    return Rect(
        left = anchorRect.left - rootRect.left,
        top = anchorRect.top - rootRect.top,
        right = anchorRect.right - rootRect.left,
        bottom = anchorRect.bottom - rootRect.top
    )
}
