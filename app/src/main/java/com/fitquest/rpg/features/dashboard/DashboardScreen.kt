package com.fitquest.rpg.features.dashboard

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.launch
import com.fitquest.rpg.core.domain.model.*
import com.fitquest.rpg.ui.components.*
import com.fitquest.rpg.ui.theme.*
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import java.util.Locale
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToStore: () -> Unit,
    onNavigateToAttributes: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Onboarding Tutorial Tour States
    val context = LocalContext.current
    var showTutorial by remember { mutableStateOf(false) }
    var tutorialStep by remember { mutableStateOf(0) }
    val tutorialAnchors = remember { mutableStateMapOf<String, Rect>() }

    LaunchedEffect(uiState.profile, uiState.isLoading) {
        if (!uiState.isLoading) {
            val profile = uiState.profile
            if (profile == null || !profile.onboardingComplete) {
                onNavigateToOnboarding()
            } else {
                showTutorial = !TutorialManager.isTutorialCompleted(context, "dashboard")
            }
        }
    }

    // Entrance animation
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    // Level-up Fire Animation States
    val globalLevel = remember(uiState.attributes) {
        if (uiState.attributes.isEmpty()) 1
        else XpAlgorithm.globalLevelFromTotalXp(uiState.attributes.sumOf { it.totalXpEarned }).first
    }
    var lastLevel by remember { mutableStateOf<Int?>(null) }
    var attributesWasEmpty by remember { mutableStateOf(true) }
    var showLevelUpAnimation by remember { mutableStateOf(false) }
    var showNewLevel by remember { mutableStateOf(1) }
    var showPrevLevel by remember { mutableStateOf(1) }
    
    val particles = remember { mutableStateListOf<FireParticle>() }
    
    LaunchedEffect(globalLevel, uiState.isLoading) {
        if (!uiState.isLoading) {
            val prev = lastLevel
            val prevWasEmpty = attributesWasEmpty
            lastLevel = globalLevel
            attributesWasEmpty = uiState.attributes.isEmpty()
            
            if (prev != null && !prevWasEmpty && globalLevel > prev) {
                showPrevLevel = prev
                showNewLevel = globalLevel
                showLevelUpAnimation = true
            }
        }
    }
    
    LaunchedEffect(showLevelUpAnimation) {
        if (showLevelUpAnimation) {
            var lastTime = System.currentTimeMillis()
            while (showLevelUpAnimation) {
                withFrameMillis { _ ->
                    val now = System.currentTimeMillis()
                    val dt = ((now - lastTime) / 1000f).coerceIn(0f, 0.05f)
                    lastTime = now
                    
                    // Update particles
                    val iterator = particles.iterator()
                    while (iterator.hasNext()) {
                        val p = iterator.next()
                        p.life -= dt
                        if (p.life <= 0f) {
                            iterator.remove()
                        } else {
                            if (p.isSpark) {
                                p.y += p.vy * dt
                                p.x += (Math.sin(p.life.toDouble() * 8.0) * 80f * dt).toFloat()
                            } else {
                                p.x += p.vx * dt
                                p.y += p.vy * dt
                            }
                        }
                    }
                    
                    // Spawn flame particles
                    repeat(4) {
                        val angle = Math.random() * 2 * Math.PI
                        val speed = 120f + (Math.random() * 160f).toFloat()
                        val p = FireParticle(
                            x = 0f,
                            y = 120f,
                            vx = (Math.cos(angle) * 50f).toFloat(),
                            vy = -speed,
                            maxLife = 0.7f + (Math.random() * 0.5f).toFloat(),
                            baseSize = 25f + (Math.random() * 20f).toFloat(),
                            color = when ((Math.random() * 3).toInt()) {
                                0 -> Color(0xFFFFD54F) // Yellow
                                1 -> Color(0xFFFF8A65) // Orange
                                else -> Color(0xFFEF5350) // Red
                            }
                        )
                        particles.add(p)
                    }

                    // Spawn sparks
                    repeat(2) {
                        val p = FireParticle(
                            x = (-30..30).random().toFloat(),
                            y = 120f,
                            vx = 0f,
                            vy = -220f - (Math.random() * 150f).toFloat(),
                            maxLife = 1.2f + (Math.random() * 0.8f).toFloat(),
                            baseSize = 3f + (Math.random() * 5f).toFloat(),
                            color = Color(0xFFFFE082),
                            isSpark = true
                        )
                        particles.add(p)
                    }
                }
            }
        } else {
            particles.clear()
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .tutorialAnchor("screen_root", tutorialAnchors)
    ) {

        val listState = rememberLazyListState()
        
        LaunchedEffect(tutorialStep, showTutorial, uiState.todaysTasks) {
            if (showTutorial) {
                val targetIndex = when (tutorialStep) {
                    0 -> 0 // header
                    1 -> 0 // ap_chip
                    2 -> 1 // daily_quests
                    3 -> 3 + uiState.todaysTasks.size // graph_analysis
                    else -> 0
                }
                try {
                    listState.animateScrollToItem(targetIndex)
                } catch (e: Exception) {}
            }
        }

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(tween(800)) + slideInVertically(tween(800)) { it / 4 }
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 96.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // ── Hero Header ────────────────────────────────────────────
                item {
                    DashboardHeader(
                        state = uiState,
                        onNavigateToStore = onNavigateToStore,
                        onNavigateToAttributes = onNavigateToAttributes,
                        tutorialAnchors = tutorialAnchors
                    )
                }

                // ── Daily Quests Header & Tracker ──────────────────────────
                item {
                    val done = uiState.todaysTasks.count { it.isCompleted }
                    val total = uiState.todaysTasks.size
                    
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .tutorialAnchor("daily_quests", tutorialAnchors),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Section title
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_quest),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                "DAILY CAMPAIGN",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                letterSpacing = 2.sp
                            )
                        }

                        // Quest Tracker Card
                        if (total > 0) {
                            QuestTrackerCard(done = done, total = total)
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
                                com.fitquest.rpg.ui.components.FitQuestLoadingSpinner(size = 56.dp, accentColor = SuccessGreen)
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    "GENERATING DAILY QUESTS...",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
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
                            val coroutineScope = rememberCoroutineScope()
                            TaskItem(
                                task = task,
                                onComplete = {
                                    val wasCompleted = task.isCompleted
                                    viewModel.toggleTask(task.id)
                                    if (!wasCompleted) {
                                        coroutineScope.launch {
                                            try {
                                                listState.animateScrollToItem(0)
                                            } catch (e: Exception) {}
                                        }
                                    }
                                },
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }

                // ── Attributes Grid ────────────────────────────────────────
                item {
                    AttributesSection(
                        attributes = uiState.attributes,
                        todaysTasks = uiState.todaysTasks,
                        onViewAll = onNavigateToAttributes
                    )
                }

                // ── Graphical Analysis ─────────────────────────────────────
                item {
                    QuestGraphicalAnalysisCard(
                        todaysTasks = uiState.todaysTasks,
                        profile = uiState.profile,
                        modifier = Modifier.tutorialAnchor("graph_analysis", tutorialAnchors)
                    )
                }

                // ── Motivational Quote ─────────────────────────────────────
                item {
                    MotivationalQuote()
                }
            }
        }

        // Loading overlay
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    com.fitquest.rpg.ui.components.FitQuestLoadingSpinner(size = 64.dp, accentColor = SuccessGreen)
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "SUMMONING YOUR DESTINY...",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        letterSpacing = 2.sp
                    )
                }
            }
        }

        // Level Up Fire Animation Overlay
        if (showLevelUpAnimation) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.85f))
                    .clickable(enabled = true) { /* Consume clicks to prevent background interaction */ },
                contentAlignment = Alignment.Center
            ) {
                // Fire Canvas
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    
                    particles.forEach { p ->
                        val lifeFraction = p.life / p.maxLife
                        val alpha = lifeFraction
                        val radius = p.baseSize * lifeFraction
                        
                        drawCircle(
                            color = p.color.copy(alpha = alpha),
                            radius = radius,
                            center = Offset(centerX + p.x, centerY + p.y),
                            blendMode = BlendMode.Screen
                        )
                    }
                }
                
                // Content Overlay
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "SYSTEM ASCENSION",
                            style = MaterialTheme.typography.labelLarge,
                            color = NeonGold,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 4.sp
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "LEVEL UP!",
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier.graphicsLayer(
                                scaleX = scale,
                                scaleY = scale
                            ),
                            color = Color.White,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 2.sp
                        )
                    }
                    
                    // Level Transition Card
                    Row(
                        modifier = Modifier
                            .background(Color.Black, RoundedCornerShape(8.dp))
                            .border(1.dp, NeonGold.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 24.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "LV $showPrevLevel",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "→",
                            style = MaterialTheme.typography.titleLarge,
                            color = NeonGold,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "LV $showNewLevel",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Black
                        )
                    }
                    
                    // System Message Card
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 28.dp)
                            .fillMaxWidth()
                            .border(1.dp, Color(0xFF222222), RoundedCornerShape(6.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.9f))
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "[ SYSTEM ANNOUNCEMENT ]",
                                style = MaterialTheme.typography.labelMedium,
                                color = NeonGold,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 2.sp
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = "Hunter has broken past their current threshold. All base physical attributes have been enhanced, unlocking new quest potentials.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFFBBBBBB),
                                textAlign = TextAlign.Center,
                                lineHeight = 18.sp
                            )
                        }
                    }
                    
                    Spacer(Modifier.height(16.dp))
                    
                    // Confirm button
                    Button(
                        onClick = { showLevelUpAnimation = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = NeonGold,
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            text = "CONFIRM ASCENSION",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
        }

        // Onboarding Tutorial Overlay
        if (showTutorial && tutorialAnchors.isNotEmpty()) {
            val steps = listOf(
                TutorialStep("header", "Level & XP HUD", "This is your main character progression. Complete quests to gain XP and increase your system ascension level!"),
                TutorialStep("ap_chip", "Action Points (AP)", "Earn AP from daily activities to spend on custom shop rewards or redeem cheat days."),
                TutorialStep("daily_quests", "Daily Campaign", "Your active quests for today. Tap on any quest card to complete it and claim your XP & AP rewards."),
                TutorialStep("graph_analysis", "Quest Analysis", "Tracks your quest completion performance over Days (D), Weeks (W), and Months (M).")
            )
            val currentStep = steps.getOrNull(tutorialStep)
            if (currentStep != null) {
                TutorialOverlay(
                    step = currentStep,
                    anchorRect = calculateLocalRect(tutorialAnchors[currentStep.anchorKey], tutorialAnchors["screen_root"]),
                    onNext = {
                        if (tutorialStep < steps.lastIndex) {
                            tutorialStep++
                        } else {
                            showTutorial = false
                            TutorialManager.setTutorialCompleted(context, "dashboard", true)
                        }
                    },
                    onSkip = {
                        showTutorial = false
                        TutorialManager.setTutorialCompleted(context, "dashboard", true)
                    },
                    currentStepIndex = tutorialStep,
                    totalSteps = steps.size
                )
            }
        }
    }
}

@Composable
private fun DashboardHeader(
    state: DashboardUiState,
    onNavigateToStore: () -> Unit,
    onNavigateToAttributes: () -> Unit,
    tutorialAnchors: MutableMap<String, Rect>
) {
    val profile = state.profile
    val economy = state.economy
    val globalLevelPair = if (state.attributes.isEmpty()) Pair(1, 0f)
    else XpAlgorithm.globalLevelFromTotalXp(state.attributes.sumOf { it.totalXpEarned })

    val globalLevel = globalLevelPair.first
    val globalProgressFraction = globalLevelPair.second
    val overallRank = Rank.fromLevel(globalLevel)
    val rankColor = Color(overallRank.colorHex)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 52dp 3D Rank Emblem avatar card
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(CardNavy)
                    .border(1.dp, rankColor.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                    .clickable { onNavigateToAttributes() }
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = overallRank.iconResId()),
                    contentDescription = "Rank Emblem",
                    tint = Color.Unspecified,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Name & Greetings panel
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Good ${greeting()},",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = profile?.name?.ifBlank { "HUNTER" }?.uppercase() ?: "HUNTER",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                )
            }

            // AP button
            ActionPointsChip(
                points = economy.availableActionPoints,
                modifier = Modifier
                    .clickable { onNavigateToStore() }
                    .tutorialAnchor("ap_chip", tutorialAnchors)
            )
        }

        // Compact Level Progress bar (matching Profile screen style)
        val animatedProgress by animateFloatAsState(
            targetValue = globalProgressFraction,
            animationSpec = tween(1000, easing = FastOutSlowInEasing),
            label = "dashboardGlobalXpProgress"
        )
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(38.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(CardNavy)
                .border(1.dp, BorderNavy, RoundedCornerShape(6.dp))
                .tutorialAnchor("header", tutorialAnchors),
            contentAlignment = Alignment.CenterStart
        ) {
            // Fill background
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedProgress)
                    .background(
                        Brush.horizontalGradient(
                            listOf(rankColor.copy(alpha = 0.12f), rankColor.copy(alpha = 0.28f))
                        )
                    )
            )
            
            // Content inside level progress bar
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(rankColor.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                            .border(0.5.dp, rankColor.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "LEVEL",
                            style = MaterialTheme.typography.labelSmall,
                            color = rankColor,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Text(
                        text = "$globalLevel",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Black
                    )
                }
                
                Text(
                    text = "${(globalProgressFraction * 100).toInt()}% XP PROGRESS",
                    style = MaterialTheme.typography.labelSmall,
                    color = rankColor,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
        }

        // Streak & Rank Badges Row
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
    todaysTasks: List<DailyTask>,
    onViewAll: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_rank),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    "ATTRIBUTES",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    letterSpacing = 2.sp
                )
            }
            TextButton(
                onClick = onViewAll,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("View All", color = NeonBlue, fontWeight = FontWeight.Bold)
            }
        }

        if (attributes.isEmpty()) {
            RpgCard(glowColor = BorderNavy) {
                Box(Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center) {
                    com.fitquest.rpg.ui.components.FitQuestLoadingSpinner(size = 32.dp, accentColor = NeonPurple)
                }
            }
        } else {
            attributes.take(5).forEach { attribute ->
                val potentialXp = todaysTasks
                    .filter { it.targetAttribute == attribute.type && !it.isCompleted }
                    .sumOf { it.xpReward }

                ModularAttributeCard(
                    attribute = attribute,
                    potentialXp = potentialXp,
                    onClick = onViewAll
                )
            }
        }
    }
}

@Composable
private fun ModularAttributeCard(
    attribute: Attribute,
    potentialXp: Long,
    onClick: () -> Unit
) {
    val attrColor = Color(attribute.type.color)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = CardNavy),
        border = BorderStroke(1.dp, BorderNavy)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left vertical accent bar matching attribute's color
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(attrColor)
            )

            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Icon representing the attribute
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.Black, CircleShape)
                        .border(1.dp, attrColor.copy(alpha = 0.3f), CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = attribute.type.iconResId()),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(16.dp)
                    )
                }

                // Progress slider & level
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Level & Potential XP Indicator row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = attribute.rank.title.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "LV ${attribute.level}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Black,
                                color = attrColor
                            )
                            if (potentialXp > 0) {
                                Text(
                                    text = "(+${potentialXp} XP)",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = NeonGold,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }
                    }

                    // Progress bar slider
                    val currentProgress = attribute.progressFraction
                    val animCurrentProgress = rememberProgressFraction(
                        level = attribute.level,
                        fraction = currentProgress
                    )

                    val nextLevelXp = attribute.xpForNextLevel
                    val potentialFraction = if (nextLevelXp > 0) potentialXp.toFloat() / nextLevelXp.toFloat() else 0f
                    val totalPotentialProgress = (currentProgress + potentialFraction).coerceIn(0f, 1f)
                    val animPotentialProgress = rememberProgressFraction(
                        level = attribute.level,
                        fraction = totalPotentialProgress
                    )

                    val infiniteTransition = rememberInfiniteTransition(label = "modularAttrPulse")
                    val pulseAlpha by infiniteTransition.animateFloat(
                        initialValue = 0.25f,
                        targetValue = 0.55f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "pulseAlpha"
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .background(Color(0xFF141414), RoundedCornerShape(5.dp))
                            .border(0.5.dp, Color(0xFF2E2E2E), RoundedCornerShape(5.dp))
                    ) {
                        // 1. Potential Gain Layer
                        if (totalPotentialProgress > currentProgress) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(animPotentialProgress)
                                    .fillMaxHeight()
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            listOf(attrColor.copy(alpha = pulseAlpha), attrColor.copy(alpha = pulseAlpha * 0.3f))
                                        ),
                                        shape = RoundedCornerShape(5.dp)
                                    )
                            )
                        }

                        // 2. Current XP Layer
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(animCurrentProgress)
                                .fillMaxHeight()
                                .background(attrColor, RoundedCornerShape(5.dp))
                        )
                    }

                    // Bottom info: XP numbers
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${attribute.currentXp} / ${attribute.xpForNextLevel} XP",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

private enum class AnalysisPeriod { DAY, WEEK, MONTH }

@Composable
private fun QuestGraphicalAnalysisCard(
    todaysTasks: List<DailyTask>,
    profile: UserProfile?,
    modifier: Modifier = Modifier
) {
    var selectedPeriod by remember { mutableStateOf(AnalysisPeriod.DAY) }
    val todayCompleted = remember(todaysTasks) { todaysTasks.count { it.isCompleted } }

    val daysLabels = remember {
        val sdf = SimpleDateFormat("EEE", Locale.getDefault())
        val cal = Calendar.getInstance()
        val list = mutableListOf<String>()
        for (i in 6 downTo 0) {
            val c = cal.clone() as Calendar
            c.add(Calendar.DAY_OF_YEAR, -i)
            list.add(sdf.format(c.time))
        }
        list
    }

    val weeksLabels = listOf("Wk -3", "Wk -2", "Wk -1", "Current")

    val monthsLabels = remember {
        val sdf = SimpleDateFormat("MMM", Locale.getDefault())
        val cal = Calendar.getInstance()
        val list = mutableListOf<String>()
        for (i in 5 downTo 0) {
            val c = cal.clone() as Calendar
            c.add(Calendar.MONTH, -i)
            list.add(sdf.format(c.time))
        }
        list
    }

    val joinDateMs = profile?.joinDateMs ?: System.currentTimeMillis()

    val dataPoints = remember(selectedPeriod, todayCompleted, joinDateMs) {
        when (selectedPeriod) {
            AnalysisPeriod.DAY -> {
                val cal = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                val startOfToday = cal.timeInMillis
                val mockDays = listOf(3f, 4f, 2f, 5f, 3f, 4f)
                
                val points = mutableListOf<Float>()
                for (i in 0 until 6) {
                    val dayStartMs = startOfToday - (6 - i) * 86400000L
                    if (dayStartMs + 86400000L < joinDateMs) {
                        points.add(0f)
                    } else {
                        points.add(mockDays[i])
                    }
                }
                points.add(todayCompleted.toFloat())
                points
            }
            AnalysisPeriod.WEEK -> {
                val cal = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
                val daysToSubtract = (dayOfWeek - Calendar.MONDAY + 7) % 7
                cal.add(Calendar.DAY_OF_YEAR, -daysToSubtract)
                val startOfWeek = cal.timeInMillis
                
                val mockWeeks = listOf(18f, 22f, 15f)
                val points = mutableListOf<Float>()
                for (j in 0 until 3) {
                    val weekStartMs = startOfWeek - (3 - j) * 7 * 86400000L
                    if (weekStartMs + 7 * 86400000L < joinDateMs) {
                        points.add(0f)
                    } else {
                        points.add(mockWeeks[j])
                    }
                }
                
                val currentWeekValue = if (joinDateMs >= startOfWeek) {
                    todayCompleted.toFloat()
                } else {
                    12f + todayCompleted.toFloat()
                }
                points.add(currentWeekValue)
                points
            }
            AnalysisPeriod.MONTH -> {
                val cal = Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_MONTH, 1)
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                
                val mockMonths = listOf(74f, 82f, 90f, 68f, 85f)
                val points = mutableListOf<Float>()
                for (k in 0 until 5) {
                    val c = cal.clone() as Calendar
                    c.add(Calendar.MONTH, -(5 - k))
                    c.add(Calendar.MONTH, 1)
                    val nextMonthStartMs = c.timeInMillis
                    
                    if (nextMonthStartMs < joinDateMs) {
                        points.add(0f)
                    } else {
                        points.add(mockMonths[k])
                    }
                }
                
                val currentMonthValue = if (joinDateMs >= cal.timeInMillis) {
                    todayCompleted.toFloat()
                } else {
                    55f + todayCompleted.toFloat()
                }
                points.add(currentMonthValue)
                points
            }
        }
    }

    val labels = when (selectedPeriod) {
        AnalysisPeriod.DAY -> daysLabels
        AnalysisPeriod.WEEK -> weeksLabels
        AnalysisPeriod.MONTH -> monthsLabels
    }

    val maxVal = remember(dataPoints) {
        (dataPoints.maxOrNull() ?: 10f).coerceAtLeast(6f)
    }

    RpgCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        glowColor = BorderNavy
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "QUEST ANALYSIS",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF888888),
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                    Spacer(Modifier.height(2.dp))
                    val averageText = when (selectedPeriod) {
                        AnalysisPeriod.DAY -> "${String.format("%.1f", dataPoints.average())} completed / day"
                        AnalysisPeriod.WEEK -> "${String.format("%.1f", dataPoints.average())} completed / week"
                        AnalysisPeriod.MONTH -> "${String.format("%.1f", dataPoints.average())} completed / month"
                    }
                    Text(
                        text = averageText,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier
                        .background(Color(0xFF0F0F0F), RoundedCornerShape(20.dp))
                        .border(0.5.dp, BorderNavy, RoundedCornerShape(20.dp))
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    AnalysisPeriod.values().forEach { period ->
                        val active = selectedPeriod == period
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(18.dp))
                                .background(if (active) Color(0xFF1F1F1F) else Color.Transparent)
                                .clickable { selectedPeriod = period }
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = when (period) {
                                    AnalysisPeriod.DAY -> "D"
                                    AnalysisPeriod.WEEK -> "W"
                                    AnalysisPeriod.MONTH -> "M"
                                },
                                style = MaterialTheme.typography.labelSmall,
                                color = if (active) Color.White else Color(0xFF666666),
                                fontWeight = if (active) FontWeight.Black else FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
            ) {
                val lineColor = when (selectedPeriod) {
                    AnalysisPeriod.DAY -> Color(0xFFAB47BC)
                    AnalysisPeriod.WEEK -> Color(0xFF42A5F5)
                    AnalysisPeriod.MONTH -> Color(0xFF66BB6A)
                }

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val width = size.width
                    val height = size.height

                    val gridLines = 4
                    for (i in 0 until gridLines) {
                        val y = height * (i.toFloat() / (gridLines - 1))
                        drawLine(
                            color = Color(0xFF1E1E1E),
                            start = Offset(0f, y),
                            end = Offset(width, y),
                            strokeWidth = 1f
                        )
                    }

                    val xSpacing = width / (dataPoints.size - 1)
                    val points = dataPoints.mapIndexed { idx, value ->
                        val x = idx * xSpacing
                        val y = height - (value / maxVal) * height
                        Offset(x, y)
                    }

                    val areaPath = Path().apply {
                        moveTo(0f, height)
                        points.forEachIndexed { index, point ->
                            if (index == 0) {
                                lineTo(point.x, point.y)
                            } else {
                                val prevPoint = points[index - 1]
                                val control1 = Offset(prevPoint.x + xSpacing / 2f, prevPoint.y)
                                val control2 = Offset(point.x - xSpacing / 2f, point.y)
                                cubicTo(control1.x, control1.y, control2.x, control2.y, point.x, point.y)
                            }
                        }
                        lineTo(width, height)
                        close()
                    }

                    drawPath(
                        path = areaPath,
                        brush = Brush.verticalGradient(
                            colors = listOf(lineColor.copy(alpha = 0.25f), Color.Transparent),
                            startY = 0f,
                            endY = height
                        )
                    )

                    val linePath = Path().apply {
                        points.forEachIndexed { index, point ->
                            if (index == 0) {
                                moveTo(point.x, point.y)
                            } else {
                                val prevPoint = points[index - 1]
                                val control1 = Offset(prevPoint.x + xSpacing / 2f, prevPoint.y)
                                val control2 = Offset(point.x - xSpacing / 2f, point.y)
                                cubicTo(control1.x, control1.y, control2.x, control2.y, point.x, point.y)
                            }
                        }
                    }

                    drawPath(
                        path = linePath,
                        color = lineColor.copy(alpha = 0.3f),
                        style = Stroke(width = 6f, cap = StrokeCap.Round)
                    )

                    drawPath(
                        path = linePath,
                        color = lineColor,
                        style = Stroke(width = 2.5f, cap = StrokeCap.Round)
                    )

                    points.forEachIndexed { index, point ->
                        val isCurrent = index == points.size - 1
                        
                        drawCircle(
                            color = Color.Black,
                            radius = 6f,
                            center = point
                        )

                        drawCircle(
                            color = lineColor,
                            radius = 4f,
                            center = point
                        )

                        if (isCurrent) {
                            drawCircle(
                                color = lineColor.copy(alpha = 0.35f),
                                radius = 8f,
                                style = Stroke(width = 2f),
                                center = point
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                labels.forEach { label ->
                    Text(
                        text = label.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF666666),
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun QuestTrackerCard(done: Int, total: Int) {
    val progress = if (total > 0) done.toFloat() / total.toFloat() else 0f
    val animProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000, easing = FastOutSlowInEasing),
        label = "questTrackerProgress"
    )

    val goldGlowTransition = rememberInfiniteTransition(label = "questTrackerGlow")
    val goldOffset by goldGlowTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(3000), RepeatMode.Restart),
        label = "questTrackerOffset"
    )

    val progressBrush = Brush.linearGradient(
        colors = listOf(Color(0xFFFFA000), Color(0xFFFFD700), Color(0xFFFFA000)),
        start = Offset(goldOffset * 500f - 250f, 0f),
        end = Offset(goldOffset * 500f + 250f, 0f)
    )

    val infiniteTransition = rememberInfiniteTransition(label = "indicatorPulse")
    val indicatorAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "indicatorAlpha"
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = CardNavy),
        border = BorderStroke(1.dp, BorderNavy)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = "CAMPAIGN TRACKER",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF888888),
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(SuccessGreen.copy(alpha = indicatorAlpha))
                        )
                        Text(
                            text = "MISSION ACTIVE",
                            style = MaterialTheme.typography.labelSmall,
                            color = SuccessGreen,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.5.sp
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "$done / $total COMPLETED",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "${(progress * 100).toInt()}% PROGRESS",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFFFD54F),
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(Color(0xFF141414), RoundedCornerShape(4.dp))
                    .border(0.5.dp, Color(0xFF2E2E2E), RoundedCornerShape(4.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(animProgress)
                        .fillMaxHeight()
                        .background(progressBrush, RoundedCornerShape(4.dp))
                )
            }
        }
    }
}

@Composable
private fun MotivationalQuote() {
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

private class FireParticle(
    var x: Float,
    var y: Float,
    val vx: Float,
    val vy: Float,
    val maxLife: Float,
    var life: Float = maxLife,
    val baseSize: Float,
    val color: Color,
    val isSpark: Boolean = false
)
