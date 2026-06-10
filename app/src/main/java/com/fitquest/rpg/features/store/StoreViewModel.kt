package com.fitquest.rpg.features.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.repository.*
import com.fitquest.rpg.core.domain.model.*
import com.fitquest.rpg.core.data.remote.SupabaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StoreUiState(
    val allCards: List<RewardCard> = emptyList(),
    val availableAP: Int = 0,
    val redeemSuccess: String? = null,
    val errorMessage: String? = null,
    val showAddDialog: Boolean = false,
    val isLoading: Boolean = true
)

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val cardRepo: RewardCardRepository,
    private val userRepo: UserRepository,
    private val auth: SupabaseAuth
) : ViewModel() {

    private val _uiState = MutableStateFlow(StoreUiState())
    val uiState: StateFlow<StoreUiState> = _uiState.asStateFlow()

    init {
        val currentUid = auth.currentUser?.uid
        if (currentUid != null) {
            viewModelScope.launch {
                try {
                    combine(
                        cardRepo.observeAvailableCards(currentUid),
                        userRepo.observeEconomy(currentUid)
                    ) { cards, economy ->
                        StoreUiState(
                            allCards = cards,
                            availableAP = economy?.availableActionPoints ?: 0,
                            isLoading = false
                        )
                    }.catch { /* non-fatal */ }.collect { _uiState.value = it }
                } catch (e: Exception) {
                    _uiState.update { it.copy(errorMessage = "Failed to load store.", isLoading = false) }
                }
            }
        }
    }

    fun redeemCard(card: RewardCard) {
        val currentUid = auth.currentUser?.uid ?: return
        val isLocked = card.lastRedeemedAtMs?.let { lastTime ->
            System.currentTimeMillis() - lastTime < 2 * 24 * 60 * 60 * 1000L
        } ?: false
        if (isLocked) {
            _uiState.update { it.copy(errorMessage = "This reward is currently locked.") }
            return
        }
        viewModelScope.launch {
            try {
                val success = userRepo.spendActionPoints(currentUid, card.apCost)
                if (success) {
                    cardRepo.redeemCard(currentUid, card)
                    _uiState.update { it.copy(redeemSuccess = "🎉 \"${card.title}\" unlocked! Enjoy your reward.") }
                } else {
                    _uiState.update { it.copy(errorMessage = "Not enough AP. Need ${card.apCost} AP.") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = "Something went wrong. Please try again.") }
            }
        }
    }

    fun addCustomCard(title: String, description: String, emoji: String, apCost: Int) {
        val currentUid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            try {
                cardRepo.addCustomCard(currentUid, RewardCard(
                    title = title, description = description,
                    emoji = emoji, apCost = apCost, isPredefined = false
                ))
                _uiState.update { it.copy(showAddDialog = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = "Failed to add card.") }
            }
        }
    }

    fun deleteCard(card: RewardCard) {
        val currentUid = auth.currentUser?.uid ?: return
        viewModelScope.launch { cardRepo.deleteCard(currentUid, card) }
    }

    fun incrementQuestProgress(card: RewardCard) {
        val currentUid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            try {
                cardRepo.incrementQuestProgress(currentUid, card, userRepo)
                _uiState.update { it.copy(redeemSuccess = "Quest progress updated!") }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = "Failed to update progress.") }
            }
        }
    }

    fun claimCheatDayBonus(card: RewardCard) {
        val currentUid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            try {
                cardRepo.claimCheatDayBonus(currentUid, card, userRepo)
                _uiState.update { it.copy(redeemSuccess = "🎉 Cheat day bonus claimed!") }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = "Failed to claim bonus.") }
            }
        }
    }

    fun showAddDialog() { _uiState.update { it.copy(showAddDialog = true) } }
    fun hideAddDialog() { _uiState.update { it.copy(showAddDialog = false) } }
    fun clearMessages() { _uiState.update { it.copy(redeemSuccess = null, errorMessage = null) } }
}
