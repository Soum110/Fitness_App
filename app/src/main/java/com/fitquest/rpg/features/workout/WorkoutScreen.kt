package com.fitquest.rpg.features.workout

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
import com.fitquest.rpg.ui.components.RpgCard
import com.fitquest.rpg.ui.components.TaskItem
import com.fitquest.rpg.ui.theme.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

import com.google.firebase.auth.FirebaseAuth

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
    private val auth: FirebaseAuth
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
    onCompleteTask: (Long) -> Unit,
    viewModel: WorkoutViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

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
            modifier = Modifier.padding(horizontal = 16.dp),
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
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (state.isLoading) {
                        CircularProgressIndicator(color = StrengthRed)
                    } else {
                        Text("✅ All done for today!", style = MaterialTheme.typography.headlineSmall, color = StrengthRed)
                        Text("Rest and recover. Come back tomorrow.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.workoutTasks, key = { it.id }) { task ->
                    TaskItem(task = task, onComplete = { onCompleteTask(task.id) })
                }

                item {
                    // Rest timer info card
                    RpgCard(glowColor = Color(0xFF42A5F5)) {
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
