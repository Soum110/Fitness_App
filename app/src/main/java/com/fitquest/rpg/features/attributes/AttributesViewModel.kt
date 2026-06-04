package com.fitquest.rpg.features.attributes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.repository.UserRepository
import com.fitquest.rpg.core.domain.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class AttributesUiState(
    val attributes: List<Attribute> = emptyList(),
    val overallRank: Rank? = null
)

@HiltViewModel
class AttributesViewModel @Inject constructor(
    private val userRepo: UserRepository
) : ViewModel() {
    val uiState: StateFlow<AttributesUiState> = userRepo.observeAttributes().map { attrs ->
        val avgLevel = if (attrs.isEmpty()) 1 else attrs.map { it.level }.average().toInt()
        AttributesUiState(
            attributes = attrs.sortedBy { it.type.ordinal },
            overallRank = Rank.fromLevel(avgLevel)
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AttributesUiState())
}
