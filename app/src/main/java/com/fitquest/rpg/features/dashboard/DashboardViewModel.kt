package com.fitquest.rpg.features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.repository.*
import com.fitquest.rpg.core.domain.model.*
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardUiState(
    val profile: UserProfile? = null,
    val attributes: List<Attribute> = emptyList(),
    val todaysTasks: List<DailyTask> = emptyList(),
    val economy: Economy = Economy(),
    val isLoading: Boolean = true,
    val rankUpEvent: Rank? = null,
    val weekPhaseDescription: String = "",
    val errorMessage: String? = null
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val taskRepo: TaskRepository,
    private val cardRepo: RewardCardRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        val currentUid = auth.currentUser?.uid
        if (currentUid == null) {
            _uiState.value = DashboardUiState(isLoading = false)
        } else {
            viewModelScope.launch {
                try {
                    combine(
                        userRepo.observeProfile(currentUid),
                        userRepo.observeAttributes(currentUid),
                        taskRepo.observeTodaysTasks(currentUid),
                        userRepo.observeEconomy(currentUid)
                    ) { profile, attributes, tasks, economy ->
                        val sorted = attributes.sortedBy { it.type.ordinal }
                        DashboardUiState(
                            profile = profile,
                            attributes = sorted,
                            todaysTasks = tasks,
                            economy = economy ?: Economy(),
                            isLoading = false,
                            weekPhaseDescription = profile?.let {
                                ProgressiveOverloadEngine.weekPhaseDescription(it.trainingWeekNumber)
                            } ?: ""
                        )
                    }.catch { e ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = "Failed to load data: ${e.message}"
                        )
                    }.collect { state ->
                        _uiState.value = state
                        // Auto-generate tasks if profile is complete and no tasks today
                        state.profile?.let { profile ->
                            if (profile.onboardingComplete && state.todaysTasks.isEmpty() && !state.isLoading) {
                                try {
                                    taskRepo.generateDailyTasks(currentUid, profile)
                                    cardRepo.initializePredefinedCards(currentUid)
                                } catch (e: Exception) {
                                    // Non-fatal: tasks will generate on next launch
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    _uiState.value = DashboardUiState(isLoading = false, errorMessage = e.message)
                }
            }
        }
    }

    fun completeTask(taskId: Long) {
        val currentUid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            try {
                val completedTask = taskRepo.completeTask(currentUid, taskId) ?: return@launch
                userRepo.addXpToAttribute(currentUid, completedTask.targetAttribute, completedTask.xpReward)
                userRepo.addActionPoints(currentUid, completedTask.apReward)
                checkAndAwardStreakBonus(currentUid)
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = "Failed to complete task.") }
            }
        }
    }

    private suspend fun checkAndAwardStreakBonus(uid: String) {
        val tasks = _uiState.value.todaysTasks
        val allDone = tasks.isNotEmpty() && tasks.all { it.isCompleted }
        if (allDone) {
            userRepo.updateStreak(uid)
            val streak = _uiState.value.economy.currentStreak
            if (streak > 0 && streak % 7 == 0) {
                userRepo.addActionPoints(uid, 100)
            }
        }
    }

    fun clearRankUpEvent() { _uiState.update { it.copy(rankUpEvent = null) } }
    fun clearError() { _uiState.update { it.copy(errorMessage = null) } }
}
