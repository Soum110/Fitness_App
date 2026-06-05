package com.fitquest.rpg.features.profile

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.repository.UserRepository
import com.fitquest.rpg.core.domain.model.*
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import androidx.compose.ui.res.painterResource
import com.fitquest.rpg.ui.components.*
import com.fitquest.rpg.ui.theme.*

// ─── ViewModel ──────────────────────────────────────────────────────────────

data class ProfileUiState(
    val profile: UserProfile? = null,
    val attributes: List<Attribute> = emptyList(),
    val economy: Economy = Economy(),
    val email: String = ""
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    val uiState: StateFlow<ProfileUiState> = run {
        val uid = auth.currentUser?.uid
        val email = auth.currentUser?.email ?: ""
        if (uid == null) {
            MutableStateFlow(ProfileUiState())
        } else {
            combine(
                userRepo.observeProfile(uid),
                userRepo.observeAttributes(uid),
                userRepo.observeEconomy(uid)
            ) { profile, attrs, economy ->
                ProfileUiState(profile, attrs.sortedBy { it.type.ordinal }, economy ?: Economy(), email)
            }.catch { emit(ProfileUiState()) }
             .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProfileUiState())
        }
    }
}

// ─── Screen ─────────────────────────────────────────────────────────────────

@Composable
fun ProfileScreen(
    onLogout: () -> Unit = {},
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var showLogoutDialog by remember { mutableStateOf(false) }

    // Show loading if profile not yet loaded
    if (state.profile == null) {
        Box(Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
        }
        return
    }

    val profile = state.profile!!
    val economy = state.economy
    val overallRank = if (state.attributes.isEmpty()) Rank.BRONZE_RECRUIT
    else Rank.fromLevel(state.attributes.map { it.level }.average().toInt())

    val globalLevel = if (state.attributes.isEmpty()) 1
    else state.attributes.map { it.level }.average().toInt()

    val globalProgressFraction = if (state.attributes.isEmpty()) 0f
    else state.attributes.map { it.progressFraction }.average().toFloat()

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            containerColor = CardNavy,
            shape = RoundedCornerShape(8.dp),
            title = { Text("Sign Out?", color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold) },
            text = {
                Text(
                    "Your progress is safely stored in the cloud. You can log back in anytime from any device.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            confirmButton = {
                Button(
                    onClick = { showLogoutDialog = false; onLogout() },
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935), contentColor = Color.White)
                ) { Text("Sign Out", fontWeight = FontWeight.Bold) }
            },
            dismissButton = {
                TextButton(
                    onClick = { showLogoutDialog = false },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        // Hero section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 20.dp, end = 20.dp, bottom = 24.dp)
        ) {
            // Logout button top-right
            IconButton(
                onClick = { showLogoutDialog = true },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Sign Out", tint = Color(0xFF888888))
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                val rankColor = Color(overallRank.colorHex)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(96.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(CardNavy)
                        .border(1.dp, rankColor.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = overallRank.iconResId()),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                
                Spacer(Modifier.height(16.dp))
                
                Text(
                    text = profile.name.uppercase(),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    letterSpacing = 1.sp
                )
                if (state.email.isNotEmpty()) {
                    Spacer(Modifier.height(4.dp))
                    Text(state.email, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                
                Spacer(Modifier.height(16.dp))
                
                // Constant filling global level container (Full Width and RPG Styled)
                val animatedProgress by animateFloatAsState(
                    targetValue = globalProgressFraction,
                    animationSpec = tween(1000, easing = FastOutSlowInEasing),
                    label = "profileGlobalXpProgress"
                )
                
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(CardNavy)
                        .border(1.dp, BorderNavy, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    // Fill background
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
                    
                    // Level content inside the bar
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(rankColor.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                                    .border(0.5.dp, rankColor.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "LEVEL",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = rankColor,
                                    fontWeight = FontWeight.Black,
                                    letterSpacing = 1.sp
                                )
                            }
                            Text(
                                text = "$globalLevel",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Black
                            )
                        }
                        
                        Text(
                            text = "${(globalProgressFraction * 100).toInt()}% XP PROGRESS",
                            style = MaterialTheme.typography.labelMedium,
                            color = rankColor,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }
        }

        // Stats grid
        Row(modifier = Modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            StatCard(
                label = "AP Earned",
                value = "${economy.totalActionPoints}",
                color = GoldAP,
                iconResId = com.fitquest.rpg.R.drawable.ic_energy,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                label = "Best Streak",
                value = "${economy.longestStreak} days",
                color = Color(0xFFFF8A65),
                iconResId = com.fitquest.rpg.R.drawable.ic_streak,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                label = "AP Spent",
                value = "${economy.totalSpent}",
                color = NeonPurple,
                iconResId = com.fitquest.rpg.R.drawable.ic_gift,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(20.dp))

        // RPG Character Sheet section
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Section Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_profile),
                    contentDescription = null,
                    tint = NeonPurple,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = "HUNTER DATASHEET",
                    style = MaterialTheme.typography.titleMedium,
                    color = NeonPurple,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            }

            // 1. PHYSIQUE ATTRIBUTES (2x2 Grid)
            RpgCard(glowColor = BorderNavy) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "PHYSIQUE VITALITY LOG",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF888888),
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "SYS_ID: #${profile.id}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF444444),
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SpecTile("FRAME HEIGHT", "${profile.heightCm.toInt()} CM", NeonBlue, Modifier.weight(1f))
                    SpecTile("BODY WEIGHT", "${profile.weightKg.toInt()} KG", NeonBlue, Modifier.weight(1f))
                }
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SpecTile("CHRONO AGE", "${profile.age} YRS", NeonBlue, Modifier.weight(1f))
                    val bmiVal = profile.bmi
                    val bmiCategory = when {
                        bmiVal < 18.5f -> "UNDERWEIGHT"
                        bmiVal < 25f -> "NORMAL"
                        bmiVal < 30f -> "OVERWEIGHT"
                        else -> "OBESE"
                    }
                    SpecTile("BODY MASS INDEX", "%.1f ($bmiCategory)".format(bmiVal), NeonBlue, Modifier.weight(1f))
                }
            }

            // 2. HUNTER SPECIALIZATION
            RpgCard(glowColor = BorderNavy) {
                Text(
                    text = "HUNTER SPECIALIZATION",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF888888),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    val rankColor = Color(overallRank.colorHex)
                    val cleanClass = profile.fitnessLevel.displayName.replace(Regex("[\\p{So}\\p{Cn}]"), "").trim()
                    val cleanGoal = profile.primaryGoal.displayName.replace(Regex("[\\p{So}\\p{Cn}]"), "").trim()
                    val cleanPhase = profile.transformationPhase.displayName.replace(Regex("[\\p{So}\\p{Cn}]"), "").trim()
                    
                    CharacterRow("Class Rating", cleanClass, NeonBlue, com.fitquest.rpg.R.drawable.ic_rank)
                    CharacterRow("Tactical Target", cleanGoal, NeonGold, com.fitquest.rpg.R.drawable.ic_quest)
                    CharacterRow("Transformation Phase", cleanPhase, rankColor, com.fitquest.rpg.R.drawable.ic_tips)
                }
                
                Spacer(Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF070707), RoundedCornerShape(6.dp))
                        .border(0.5.dp, BorderNavy, RoundedCornerShape(6.dp))
                        .padding(10.dp)
                ) {
                    Column {
                        Text(
                            text = "PHASE PROTOCOL DESCRIPTION",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF666666),
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = profile.transformationPhase.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFBBBBBB)
                        )
                    }
                }
            }

            // 3. CAMPAIGN REGIMEN
            RpgCard(glowColor = BorderNavy) {
                Text(
                    text = "CAMPAIGN REGIMEN",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF888888),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    val cleanDiet = profile.dietaryStyle.displayName.replace(Regex("[\\p{So}\\p{Cn}]"), "").trim()
                    CharacterRow("Alchemical Fuel", cleanDiet, Color(0xFF66BB6A), com.fitquest.rpg.R.drawable.ic_diet)
                    CharacterRow("Weekly Engagements", "${profile.workoutDaysPerWeek} Sessions", Color(0xFF42A5F5), com.fitquest.rpg.R.drawable.ic_timer)
                    CharacterRow("Campaign Chronicle", "Week ${profile.trainingWeekNumber}", Color.White, com.fitquest.rpg.R.drawable.ic_streak)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Sign out button
        OutlinedButton(
            onClick = { showLogoutDialog = true },
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            border = BorderStroke(1.dp, Color(0xFFD32F2F).copy(alpha = 0.4f)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null, tint = Color(0xFFD32F2F), modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("Sign Out", color = Color(0xFFD32F2F))
        }

        Spacer(Modifier.height(80.dp))
    }
}

@Composable
private fun StatCard(
    label: String,
    value: String,
    color: Color,
    iconResId: Int? = null,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = CardNavy),
        border = BorderStroke(1.dp, color.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, style = MaterialTheme.typography.titleLarge, color = color, fontWeight = FontWeight.Black)
            Spacer(Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (iconResId != null) {
                    Icon(
                        painter = painterResource(id = iconResId),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(12.dp)
                    )
                }
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun SpecTile(
    label: String,
    value: String,
    accentColor: Color = BorderNavy,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color(0xFF070707), RoundedCornerShape(6.dp))
            .border(0.5.dp, BorderNavy, RoundedCornerShape(6.dp))
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .fillMaxHeight()
                    .background(accentColor)
            )
            Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
                Text(label, style = MaterialTheme.typography.labelSmall, color = Color(0xFF666666), letterSpacing = 0.5.sp)
                Spacer(Modifier.height(2.dp))
                Text(value, style = MaterialTheme.typography.bodyMedium, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun CharacterRow(
    label: String,
    value: String,
    valueColor: Color,
    iconResId: Int? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF070707), RoundedCornerShape(6.dp))
            .border(0.5.dp, BorderNavy, RoundedCornerShape(6.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (iconResId != null) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = Color(0xFF888888),
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
        }
        
        Box(
            modifier = Modifier
                .background(valueColor.copy(alpha = 0.08f), RoundedCornerShape(4.dp))
                .border(0.5.dp, valueColor.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = value.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = valueColor,
                fontWeight = FontWeight.Black,
                letterSpacing = 0.5.sp
            )
        }
    }
}
