package com.fitquest.rpg.features.workout

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.repository.TaskRepository
import com.fitquest.rpg.core.data.repository.UserRepository
import com.fitquest.rpg.core.domain.model.*
import com.fitquest.rpg.ui.components.*
import com.fitquest.rpg.ui.theme.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.geometry.Rect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

import com.fitquest.rpg.core.data.remote.SupabaseAuth

// ─── ViewModel ──────────────────────────────────────────────────────────────

data class WorkoutUiState(
    val workoutTasks: List<DailyTask> = emptyList(),
    val weekNumber: Int = 1,
    val phaseDescription: String = "",
    val isLoading: Boolean = true
)

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val taskRepo: TaskRepository,
    private val userRepo: UserRepository,
    private val auth: SupabaseAuth
) : ViewModel() {

    val uiState: StateFlow<WorkoutUiState> = run {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            MutableStateFlow(WorkoutUiState(isLoading = false))
        } else {
            combine(
                taskRepo.observeTodaysTasks(uid),
                userRepo.observeProfile(uid)
            ) { tasks, profile ->
                WorkoutUiState(
                    workoutTasks = tasks.filter {
                        it.taskType == TaskType.WORKOUT || it.taskType == TaskType.CARDIO || it.taskType == TaskType.STRETCH
                    },
                    weekNumber = profile?.trainingWeekNumber ?: 1,
                    phaseDescription = profile?.let {
                        ProgressiveOverloadEngine.weekPhaseDescription(it.trainingWeekNumber)
                    } ?: "",
                    isLoading = false
                )
            }.catch { emit(WorkoutUiState(isLoading = false)) }
             .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WorkoutUiState())
        }
    }
}


// ─── Screen ─────────────────────────────────────────────────────────────────

@Composable
fun WorkoutScreen(
    onToggleTask: (Long) -> Unit,
    viewModel: WorkoutViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
 
    // Onboarding Tutorial States
    val context = LocalContext.current
    var showTutorial by remember { mutableStateOf(false) }
    var tutorialStep by remember { mutableStateOf(0) }
    val tutorialAnchors = remember { mutableStateMapOf<String, Rect>() }
    val lazyListState = rememberLazyListState()
 
    LaunchedEffect(tutorialStep, showTutorial, state.workoutTasks) {
        if (showTutorial) {
            val targetIndex = when (tutorialStep) {
                0 -> 0 // workout_list (top of list)
                1 -> 0 // week_phase (top of list)
                2 -> state.workoutTasks.size // rest_timer (bottom of list)
                else -> 0
            }
            try {
                lazyListState.animateScrollToItem(targetIndex)
            } catch (e: Exception) {}
        }
    }
 
    LaunchedEffect(state.workoutTasks, state.isLoading) {
        if (!state.isLoading) {
            showTutorial = !TutorialManager.isTutorialCompleted(context, "workout")
        }
    }
 
    Box(
        modifier = Modifier
            .fillMaxSize()
            .tutorialAnchor("screen_root", tutorialAnchors)
    ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    com.fitquest.rpg.ui.components.FitQuestLoadingSpinner(size = 64.dp, accentColor = StrengthRed)
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "PREPARING WORKOUT...",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        letterSpacing = 2.sp
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                // Header
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp, start = 20.dp, end = 20.dp, bottom = 16.dp)
                        .tutorialAnchor("workout_list", tutorialAnchors)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_strength),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            "TODAY'S WORKOUT",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 2.sp
                        )
                    }
                    Text(
                        "Week ${state.weekNumber} — ${state.phaseDescription}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = StrengthRed.copy(alpha = 0.8f)
                    )
                }
 
                // Overload cycle legend
                RpgCard(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .tutorialAnchor("week_phase", tutorialAnchors),
                    glowColor = StrengthRed
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CycleWeekChip("W1", "Base", state.weekNumber % 4 == 1, Color(0xFF66BB6A))
                        CycleWeekChip("W2", "+Reps", state.weekNumber % 4 == 2, Color(0xFF42A5F5))
                        CycleWeekChip("W3", "+Sets", state.weekNumber % 4 == 3, StrengthRed)
                        CycleWeekChip("W4", "Deload", state.weekNumber % 4 == 0, Color(0xFFFFD54F))
                    }
                }
 
                Spacer(Modifier.height(16.dp))
 
                if (state.workoutTasks.isEmpty()) {
                    Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("✅ All done for today!", style = MaterialTheme.typography.headlineSmall, color = StrengthRed)
                            Spacer(Modifier.height(8.dp))
                            Text("Rest and recover. Come back tomorrow.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                } else {
                    LazyColumn(
                        state = lazyListState,
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.workoutTasks, key = { it.id }) { task ->
                            TaskItem(task = task, onComplete = { onToggleTask(task.id) })
                        }
 
                        item {
                            // Rest timer info card
                            RpgCard(
                                glowColor = Color(0xFF42A5F5),
                                modifier = Modifier.tutorialAnchor("rest_timer", tutorialAnchors)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_timer),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        "REST BETWEEN SETS",
                                        style = MaterialTheme.typography.labelLarge,
                                        color = Color(0xFF42A5F5),
                                        letterSpacing = 2.sp
                                    )
                                }
                                Spacer(Modifier.height(6.dp))
                                Text(
                                    "Compound movements (squats, rows): 2–3 min\nIsolation movements (curls, raises): 60–90 sec\nCardio intervals: 30–60 sec",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Spacer(Modifier.height(80.dp))
                        }
                    }
                }
            }
        }

        // Onboarding Tutorial Overlay
        if (showTutorial && tutorialAnchors.isNotEmpty()) {
            val steps = listOf(
                TutorialStep("workout_list", "Workout Quests", "These are your customized training exercises for today. Click a card to complete it and gain Strength or Stamina XP!"),
                TutorialStep("week_phase", "Overload Cycle", "FitQuest monitors progressive overload. Follow the active weekly phase: Base, +Reps, +Sets, or Deload."),
                TutorialStep("rest_timer", "Rest Guidelines", "Rest recommendations between sets. Maintain correct pacing to optimize hypertrophy and recovery.")
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
                            TutorialManager.setTutorialCompleted(context, "workout", true)
                        }
                    },
                    onSkip = {
                        showTutorial = false
                        TutorialManager.setTutorialCompleted(context, "workout", true)
                    },
                    currentStepIndex = tutorialStep,
                    totalSteps = steps.size
                )
            }
        }
    }
}

@Composable
private fun CycleWeekChip(week: String, label: String, isActive: Boolean, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .background(
                    if (isActive) color.copy(0.15f) else Color.Transparent,
                    RoundedCornerShape(6.dp)
                )
                .border(1.dp, if (isActive) color else BorderNavy, RoundedCornerShape(6.dp))
        ) {
            Text(week, color = if (isActive) color else MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
        Text(label, style = MaterialTheme.typography.labelSmall, color = if (isActive) color else MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
