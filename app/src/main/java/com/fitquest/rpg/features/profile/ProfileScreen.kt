package com.fitquest.rpg.features.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.repository.UserRepository
import com.fitquest.rpg.core.domain.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import com.fitquest.rpg.ui.components.RankBadge
import com.fitquest.rpg.ui.theme.*

// ─── ViewModel ──────────────────────────────────────────────────────────────

data class ProfileUiState(
    val profile: UserProfile? = null,
    val attributes: List<Attribute> = emptyList(),
    val economy: Economy = Economy()
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepo: UserRepository
) : ViewModel() {
    val uiState: StateFlow<ProfileUiState> = combine(
        userRepo.observeProfile(),
        userRepo.observeAttributes(),
        userRepo.observeEconomy()
    ) { profile, attrs, economy ->
        ProfileUiState(profile, attrs.sortedBy { it.type.ordinal }, economy ?: Economy())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProfileUiState())
}

// ─── Screen ─────────────────────────────────────────────────────────────────

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val profile = state.profile ?: return
    val economy = state.economy

    val overallRank = if (state.attributes.isEmpty()) Rank.BRONZE_RECRUIT
    else Rank.fromLevel(state.attributes.map { it.level }.average().toInt())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepNavy)
            .verticalScroll(rememberScrollState())
    ) {
        // Hero section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFF14103A), Color.Transparent)
                    )
                )
                .padding(top = 48.dp, start = 20.dp, end = 20.dp, bottom = 24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                // Avatar circle
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.radialGradient(
                                listOf(NeonPurple.copy(0.4f), Color(0xFF14103A))
                            )
                        )
                        .border(2.dp, NeonPurple, CircleShape)
                ) {
                    Text("⚔️", fontSize = 40.sp)
                }

                Spacer(Modifier.height(12.dp))
                Text(profile.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Black)
                Spacer(Modifier.height(6.dp))
                RankBadge(rank = overallRank)
            }
        }

        // Stats grid
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            StatCard("⚡ AP Earned", "${economy.totalActionPoints}", GoldAP, Modifier.weight(1f))
            StatCard("🔥 Best Streak", "${economy.longestStreak} days", Color(0xFFFF8A65), Modifier.weight(1f))
            StatCard("💰 AP Spent", "${economy.totalSpent}", NeonPurple, Modifier.weight(1f))
        }

        Spacer(Modifier.height(20.dp))

        // Profile details
        Card(
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardNavy),
            border = BorderStroke(1.dp, BorderNavy)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("👤 PROFILE", style = MaterialTheme.typography.titleMedium, color = NeonPurple, letterSpacing = 2.sp)
                HorizontalDivider(color = BorderNavy)
                ProfileRow("Age", "${profile.age} years")
                ProfileRow("Height", "${profile.heightCm.toInt()} cm")
                ProfileRow("Weight", "${profile.weightKg.toInt()} kg")
                ProfileRow("BMI", "%.1f".format(profile.bmi))
                ProfileRow("Fitness Level", profile.fitnessLevel.displayName)
                ProfileRow("Primary Goal", profile.primaryGoal.displayName)
                ProfileRow("Diet Style", profile.dietaryStyle.displayName)
                ProfileRow("Training Days", "${profile.workoutDaysPerWeek}x / week")
                ProfileRow("Phase", profile.transformationPhase.displayName)
                ProfileRow("Training Week", "Week ${profile.trainingWeekNumber}")
            }
        }

        Spacer(Modifier.height(80.dp))
    }
}

@Composable
private fun StatCard(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = CardNavy),
        border = BorderStroke(1.dp, color.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, style = MaterialTheme.typography.titleLarge, color = color, fontWeight = FontWeight.Black)
            Spacer(Modifier.height(2.dp))
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }
    }
}

@Composable
private fun ProfileRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.SemiBold)
    }
}
