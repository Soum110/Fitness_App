package com.fitquest.rpg.features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.repository.*
import com.fitquest.rpg.core.domain.model.*
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
    val weekPhaseDescription: String = ""
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val taskRepo: TaskRepository,
    private val cardRepo: RewardCardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                userRepo.observeProfile(),
                userRepo.observeAttributes(),
                taskRepo.observeTodaysTasks(),
                userRepo.observeEconomy()
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
            }.collect { state ->
                _uiState.value = state
                // Auto-generate tasks if profile exists and tasks are empty
                state.profile?.let { profile ->
                    if (state.todaysTasks.isEmpty() && !state.isLoading) {
                        taskRepo.generateDailyTasks(profile)
                        cardRepo.initializePredefinedCards()
                    }
                }
            }
        }
    }

    fun completeTask(taskId: Long) {
        viewModelScope.launch {
            val completedTask = taskRepo.completeTask(taskId) ?: return@launch
            // Award XP to the relevant attribute
            userRepo.addXpToAttribute(completedTask.targetAttribute, completedTask.xpReward)
            // Award action points
            userRepo.addActionPoints(completedTask.apReward)
            // Check if all tasks done — update streak + streak bonus
            checkAndAwardStreakBonus()
        }
    }

    private suspend fun checkAndAwardStreakBonus() {
        val tasks = _uiState.value.todaysTasks
        val allDone = tasks.isNotEmpty() && tasks.all { it.isCompleted }
        if (allDone) {
            userRepo.updateStreak()
            val streak = _uiState.value.economy.currentStreak
            if (streak > 0 && streak % 7 == 0) {
                // 7-day streak bonus: +100 AP
                userRepo.addActionPoints(100)
            }
        }
    }

    fun clearRankUpEvent() {
        _uiState.update { it.copy(rankUpEvent = null) }
    }
}
