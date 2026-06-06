package com.fitquest.rpg.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.remote.SupabaseAuth
import com.fitquest.rpg.core.data.remote.SupabaseUser
import com.fitquest.rpg.core.data.local.FitQuestDatabase
import com.fitquest.rpg.core.data.repository.UserRepository
import com.fitquest.rpg.core.data.repository.TaskRepository
import com.fitquest.rpg.core.data.repository.RewardCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val currentUser: SupabaseUser? = null,
    val isLoginMode: Boolean = true   // toggle between Login and Register
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val auth: SupabaseAuth,
    private val userRepo: UserRepository,
    private val taskRepo: TaskRepository,
    private val rewardCardRepo: RewardCardRepository,
    private val db: FitQuestDatabase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState(currentUser = auth.currentUser))
    val uiState: StateFlow<AuthUiState> = _uiState

    val currentUser: SupabaseUser? get() = auth.currentUser

    fun toggleMode() {
        _uiState.value = _uiState.value.copy(
            isLoginMode = !_uiState.value.isLoginMode,
            errorMessage = null
        )
    }

    fun signIn(email: String, password: String, onSuccess: () -> Unit) {
        if (!validate(email, password)) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                auth.signInWithEmailAndPassword(email.trim(), password)
                _uiState.value = _uiState.value.copy(isLoading = false, currentUser = auth.currentUser)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = friendlyError(e.message)
                )
            }
        }
    }

    fun register(email: String, password: String, onSuccess: () -> Unit) {
        if (!validate(email, password)) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                auth.createUserWithEmailAndPassword(email.trim(), password)
                _uiState.value = _uiState.value.copy(isLoading = false, currentUser = auth.currentUser)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = friendlyError(e.message)
                )
            }
        }
    }

    fun signOut(onComplete: () -> Unit) {
        auth.signOut()
        userRepo.stopSync()
        taskRepo.stopSync()
        rewardCardRepo.stopSync()
        _uiState.value = AuthUiState(currentUser = null)
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    db.clearAllTables()
                }
            } catch (e: Exception) {
                // ignore
            }
            onComplete()
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    private fun validate(email: String, password: String): Boolean {
        return when {
            email.isBlank() -> {
                _uiState.value = _uiState.value.copy(errorMessage = "Please enter your email.")
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() -> {
                _uiState.value = _uiState.value.copy(errorMessage = "Please enter a valid email address.")
                false
            }
            password.length < 6 -> {
                _uiState.value = _uiState.value.copy(errorMessage = "Password must be at least 6 characters.")
                false
            }
            else -> true
        }
    }

    private fun friendlyError(raw: String?): String {
        return when {
            raw == null -> "An unknown error occurred."
            "no user record" in raw.lowercase() -> "No account found with this email."
            "password is invalid" in raw.lowercase() -> "Incorrect password. Please try again."
            "email address is already in use" in raw.lowercase() -> "An account with this email already exists. Please log in."
            "network" in raw.lowercase() -> "Network error. Please check your connection."
            "badly formatted" in raw.lowercase() -> "Please enter a valid email address."
            else -> raw
        }
    }
}
