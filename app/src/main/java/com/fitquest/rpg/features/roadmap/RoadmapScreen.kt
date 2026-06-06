package com.fitquest.rpg.features.roadmap

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.repository.UserRepository
import com.fitquest.rpg.core.domain.model.*
import com.fitquest.rpg.ui.components.*
import com.fitquest.rpg.ui.theme.*
import com.fitquest.rpg.core.data.remote.SupabaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

// ─── ViewModel ──────────────────────────────────────────────────────────────

@HiltViewModel
class RoadmapViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val auth: SupabaseAuth
) : ViewModel() {

    data class RoadmapUiState(
        val overallRank: Rank = Rank.BRONZE_RECRUIT,
        val globalLevel: Int = 1,
        val loaded: Boolean = false
    )

    val uiState: StateFlow<RoadmapUiState> = run {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            MutableStateFlow(RoadmapUiState())
        } else {
            userRepo.observeAttributes(uid).map { attrs ->
                val globalLevel = if (attrs.isEmpty()) 1
                else XpAlgorithm.globalLevelFromTotalXp(attrs.sumOf { it.totalXpEarned }).first
                RoadmapUiState(
                    overallRank = Rank.fromLevel(globalLevel),
                    globalLevel = globalLevel,
                    loaded = true
                )
            }.catch { emit(RoadmapUiState()) }
             .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RoadmapUiState())
        }
    }
}

// ─── Screen ─────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoadmapScreen(
    onBack: () -> Unit,
    viewModel: RoadmapViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val rankColor = Color(state.overallRank.colorHex)
    
    // Smooth transition for rankColor
    val animatedRankColor by animateColorAsState(
        targetValue = rankColor,
        animationSpec = tween(1000, easing = FastOutSlowInEasing),
        label = "animatedRankColor"
    )
    val density = LocalDensity.current
    
    val slotHeight = 140.dp
    val totalHeight = 1400.dp
    
    // Ranks from bottom (Bronze Recruit) to top (Arise)
    val ranks = remember { Rank.values().reversed() }
    
    // Gradient colors for completed section background tint (alpha 0.15f for distinct split)
    val gradientColors = remember {
        listOf(
            Color(0xFFFFD700), // Arise (Gold)
            Color(0xFF7E57C2), // Shadow Monarch (Indigo)
            Color(0xFFEF5350), // Mythic Raider (Red)
            Color(0xFFFF8A65), // Platinum Hunter (Orange)
            Color(0xFFFFD54F), // Gold Shadow (Yellow)
            Color(0xFFAB47BC), // Diamond Sentinel (Purple)
            Color(0xFF42A5F5), // Crystal Knight (Blue)
            Color(0xFF66BB6A), // Steel Warrior (Green)
            Color(0xFFB0BEC5), // Iron Soldier (Grey)
            Color(0xFFCD7F32)  // Bronze Recruit (Brown)
        ).map { it.copy(alpha = 0.15f) }
    }
    
    // Piecewise level Y calculation matching slot alignment
    val levelY = remember(state.globalLevel) {
        val level = state.globalLevel.coerceIn(1, 100)
        if (level == 100) {
            0.dp
        } else {
            val tierIndex = 9 - (level / 10).coerceAtMost(9)
            val levelInTier = level % 10
            val fraction = levelInTier / 10f
            ((tierIndex + 1) * 140f - fraction * 140f).dp
        }
    }
    
    val scrollState = rememberScrollState()
    
    // Smoothly scroll to center the player's level when loaded
    LaunchedEffect(state.loaded) {
        if (state.loaded) {
            val targetScrollDp = (levelY + 24.dp - 240.dp).coerceAtLeast(0.dp)
            val targetScrollPx = with(density) { targetScrollDp.toPx() }
            scrollState.animateScrollTo(targetScrollPx.toInt(), tween(1200, easing = FastOutSlowInEasing))
        }
    }
    
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "ASCENSION ROADMAP",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            letterSpacing = 1.5.sp
                        )
                        Text(
                            text = "TIER PROGRESSION ARCHIVE",
                            style = MaterialTheme.typography.labelSmall,
                            color = animatedRankColor,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black)
        ) {
            // Scrollable Progression Track
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(totalHeight)
                    ) {
                        // Background overlay for completed area tint and current position line
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(totalHeight)
                        ) {
                            val currentY = levelY.toPx()
                            
                            // Completed lower side smooth vertical gradient background
                            val completedBrush = Brush.verticalGradient(
                                colors = gradientColors,
                                startY = 0f,
                                endY = size.height
                            )
                            
                            drawRect(
                                brush = completedBrush,
                                topLeft = Offset(0f, currentY),
                                size = androidx.compose.ui.geometry.Size(size.width, size.height - currentY)
                            )
                            
                            // Current stage horizontal indicator line
                            drawLine(
                                color = animatedRankColor,
                                start = Offset(0f, currentY),
                                end = Offset(size.width, currentY),
                                strokeWidth = 2.dp.toPx()
                            )
                        }
                        
                        // Content Row containing Ruler (Left) and Tier Cards (Right)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(totalHeight)
                        ) {
                            // LEFT COLUMN: Ruler
                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .fillMaxHeight()
                            ) {
                                Canvas(modifier = Modifier.fillMaxSize()) {
                                    val rulerX = 60.dp.toPx()
                                    val currentY = levelY.toPx()
                                    
                                    // Uncompleted track (thin grey line)
                                    drawLine(
                                        color = Color(0xFF222222),
                                        start = Offset(rulerX, 0f),
                                        end = Offset(rulerX, currentY),
                                        strokeWidth = 2.dp.toPx()
                                    )
                                    
                                    // Completed track (glowing rank color line)
                                    drawLine(
                                        color = animatedRankColor,
                                        start = Offset(rulerX, currentY),
                                        end = Offset(rulerX, size.height),
                                        strokeWidth = 4.dp.toPx(),
                                        cap = StrokeCap.Round
                                    )
                                    
                                    // Tick lines at boundaries
                                    for (i in 0..10) {
                                        val tickY = i * 140.dp.toPx()
                                        drawLine(
                                            color = if (tickY >= currentY) animatedRankColor else Color(0xFF333333),
                                            start = Offset(rulerX - 8.dp.toPx(), tickY),
                                            end = Offset(rulerX, tickY),
                                            strokeWidth = 1.5.dp.toPx()
                                        )
                                    }
                                }
                                
                                // Text labels overlayed at tick levels
                                for (i in 0..10) {
                                    val levelNum = 100 - i * 10
                                    val displayNum = if (levelNum == 0) 1 else levelNum
                                    val labelColor = if ((i * 140).dp >= levelY) animatedRankColor else Color(0xFF666666)
                                    
                                    Text(
                                        text = "LV $displayNum",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = labelColor,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .offset(x = 12.dp, y = (i * 140).dp - 6.dp)
                                    )
                                }
                            }
                            
                            // RIGHT COLUMN: Tier Cards
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                ranks.forEach { rank ->
                                    val isCurrent = (rank == state.overallRank)
                                    val tierColor = Color(rank.colorHex)
                                    
                                    Box(
                                        modifier = Modifier
                                            .height(slotHeight)
                                            .fillMaxWidth()
                                            .padding(end = 16.dp, top = 8.dp, bottom = 8.dp),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Card(
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = CardDefaults.cardColors(containerColor = CardNavy),
                                            border = BorderStroke(
                                                width = if (isCurrent) 1.5.dp else 1.dp,
                                                color = if (isCurrent) tierColor else BorderNavy
                                            ),
                                            shape = RoundedCornerShape(8.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = rank.iconResId()),
                                                    contentDescription = null,
                                                    tint = Color.Unspecified,
                                                    modifier = Modifier.size(52.dp)
                                                )
                                                
                                                Column(modifier = Modifier.weight(1f)) {
                                                    Row(
                                                        verticalAlignment = Alignment.CenterVertically,
                                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                                    ) {
                                                        Text(
                                                            text = rank.title.uppercase(),
                                                            style = MaterialTheme.typography.labelLarge,
                                                            color = tierColor,
                                                            fontWeight = FontWeight.Black,
                                                            letterSpacing = 0.5.sp
                                                        )
                                                        if (isCurrent) {
                                                            Box(
                                                                modifier = Modifier
                                                                    .background(tierColor.copy(alpha = 0.08f), RoundedCornerShape(3.dp))
                                                                    .border(0.5.dp, tierColor.copy(alpha = 0.4f), RoundedCornerShape(3.dp))
                                                                    .padding(horizontal = 6.dp, vertical = 1.dp)
                                                            ) {
                                                                Text(
                                                                    text = "CURRENT",
                                                                    style = MaterialTheme.typography.labelSmall,
                                                                    color = tierColor,
                                                                    fontWeight = FontWeight.Black,
                                                                    fontSize = 8.sp,
                                                                    letterSpacing = 0.5.sp
                                                                )
                                                            }
                                                        }
                                                    }
                                                    Text(
                                                        text = "LEVELS ${rank.minLevel} - ${if (rank.minLevel == 90) "100" else (rank.minLevel + 9)}",
                                                        style = MaterialTheme.typography.labelMedium,
                                                        color = Color(0xFF888888),
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                    Spacer(Modifier.height(4.dp))
                                                    Text(
                                                        text = getRankDesc(rank),
                                                        style = MaterialTheme.typography.bodySmall,
                                                        color = Color(0xFF666666),
                                                        lineHeight = 13.sp
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        
                        // HUD current position floating indicator badge
                        Box(
                            modifier = Modifier
                                .offset(x = 92.dp, y = levelY - 10.dp)
                                .background(animatedRankColor, RoundedCornerShape(4.dp))
                                .border(0.5.dp, Color.White, RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "YOU (LV ${state.globalLevel})",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Black,
                                fontWeight = FontWeight.Black,
                                fontSize = 9.sp,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun getRankDesc(rank: Rank): String = when (rank) {
    Rank.BRONZE_RECRUIT -> "Newly enlisted seeker. The journey begins."
    Rank.IRON_SOLDIER -> "Standard iron armament. Frontline troop."
    Rank.STEEL_WARRIOR -> "Hardened steel gear. Proficient in combat."
    Rank.CRYSTAL_KNIGHT -> "Shining armor imbued with magical crystals."
    Rank.DIAMOND_SENTINEL -> "Unbreakable defense and high resilience."
    Rank.GOLD_SHADOW -> "Master of stealth, agility, and precise strikes."
    Rank.PLATINUM_HUNTER -> "Elite status. Top tier mercenary ranks."
    Rank.MYTHIC_RAIDER -> "Conquer legendary dungeons and raid bosses."
    Rank.SHADOW_MONARCH -> "Control the shadows. Death is but a command."
    Rank.ARISE -> "The apex level of ascension. Infinite authority."
}
