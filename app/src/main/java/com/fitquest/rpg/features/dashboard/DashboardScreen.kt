package com.fitquest.rpg.features.dashboard

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.fitquest.rpg.core.domain.model.*
import com.fitquest.rpg.ui.components.*
import com.fitquest.rpg.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToStore: () -> Unit,
    onNavigateToAttributes: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Entrance animation
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF0A0E20), DeepNavy, Color(0xFF080C18))
                )
            )
    ) {
        // Background particle/glow effect (decorative)
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                brush = Brush.radialGradient(
                    listOf(NeonPurple.copy(alpha = 0.06f), Color.Transparent),
                    radius = size.width * 0.6f,
                    center = androidx.compose.ui.geometry.Offset(size.width * 0.8f, size.height * 0.1f)
                ),
                radius = size.width * 0.6f,
                center = androidx.compose.ui.geometry.Offset(size.width * 0.8f, size.height * 0.1f)
            )
            drawCircle(
                brush = Brush.radialGradient(
                    listOf(NeonBlue.copy(alpha = 0.04f), Color.Transparent),
                    radius = size.width * 0.5f,
                    center = androidx.compose.ui.geometry.Offset(size.width * 0.1f, size.height * 0.4f)
                ),
                radius = size.width * 0.5f,
                center = androidx.compose.ui.geometry.Offset(size.width * 0.1f, size.height * 0.4f)
            )
        }

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(tween(800)) + slideInVertically(tween(800)) { it / 4 }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 96.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // ── Hero Header ────────────────────────────────────────────
                item {
                    DashboardHeader(uiState, onNavigateToStore)
                }

                // ── Attributes Grid ────────────────────────────────────────
                item {
                    AttributesSection(
                        attributes = uiState.attributes,
                        onViewAll = onNavigateToAttributes
                    )
                }

                // ── Week Phase Banner ──────────────────────────────────────
                if (uiState.weekPhaseDescription.isNotEmpty()) {
                    item {
                        WeekPhaseBanner(uiState.weekPhaseDescription)
                    }
                }

                // ── Daily Quests ───────────────────────────────────────────
                item {
                    val done = uiState.todaysTasks.count { it.isCompleted }
                    val total = uiState.todaysTasks.size
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                "⚔️ DAILY QUESTS",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                letterSpacing = 2.sp
                            )
                            Text(
                                "$done / $total completed",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        // Daily progress ring
                        if (total > 0) {
                            val progress = done.toFloat() / total.toFloat()
                            DailyProgressRing(progress = progress, done = done, total = total)
                        }
                    }
                }

                // Task items
                if (uiState.todaysTasks.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("⚙️", fontSize = 40.sp)
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    "Generating your daily quests...",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                } else {
                    items(uiState.todaysTasks, key = { it.id }) { task ->
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + expandVertically()
                        ) {
                            TaskItem(
                                task = task,
                                onComplete = { viewModel.completeTask(task.id) },
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }

                // ── Motivational Quote ─────────────────────────────────────
                item {
                    MotivationalQuote(profile = uiState.profile)
                }
            }
        }

        // Loading overlay
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().background(DeepNavy),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = NeonPurple)
                    Spacer(Modifier.height(16.dp))
                    Text("Summoning your destiny...", color = NeonPurple)
                }
            }
        }
    }
}

@Composable
private fun DashboardHeader(
    state: DashboardUiState,
    onNavigateToStore: () -> Unit
) {
    val profile = state.profile
    val economy = state.economy
    val overallRank = state.attributes.let { attrs ->
        if (attrs.isEmpty()) Rank.BRONZE_RECRUIT
        else Rank.fromLevel(attrs.map { it.level }.average().toInt())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF14103A), Color.Transparent)
                )
            )
            .padding(top = 48.dp, start = 20.dp, end = 20.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = "Good ${greeting()},",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = profile?.name?.ifBlank { "Hunter" } ?: "Hunter",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Black
                )
            }
            // AP button
            ActionPointsChip(
                points = economy.availableActionPoints,
                modifier = Modifier.clickable { onNavigateToStore() }
            )
        }

        Spacer(Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            RankBadge(rank = overallRank)
            if (economy.currentStreak > 0) {
                StreakBadge(streak = economy.currentStreak)
            }
        }
    }
}

@Composable
private fun AttributesSection(
    attributes: List<Attribute>,
    onViewAll: () -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "📊 ATTRIBUTES",
                style = MaterialTheme.typography.titleLarge,
                letterSpacing = 2.sp
            )
            TextButton(onClick = onViewAll) {
                Text("View All", color = NeonPurple)
            }
        }

        Spacer(Modifier.height(12.dp))

        RpgCard(glowColor = NeonPurple) {
            if (attributes.isEmpty()) {
                Box(Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = NeonPurple, modifier = Modifier.size(24.dp))
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    attributes.take(5).forEach { attribute ->
                        AttributeXpRing(attribute = attribute, size = 72.dp)
                    }
                }
            }
        }
    }
}

@Composable
private fun WeekPhaseBanner(description: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFF1A1040), Color(0xFF101A3A))
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .border(1.dp, NeonPurple.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = NeonPurpleLight
        )
    }
}

@Composable
private fun DailyProgressRing(progress: Float, done: Int, total: Int) {
    val animProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000, easing = FastOutSlowInEasing),
        label = "dailyProgress"
    )
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(52.dp)) {
        Canvas(modifier = Modifier.size(52.dp)) {
            val strokeW = 5.dp.toPx()
            drawArc(
                color = Color.White.copy(alpha = 0.1f),
                startAngle = -90f, sweepAngle = 360f, useCenter = false,
                style = Stroke(strokeW, cap = StrokeCap.Round)
            )
            drawArc(
                brush = Brush.sweepGradient(listOf(NeonPurple, NeonCyan)),
                startAngle = -90f, sweepAngle = 360f * animProgress, useCenter = false,
                style = Stroke(strokeW, cap = StrokeCap.Round)
            )
        }
        Text(
            text = "$done/$total",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp
        )
    }
}

@Composable
private fun MotivationalQuote(profile: UserProfile?) {
    val quotes = listOf(
        "\"I am the only one who can change my own story.\" — Solo Leveling",
        "\"Arise.\" — Sung Jin-Woo",
        "\"Every day you don't train is a day someone else does.\"",
        "\"The grind is silent. The results are not.\"",
        "\"Pain is temporary. Progress is permanent.\"",
        "\"Discipline is the bridge between goals and accomplishment.\" — Jim Rohn",
        "\"The iron never lies.\" — Henry Rollins"
    )
    val quote = remember { quotes.random() }

    RpgCard(
        modifier = Modifier.padding(horizontal = 16.dp),
        glowColor = NeonGold
    ) {
        Text(
            text = quote,
            style = MaterialTheme.typography.bodyMedium,
            color = NeonGold.copy(alpha = 0.9f),
            fontWeight = FontWeight.Medium
        )
    }
}

private fun greeting(): String {
    val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
    return when {
        hour < 12 -> "Morning"
        hour < 17 -> "Afternoon"
        else -> "Evening"
    }
}
