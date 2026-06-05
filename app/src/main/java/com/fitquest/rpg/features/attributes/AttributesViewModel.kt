package com.fitquest.rpg.features.attributes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.repository.UserRepository
import com.fitquest.rpg.core.domain.model.*
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class AttributesUiState(
    val attributes: List<Attribute> = emptyList(),
    val overallRank: Rank? = null,
    val globalLevel: Int = 1,
    val globalProgressFraction: Float = 0f
)

@HiltViewModel
class AttributesViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    val uiState: StateFlow<AttributesUiState> = run {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            MutableStateFlow(AttributesUiState())
        } else {
            userRepo.observeAttributes(uid).map { attrs ->
                val avgLevel = if (attrs.isEmpty()) 1 else attrs.map { it.level }.average().toInt()
                val avgProgress = if (attrs.isEmpty()) 0f else attrs.map { it.progressFraction }.average().toFloat()
                AttributesUiState(
                    attributes = attrs.sortedBy { it.type.ordinal },
                    overallRank = Rank.fromLevel(avgLevel),
                    globalLevel = avgLevel,
                    globalProgressFraction = avgProgress
                )
            }.catch { emit(AttributesUiState()) }
             .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AttributesUiState())
        }
    }
}
