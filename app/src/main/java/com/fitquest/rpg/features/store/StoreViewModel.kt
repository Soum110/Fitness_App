package com.fitquest.rpg.features.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.repository.*
import com.fitquest.rpg.core.domain.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StoreUiState(
    val allCards: List<RewardCard> = emptyList(),
    val availableAP: Int = 0,
    val redeemSuccess: String? = null,
    val errorMessage: String? = null,
    val showAddDialog: Boolean = false
)

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val cardRepo: RewardCardRepository,
    private val userRepo: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StoreUiState())
    val uiState: StateFlow<StoreUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(cardRepo.observeAllCards(), userRepo.observeEconomy()) { cards, economy ->
                StoreUiState(allCards = cards, availableAP = economy?.availableActionPoints ?: 0)
            }.collect { _uiState.value = it }
        }
    }

    fun redeemCard(card: RewardCard) {
        viewModelScope.launch {
            val success = userRepo.spendActionPoints(card.apCost)
            if (success) {
                cardRepo.redeemCard(card)
                _uiState.update { it.copy(redeemSuccess = "🎉 \"${card.title}\" unlocked! Enjoy your reward.") }
            } else {
                _uiState.update { it.copy(errorMessage = "Not enough AP. Need ${card.apCost} AP.") }
            }
        }
    }

    fun addCustomCard(title: String, description: String, emoji: String, apCost: Int) {
        viewModelScope.launch {
            cardRepo.addCustomCard(RewardCard(
                title = title, description = description,
                emoji = emoji, apCost = apCost, isPredefined = false
            ))
            _uiState.update { it.copy(showAddDialog = false) }
        }
    }

    fun deleteCard(card: RewardCard) {
        viewModelScope.launch { cardRepo.deleteCard(card) }
    }

    fun showAddDialog() { _uiState.update { it.copy(showAddDialog = true) } }
    fun hideAddDialog() { _uiState.update { it.copy(showAddDialog = false) } }
    fun clearMessages() { _uiState.update { it.copy(redeemSuccess = null, errorMessage = null) } }
}
