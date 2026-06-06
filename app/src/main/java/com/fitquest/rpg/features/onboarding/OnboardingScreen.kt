package com.fitquest.rpg.features.onboarding

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.res.painterResource
import com.fitquest.rpg.core.domain.model.*
import com.fitquest.rpg.ui.theme.*

@Composable
fun OnboardingScreen(
    onComplete: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Progress indicator
            LinearProgressIndicator(
                progress = { (state.step + 1f) / viewModel.totalSteps },
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                trackColor = Color(0xFF141414)
            )

            Spacer(Modifier.height(24.dp))

            // Step counter
            Text(
                text = "STEP ${state.step + 1} OF ${viewModel.totalSteps}",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 24.dp),
                letterSpacing = 3.sp
            )

            // Content with slide animation
            AnimatedContent(
                targetState = state.step,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInHorizontally { it } + fadeIn() togetherWith
                        slideOutHorizontally { -it } + fadeOut()
                    } else {
                        slideInHorizontally { -it } + fadeIn() togetherWith
                        slideOutHorizontally { it } + fadeOut()
                    }
                },
                label = "onboardingStep",
                modifier = Modifier.weight(1f)
            ) { step ->
                when (step) {
                    0 -> Step0Welcome(state, viewModel)
                    1 -> Step1PhysicalStats(state, viewModel)
                    2 -> Step2Goals(state, viewModel)
                    3 -> Step3Diet(state, viewModel)
                    4 -> Step4Schedule(state, viewModel)
                }
            }

            // Navigation buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (state.step > 0) {
                    OutlinedButton(
                        onClick = viewModel::prevStep,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                        border = BorderStroke(1.dp, BorderNavy),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("← Back")
                    }
                }

                Button(
                    onClick = {
                        if (state.step < viewModel.totalSteps - 1) {
                            viewModel.nextStep()
                        } else {
                            viewModel.finishOnboarding(onComplete)
                        }
                    },
                    modifier = Modifier.weight(if (state.step > 0) 2f else 1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !state.isSaving
                ) {
                    if (state.isSaving) {
                        CircularProgressIndicator(Modifier.size(20.dp), color = Color.Black, strokeWidth = 2.dp)
                    } else {
                        Text(if (state.step == viewModel.totalSteps - 1) "🚀 Begin My Journey" else "Next →", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun Step0Welcome(state: OnboardingState, vm: OnboardingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_quest),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(72.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "ARISE",
            style = MaterialTheme.typography.displayLarge,
            color = NeonGold,
            fontWeight = FontWeight.Black,
            letterSpacing = 8.sp
        )
        Text(
            "FitQuest: Shadow Rising",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            letterSpacing = 2.sp
        )
        Spacer(Modifier.height(32.dp))
        Text(
            "Your real-life RPG begins here.\nLevel up your body, mind, and spirit.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(40.dp))
        Text("What shall we call you, Hunter?", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = state.name,
            onValueChange = vm::updateName,
            placeholder = { Text("Enter your name") },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NeonPurple,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedBorderColor = BorderNavy
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun Step1PhysicalStats(state: OnboardingState, vm: OnboardingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("💪 Physical Stats", style = MaterialTheme.typography.headlineMedium)
        Text("We'll use these to calibrate your starting power level.", color = MaterialTheme.colorScheme.onSurfaceVariant)

        // Age
        LabeledSlider("Age: ${state.age} years", state.age.toFloat(), 15f, 70f) {
            vm.updateAge(it.toInt())
        }

        // Height
        LabeledSlider("Height: ${state.heightCm.toInt()} cm", state.heightCm, 140f, 220f) {
            vm.updateHeight(it)
        }

        // Weight
        LabeledSlider("Weight: ${state.weightKg.toInt()} kg", state.weightKg, 40f, 150f) {
            vm.updateWeight(it)
        }

        // Gender
        Text("Gender", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Gender.values().forEach { g ->
                val selected = state.gender == g
                FilterChip(
                    selected = selected,
                    onClick = { vm.updateGender(g) },
                    label = { Text(g.displayName) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = NeonPurple,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        // Fitness Level
        Text("Fitness Level", style = MaterialTheme.typography.titleMedium)
        FitnessLevel.values().forEach { level ->
            OptionCard(
                selected = state.fitnessLevel == level,
                title = level.displayName,
                onClick = { vm.updateFitnessLevel(level) }
            )
        }
    }
}

@Composable
private fun Step2Goals(state: OnboardingState, vm: OnboardingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("🎯 Your Mission", style = MaterialTheme.typography.headlineMedium)
        Text("Choose your primary objective, Hunter.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(8.dp))
        FitnessGoal.values().forEach { goal ->
            OptionCard(
                selected = state.primaryGoal == goal,
                title = goal.displayName,
                onClick = { vm.updateGoal(goal) }
            )
        }
    }
}

@Composable
private fun Step3Diet(state: OnboardingState, vm: OnboardingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("🥗 Dietary Style", style = MaterialTheme.typography.headlineMedium)
        Text("We'll personalize your nutrition plan accordingly.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(8.dp))
        DietaryStyle.values().forEach { diet ->
            OptionCard(
                selected = state.dietaryStyle == diet,
                title = diet.displayName,
                onClick = { vm.updateDiet(diet) }
            )
        }
    }
}

@Composable
private fun Step4Schedule(state: OnboardingState, vm: OnboardingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("📅 Your Schedule", style = MaterialTheme.typography.headlineMedium)
        Text("We'll design your routine around your life.", color = MaterialTheme.colorScheme.onSurfaceVariant)

        LabeledSlider(
            label = "Workout days per week: ${state.workoutDaysPerWeek}",
            value = state.workoutDaysPerWeek.toFloat(),
            min = 2f, max = 7f
        ) { vm.updateWorkoutDays(it.toInt()) }

        LabeledSlider(
            label = "Wake up at: ${formatHour(state.wakeTimeHour)}",
            value = state.wakeTimeHour.toFloat(),
            min = 4f, max = 11f
        ) { vm.updateWakeTime(it.toInt()) }

        LabeledSlider(
            label = "Bed time: ${formatHour(state.sleepTimeHour)}",
            value = state.sleepTimeHour.toFloat(),
            min = 19f, max = 26f
        ) { vm.updateSleepTime(it.toInt().coerceIn(0, 23)) }

        // Summary card
        Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceNavy),
            border = BorderStroke(1.dp, BorderNavy),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Your Profile Preview", style = MaterialTheme.typography.titleMedium, color = Color.White, fontWeight = FontWeight.Bold)
                HorizontalDivider(color = BorderNavy, modifier = Modifier.padding(vertical = 8.dp))
                ProfileSummaryRow("Fitness Level", state.fitnessLevel.displayName)
                ProfileSummaryRow("Goal", state.primaryGoal.displayName)
                ProfileSummaryRow("Diet", state.dietaryStyle.displayName)
                ProfileSummaryRow("Training Days", "${state.workoutDaysPerWeek}x / week")
            }
        }
    }
}

@Composable
private fun LabeledSlider(label: String, value: Float, min: Float, max: Float, onChanged: (Float) -> Unit) {
    Column {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
        Slider(
            value = value,
            onValueChange = onChanged,
            valueRange = min..max,
            colors = SliderDefaults.colors(thumbColor = Color.White, activeTrackColor = Color.White)
        )
    }
}

@Composable
private fun OptionCard(selected: Boolean, title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) Color(0xFF141414) else CardNavy
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) Color.White else BorderNavy
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
            if (selected) Text("✓", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun ProfileSummaryRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
        Text(value, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.bodyMedium)
    }
}

private fun formatHour(hour: Int): String {
    val h = hour % 24
    val suffix = if (h < 12) "AM" else "PM"
    val display = when {
        h == 0 -> 12
        h > 12 -> h - 12
        else -> h
    }
    return "$display:00 $suffix"
}
