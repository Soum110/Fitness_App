package com.fitquest.rpg.features.attributes

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.fitquest.rpg.core.domain.model.*
import com.fitquest.rpg.ui.components.*
import com.fitquest.rpg.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttributesScreen(
    onBack: () -> Unit,
    viewModel: AttributesViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(containerColor = DeepNavy) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Column(modifier = Modifier.padding(20.dp)) {
                Text("📊 ATTRIBUTES", style = MaterialTheme.typography.headlineLarge, letterSpacing = 2.sp)
                Text("Track your growth across all dimensions.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            // Rank display
            state.overallRank?.let { rank ->
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    RankBadge(rank = rank, modifier = Modifier.fillMaxWidth())
                }
            }

            Spacer(Modifier.height(16.dp))

            // XP milestones banner
            RpgCard(modifier = Modifier.padding(horizontal = 20.dp), glowColor = NeonGold) {
                Text("🏆 PROGRESSION MILESTONES", style = MaterialTheme.typography.labelLarge, color = NeonGold, letterSpacing = 2.sp)
                Spacer(Modifier.height(8.dp))
                val milestones = listOf(
                    10 to "3 weeks" to "🔘 Iron Soldier",
                    25 to "2 months" to "🟢 Steel Warrior",
                    50 to "6 months" to "🟡 Gold Shadow",
                    75 to "12 months" to "⚡ Shadow Monarch",
                    100 to "~20 months" to "👑 ARISE"
                )
                milestones.forEach { (levelTime, rank) ->
                    val (level, time) = levelTime
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Lv.$level — $rank", style = MaterialTheme.typography.bodyMedium)
                        Text(time, style = MaterialTheme.typography.bodyMedium, color = NeonGold.copy(0.7f))
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

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
}

@Composable
private fun DetailedAttributeCard(attribute: Attribute, modifier: Modifier = Modifier) {
    val attrColor = Color(attribute.type.color)
    val animProgress by animateFloatAsState(
        targetValue = attribute.progressFraction,
        animationSpec = tween(1200, easing = FastOutSlowInEasing),
        label = "attrBar"
    )

    RpgCard(modifier = modifier, glowColor = attrColor) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            // Big emoji circle
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        brush = Brush.radialGradient(
                            listOf(attrColor.copy(0.3f), Color.Transparent)
                        ),
                        shape = RoundedCornerShape(28.dp)
                    )
                    .border(1.dp, attrColor.copy(0.5f), RoundedCornerShape(28.dp))
            ) {
                Text(attribute.type.emoji, fontSize = 26.sp)
            }

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(attribute.type.displayName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(
                        "Lv.${attribute.level}",
                        style = MaterialTheme.typography.titleMedium,
                        color = attrColor,
                        fontWeight = FontWeight.Black
                    )
                }

                Spacer(Modifier.height(6.dp))

                // XP bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .background(Color.White.copy(0.08f), RoundedCornerShape(4.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(animProgress)
                            .fillMaxHeight()
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(attrColor.copy(0.7f), attrColor)
                                ),
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                }

                Spacer(Modifier.height(4.dp))

                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "${attribute.currentXp} / ${attribute.xpForNextLevel} XP",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        attribute.rank.title,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(attribute.rank.colorHex)
                    )
                }
            }
        }
    }
}
