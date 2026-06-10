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
import com.fitquest.rpg.core.data.repository.TaskRepository
import com.fitquest.rpg.core.data.repository.RewardCardRepository
import com.fitquest.rpg.core.data.remote.SupabaseRepository
import com.fitquest.rpg.core.data.local.FitQuestDatabase
import com.fitquest.rpg.core.domain.model.*
import com.fitquest.rpg.core.data.remote.SupabaseAuth
import androidx.compose.ui.text.input.PasswordVisualTransformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import androidx.compose.ui.res.painterResource
import com.fitquest.rpg.ui.components.*
import com.fitquest.rpg.ui.theme.*
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import kotlinx.coroutines.withContext
import androidx.compose.ui.geometry.Rect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    private val taskRepo: TaskRepository,
    private val rewardCardRepo: RewardCardRepository,
    private val supabaseRepo: SupabaseRepository,
    private val db: FitQuestDatabase,
    private val auth: SupabaseAuth
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

    fun resetProgress(onSuccess: (String?) -> Unit, onFailure: (String) -> Unit) {
        val user = auth.currentUser
        val uid = user?.uid
        if (uid == null) {
            onFailure("No user is currently signed in.")
            return
        }

        viewModelScope.launch {
            try {
                // 1. Stop all active sync jobs
                userRepo.stopSync()
                taskRepo.stopSync()
                rewardCardRepo.stopSync()

                // 2. Clear remote data in Supabase (while auth token is still valid)
                supabaseRepo.deleteUserData(uid)

                // 3. Clear local cache
                withContext(Dispatchers.IO) {
                    db.clearAllTables()
                }

                // 4. Sign out since database progress was successfully cleared
                auth.signOut()

                onSuccess(null)
            } catch (e: Exception) {
                onFailure(e.localizedMessage ?: "Failed to reset progress.")
            }
        }
    }

    fun updateProfile(profile: UserProfile) {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            try {
                userRepo.saveProfile(uid, profile)
                taskRepo.regenerateWorkoutTasksForToday(uid, profile)
            } catch (e: Exception) {
                // ignore
            }
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
    var showDeleteDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Onboarding Tutorial States
    var showTutorial by remember { mutableStateOf(false) }
    var tutorialStep by remember { mutableStateOf(0) }
    val tutorialAnchors = remember { mutableStateMapOf<String, Rect>() }

    LaunchedEffect(state.profile) {
        if (state.profile != null) {
            showTutorial = !TutorialManager.isTutorialCompleted(context, "profile")
        }
    }

    if (state.profile == null) {
        Box(Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                com.fitquest.rpg.ui.components.FitQuestLoadingSpinner(size = 60.dp, accentColor = SuccessGreen)
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "SYNCHRONIZING PROFILE...",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    letterSpacing = 2.sp
                )
            }
        }
        return
    }

    val profile = state.profile!!
    val economy = state.economy
    val globalLevelPair = if (state.attributes.isEmpty()) Pair(1, 0f)
    else XpAlgorithm.globalLevelFromTotalXp(state.attributes.sumOf { it.totalXpEarned })

    val globalLevel = globalLevelPair.first
    val globalProgressFraction = globalLevelPair.second
    val overallRank = Rank.fromLevel(globalLevel)

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

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { 
                showDeleteDialog = false
            },
            containerColor = CardNavy,
            shape = RoundedCornerShape(8.dp),
            title = { Text("Clear Progress & Reset?", color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold) },
            text = {
                Text(
                    "This action is permanent and cannot be undone. All your levels, daily quests, action points, and store history will be permanently deleted from the database. You will be signed out and must set up your requirements again.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDeleteDialog = false
                        viewModel.resetProgress(
                            onSuccess = { message ->
                                if (message != null) {
                                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(context, "All progress reset successfully. Please log back in to set up your profile.", Toast.LENGTH_LONG).show()
                                }
                                onLogout()
                            },
                            onFailure = { error ->
                                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                            }
                        )
                    },
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935), contentColor = Color.White)
                ) {
                    Text("Reset Progress")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { 
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    val scrollState = rememberScrollState()

    LaunchedEffect(tutorialStep, showTutorial, tutorialAnchors) {
        if (showTutorial) {
            val steps = listOf(
                TutorialStep("profile_hud", "Character Overview", "Displays your level, XP progression, and current rank. Competing quests increases this progress bar."),
                TutorialStep("physique_grid", "Physique Attributes", "Your physical stats, including height, weight, target, and BMI. Essential metrics calculated for your quests."),
                TutorialStep("delete_registry", "Registry Wipe", "Delete your profile registry from the database to start completely fresh with new goals, etc.")
            )
            val step = steps.getOrNull(tutorialStep)
            val anchorRect = tutorialAnchors[step?.anchorKey]
            val rootRect = tutorialAnchors["screen_root"]
            if (anchorRect != null && rootRect != null) {
                val elemY = anchorRect.top
                val rootY = rootRect.top
                val currentScroll = scrollState.value
                val targetScroll = (elemY - rootY + currentScroll - 150).toInt()
                try {
                    scrollState.animateScrollTo(targetScroll.coerceIn(0, scrollState.maxValue))
                } catch (e: Exception) {}
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .tutorialAnchor("screen_root", tutorialAnchors)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(scrollState)
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
                        .border(1.dp, BorderNavy, RoundedCornerShape(8.dp))
                        .tutorialAnchor("profile_hud", tutorialAnchors),
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
            RpgCard(
                glowColor = BorderNavy,
                modifier = Modifier.tutorialAnchor("physique_grid", tutorialAnchors)
            ) {
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

            // 4. TRAINING ZONE SETTINGS
            RpgCard(glowColor = BorderNavy) {
                Text(
                    text = "TRAINING ZONE SETTINGS",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF888888),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(Modifier.height(10.dp))
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
                            painter = painterResource(
                                if (profile.workoutLocation == WorkoutLocation.GYM) com.fitquest.rpg.R.drawable.ic_strength
                                else com.fitquest.rpg.R.drawable.ic_timer
                            ),
                            contentDescription = null,
                            tint = NeonGold,
                            modifier = Modifier.size(18.dp)
                        )
                        Column {
                            Text(
                                text = "Workout Location",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if (profile.workoutLocation == WorkoutLocation.GYM) "Gym Workouts Enabled" else "Bodyweight Workouts Enabled",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF888888)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .background(Color(0xFF0C0C0C), RoundedCornerShape(4.dp))
                            .border(0.5.dp, BorderNavy, RoundedCornerShape(4.dp))
                            .padding(2.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        listOf(WorkoutLocation.HOME, WorkoutLocation.GYM).forEach { loc ->
                            val isSelected = profile.workoutLocation == loc
                            val bg = if (isSelected) SuccessGreen.copy(alpha = 0.15f) else Color.Transparent
                            val border = if (isSelected) SuccessGreen.copy(alpha = 0.4f) else Color.Transparent
                            val textCol = if (isSelected) Color.White else Color(0xFF888888)
                            Box(
                                modifier = Modifier
                                    .background(bg, RoundedCornerShape(3.dp))
                                    .border(0.5.dp, border, RoundedCornerShape(3.dp))
                                    .clickable {
                                        if (profile.workoutLocation != loc) {
                                            viewModel.updateProfile(profile.copy(workoutLocation = loc))
                                            Toast.makeText(context, "Workout environment changed to ${if (loc == WorkoutLocation.GYM) "Gym" else "Home/Travel"}.", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = if (loc == WorkoutLocation.HOME) "HOME" else "GYM",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = textCol,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Replay Tutorials button
        OutlinedButton(
            onClick = {
                TutorialManager.resetAllTutorials(context)
                Toast.makeText(context, "Tutorials have been reset. Reload pages to view guides.", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            border = BorderStroke(1.dp, NeonGold.copy(alpha = 0.4f)),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = NeonGold)
        ) {
            Icon(
                painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_tips),
                contentDescription = null,
                tint = NeonGold,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("Replay Onboarding Tutorials", color = NeonGold, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(12.dp))

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

        Spacer(Modifier.height(12.dp))

        // Clear Progress & Reset button
        OutlinedButton(
            onClick = { showDeleteDialog = true },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .tutorialAnchor("delete_registry", tutorialAnchors),
            border = BorderStroke(1.dp, Color(0xFFE53935).copy(alpha = 0.4f)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = null, tint = Color(0xFFE53935), modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("Clear Progress & Reset", color = Color(0xFFE53935))
        }

        Spacer(Modifier.height(80.dp))
        }

        // Onboarding Tutorial Overlay
        if (showTutorial && tutorialAnchors.isNotEmpty()) {
            val steps = listOf(
                TutorialStep("profile_hud", "Character Overview", "Displays your level, XP progression, and current rank. Competing quests increases this progress bar."),
                TutorialStep("physique_grid", "Physique Attributes", "Your physical stats, including height, weight, target, and BMI. Essential metrics calculated for your quests."),
                TutorialStep("delete_registry", "Registry Wipe", "Delete your profile registry from the database to start completely fresh with new goals, etc.")
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
                            TutorialManager.setTutorialCompleted(context, "profile", true)
                        }
                    },
                    onSkip = {
                        showTutorial = false
                        TutorialManager.setTutorialCompleted(context, "profile", true)
                    },
                    currentStepIndex = tutorialStep,
                    totalSteps = steps.size
                )
            }
        }
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
