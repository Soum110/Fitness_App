package com.fitquest.rpg.features.diet

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.fitquest.rpg.core.domain.model.TransformationPhase
import com.fitquest.rpg.ui.theme.*

@Composable
fun DietScreen(
    viewModel: DietViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val phase = state.phase

    val phaseColor = when (phase) {
        TransformationPhase.CUT -> Color(0xFFEF5350)
        TransformationPhase.BULK -> Color(0xFF66BB6A)
        TransformationPhase.RECOMP -> Color(0xFF42A5F5)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_diet),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(28.dp)
                    )
                    Text(
                        "NUTRITION HQ",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp
                    )
                }
                Spacer(Modifier.height(8.dp))
                // Phase chip
                Box(
                    modifier = Modifier
                        .background(phaseColor.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                        .border(1.dp, phaseColor.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "${phase.displayName.uppercase()} — ${phase.description}",
                        style = MaterialTheme.typography.bodySmall,
                        color = phaseColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Macro targets card
        if (state.macroTargets.caloriesKcal > 0) {
            MacroTargetsCard(
                macros = state.macroTargets,
                phaseColor = phaseColor,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(Modifier.height(20.dp))

        // Today's meal plan
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_diet),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    "TODAY'S MEAL PLAN",
                    style = MaterialTheme.typography.titleLarge,
                    letterSpacing = 2.sp
                )
            }
            Text(
                state.profile?.dietaryStyle?.displayName ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(12.dp))

            state.mealPlan.forEachIndexed { index, meal ->
                MealCard(meal = meal, index = index, phaseColor = phaseColor)
                Spacer(Modifier.height(8.dp))
            }
        }

        Spacer(Modifier.height(20.dp))

        // Diet tips
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_tips),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    "NUTRITION TIPS",
                    style = MaterialTheme.typography.titleLarge,
                    letterSpacing = 2.sp
                )
            }
            Spacer(Modifier.height(12.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = CardNavy),
                border = BorderStroke(1.dp, BorderNavy),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    state.tips.forEach { tip ->
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = tip,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        if (tip != state.tips.last()) {
                            HorizontalDivider(color = BorderNavy)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(80.dp))
    }
}

@Composable
private fun MacroTargetsCard(
    macros: MacroTargets,
    phaseColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardNavy),
        border = BorderStroke(1.dp, BorderNavy),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "🎯 DAILY TARGETS",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    "${macros.caloriesKcal} kcal",
                    style = MaterialTheme.typography.headlineSmall,
                    color = phaseColor,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MacroChip(label = "Protein", value = "${macros.proteinG}g", color = StrengthRed)
                MacroChip(label = "Carbs", value = "${macros.carbsG}g", color = EnergyYellow)
                MacroChip(label = "Fat", value = "${macros.fatG}g", color = IntelPurple)
            }

            Spacer(Modifier.height(12.dp))

            // Macro ratio bar
            val total = (macros.proteinG * 4 + macros.carbsG * 4 + macros.fatG * 9).toFloat()
            if (total > 0) {
                val proteinFrac = (macros.proteinG * 4) / total
                val carbsFrac = (macros.carbsG * 4) / total
                val fatFrac = (macros.fatG * 9) / total

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                ) {
                    Box(Modifier.fillMaxHeight().fillMaxWidth(proteinFrac).background(StrengthRed))
                    Box(Modifier.fillMaxHeight().fillMaxWidth(carbsFrac / (1f - proteinFrac)).background(EnergyYellow))
                    Box(Modifier.fillMaxHeight().weight(1f).background(IntelPurple))
                }

                Spacer(Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MacroLegend("P", "${(proteinFrac * 100).toInt()}%", StrengthRed)
                    MacroLegend("C", "${(carbsFrac * 100).toInt()}%", EnergyYellow)
                    MacroLegend("F", "${(fatFrac * 100).toInt()}%", IntelPurple)
                }
            }
        }
    }
}

@Composable
private fun MacroChip(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.titleMedium, color = color, fontWeight = FontWeight.Black)
        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun MacroLegend(label: String, percent: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Box(Modifier.size(8.dp).clip(RoundedCornerShape(2.dp)).background(color))
        Text("$label $percent", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun MealCard(meal: String, index: Int, phaseColor: Color) {
    val mealColors = listOf(
        Color(0xFFFFD54F), Color(0xFF81C784), Color(0xFF64B5F6),
        Color(0xFFBA68C8), Color(0xFFFF8A65)
    )
    val accentColor = mealColors.getOrElse(index) { phaseColor }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardNavy, RoundedCornerShape(8.dp))
            .border(1.dp, BorderNavy, RoundedCornerShape(8.dp))
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(accentColor)
        )
        Text(
            text = meal,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )
    }
}
