package com.fitquest.rpg.features.diet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitquest.rpg.core.data.repository.UserRepository
import com.fitquest.rpg.core.domain.model.*
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class DietUiState(
    val profile: UserProfile? = null,
    val phase: TransformationPhase = TransformationPhase.RECOMP,
    val mealPlan: List<String> = emptyList(),
    val macroTargets: MacroTargets = MacroTargets(),
    val tips: List<String> = emptyList()
)

data class MacroTargets(
    val caloriesKcal: Int = 0,
    val proteinG: Int = 0,
    val carbsG: Int = 0,
    val fatG: Int = 0
)

@HiltViewModel
class DietViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    val uiState: StateFlow<DietUiState> = run {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            MutableStateFlow(DietUiState())
        } else {
            userRepo.observeProfile(uid).map { profile ->
                if (profile == null) return@map DietUiState()
                val phase = profile.transformationPhase
                val macros = computeMacros(profile)
                DietUiState(
                    profile = profile,
                    phase = phase,
                    mealPlan = getMealPlan(profile),
                    macroTargets = macros,
                    tips = getDietTips(profile)
                )
            }.catch { emit(DietUiState()) }
             .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DietUiState())
        }
    }

    private fun computeMacros(profile: UserProfile): MacroTargets {
        // Mifflin-St Jeor BMR estimate
        val bmr = if (profile.gender == Gender.MALE) {
            (10 * profile.weightKg + 6.25 * profile.heightCm - 5 * profile.age + 5).toInt()
        } else {
            (10 * profile.weightKg + 6.25 * profile.heightCm - 5 * profile.age - 161).toInt()
        }
        // Moderate activity multiplier
        val tdee = (bmr * 1.55).toInt()

        val calories = when (profile.transformationPhase) {
            TransformationPhase.CUT -> tdee - 400
            TransformationPhase.BULK -> tdee + 400
            TransformationPhase.RECOMP -> tdee
        }

        val proteinG = (profile.weightKg * when (profile.transformationPhase) {
            TransformationPhase.CUT -> 2.2f
            TransformationPhase.BULK -> 1.8f
            TransformationPhase.RECOMP -> 2.4f
        }).toInt()

        val proteinKcal = proteinG * 4
        val fatG = (profile.weightKg * 0.9f).toInt()
        val fatKcal = fatG * 9
        val carbsG = ((calories - proteinKcal - fatKcal) / 4).coerceAtLeast(50)

        return MacroTargets(calories, proteinG, carbsG, fatG)
    }

    private fun getMealPlan(profile: UserProfile): List<String> {
        return when (profile.dietaryStyle) {
            DietaryStyle.OMNIVORE -> when (profile.transformationPhase) {
                TransformationPhase.CUT -> listOf(
                    "🌅 Breakfast — 3 egg whites + 1 whole egg + 40g oats + black coffee",
                    "🌞 Lunch — 150g grilled chicken breast + large salad + olive oil",
                    "🍎 Snack — 0% Greek yogurt + handful almonds",
                    "🌙 Dinner — 150g white fish + steamed broccoli + 100g sweet potato",
                    "🌛 Pre-bed — Casein protein shake"
                )
                TransformationPhase.BULK -> listOf(
                    "🌅 Breakfast — 4 whole eggs + 2 slices sourdough + 250ml whole milk",
                    "🌞 Lunch — 200g lean beef mince + 200g cooked rice + veg",
                    "💪 Post-Workout — 50g oats + 2 bananas + whey protein",
                    "🌙 Dinner — 200g salmon + 200g pasta + olive oil + veg",
                    "🍌 Snack — 30g peanut butter + 2 rice cakes + banana"
                )
                TransformationPhase.RECOMP -> listOf(
                    "🌅 Breakfast — Overnight oats (60g) + chia seeds + whey + berries",
                    "🌞 Lunch — 180g turkey breast + 150g sweet potato + asparagus",
                    "🍎 Snack — 200g cottage cheese + walnuts (20g)",
                    "🌙 Dinner — 180g cod + green beans + 100g quinoa",
                    "🌛 Pre-bed — Casein protein + small handful mixed nuts"
                )
            }
            DietaryStyle.VEGAN -> listOf(
                "🌅 Breakfast — Tofu scramble (150g) + spinach + nutritional yeast",
                "🌞 Lunch — Lentil soup + 100g quinoa + side salad",
                "🍎 Snack — Edamame (100g) + rice cakes",
                "🌙 Dinner — 150g tempeh stir-fry + broccoli + brown rice",
                "🌛 Pre-bed — Pea protein shake (25g protein)"
            )
            DietaryStyle.VEGETARIAN -> listOf(
                "🌅 Breakfast — 3 whole eggs + 40g oats + berries",
                "🌞 Lunch — Paneer (150g) + chickpea salad + yogurt",
                "🍎 Snack — Cottage cheese (200g) + fruit",
                "🌙 Dinner — Lentil dal + brown rice + vegetables",
                "🌛 Pre-bed — Casein or whey protein shake"
            )
            DietaryStyle.KETO -> listOf(
                "🌅 Breakfast — 4 eggs + bacon (50g) + avocado (½)",
                "🌞 Lunch — 180g salmon + leafy greens + olive oil dressing",
                "🍎 Snack — Handful macadamia nuts + 30g cheese",
                "🌙 Dinner — 200g ribeye + asparagus + butter",
                "🌛 Pre-bed — Cream cheese + cucumber slices"
            )
        }
    }

    private fun getDietTips(profile: UserProfile): List<String> = when (profile.transformationPhase) {
        TransformationPhase.CUT -> listOf(
            "💧 Drink 3–4L of water daily — hunger is often just thirst",
            "🕐 Eat protein with every meal to preserve muscle",
            "🥦 Fill half your plate with vegetables to stay full on fewer calories",
            "🚫 Avoid liquid calories — they don't trigger satiety signals",
            "⏰ Consider a 16:8 eating window (intermittent fasting)"
        )
        TransformationPhase.BULK -> listOf(
            "📈 Prioritise getting enough total calories — this is the main challenge",
            "💪 Hit at least 1.8g protein per kg bodyweight daily",
            "🍚 Eat carbs around your workouts for maximum performance",
            "😴 Sleep 7–9 hours — growth hormone peaks during deep sleep",
            "📊 Weigh yourself weekly — aim for 0.25–0.5kg gain per week"
        )
        TransformationPhase.RECOMP -> listOf(
            "🎯 Protein is your #1 priority at 2.2–2.6g/kg",
            "⚡ Eat most carbs around workout times",
            "📏 Weigh weekly — scale may not move much but body composition improves",
            "💧 Stay hydrated — aim for 35ml per kg bodyweight",
            "🔄 Be patient — recomp is the slowest but most sustainable approach"
        )
    }
}
