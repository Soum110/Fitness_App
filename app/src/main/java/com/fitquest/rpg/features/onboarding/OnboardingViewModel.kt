package com.fitquest.rpg.features.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.repository.UserRepository
import com.fitquest.rpg.core.domain.model.*
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OnboardingState(
    val step: Int = 0,
    val name: String = "",
    val age: Int = 25,
    val gender: Gender = Gender.MALE,
    val heightCm: Float = 170f,
    val weightKg: Float = 70f,
    val fitnessLevel: FitnessLevel = FitnessLevel.BEGINNER,
    val primaryGoal: FitnessGoal = FitnessGoal.GENERAL_FITNESS,
    val dietaryStyle: DietaryStyle = DietaryStyle.OMNIVORE,
    val workoutDaysPerWeek: Int = 4,
    val wakeTimeHour: Int = 7,
    val sleepTimeHour: Int = 23,
    val isSaving: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    val totalSteps = 5

    fun nextStep() { _state.value = _state.value.copy(step = (_state.value.step + 1).coerceAtMost(totalSteps - 1)) }
    fun prevStep() { _state.value = _state.value.copy(step = (_state.value.step - 1).coerceAtLeast(0)) }

    fun updateName(v: String) { _state.value = _state.value.copy(name = v) }
    fun updateAge(v: Int) { _state.value = _state.value.copy(age = v) }
    fun updateGender(v: Gender) { _state.value = _state.value.copy(gender = v) }
    fun updateHeight(v: Float) { _state.value = _state.value.copy(heightCm = v) }
    fun updateWeight(v: Float) { _state.value = _state.value.copy(weightKg = v) }
    fun updateFitnessLevel(v: FitnessLevel) { _state.value = _state.value.copy(fitnessLevel = v) }
    fun updateGoal(v: FitnessGoal) { _state.value = _state.value.copy(primaryGoal = v) }
    fun updateDiet(v: DietaryStyle) { _state.value = _state.value.copy(dietaryStyle = v) }
    fun updateWorkoutDays(v: Int) { _state.value = _state.value.copy(workoutDaysPerWeek = v) }
    fun updateWakeTime(v: Int) { _state.value = _state.value.copy(wakeTimeHour = v) }
    fun updateSleepTime(v: Int) { _state.value = _state.value.copy(sleepTimeHour = v) }

    fun finishOnboarding(onComplete: () -> Unit) {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            _state.value = _state.value.copy(error = "Not logged in. Please restart the app.")
            return
        }
        viewModelScope.launch {
            _state.value = _state.value.copy(isSaving = true, error = null)
            try {
                val s = _state.value
                userRepo.saveProfile(
                    uid = uid,
                    profile = UserProfile(
                        name = s.name.ifBlank { "Hunter" },
                        age = s.age,
                        gender = s.gender,
                        heightCm = s.heightCm,
                        weightKg = s.weightKg,
                        fitnessLevel = s.fitnessLevel,
                        primaryGoal = s.primaryGoal,
                        dietaryStyle = s.dietaryStyle,
                        workoutDaysPerWeek = s.workoutDaysPerWeek,
                        wakeTimeHour = s.wakeTimeHour,
                        sleepTimeHour = s.sleepTimeHour,
                        onboardingComplete = true
                    )
                )
                _state.value = _state.value.copy(isSaving = false)
                onComplete()
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isSaving = false,
                    error = "Failed to save profile. Please try again."
                )
            }
        }
    }
}
