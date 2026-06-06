package com.fitquest.rpg.features.diet

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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

    // Remember checked consumed meals locally for daily tracking
    var consumedMeals by rememberSaveable { mutableStateOf(emptyList<Int>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        // Header Block
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 20.dp, end = 20.dp, bottom = 16.dp)
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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Protocol badge
                    Box(
                        modifier = Modifier
                            .background(phaseColor.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                            .border(1.dp, phaseColor.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "${phase.displayName.uppercase()} PROTOCOL",
                            style = MaterialTheme.typography.labelSmall,
                            color = phaseColor,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.5.sp
                        )
                    }
                    // Dietary Style badge
                    state.profile?.dietaryStyle?.let { style ->
                        Box(
                            modifier = Modifier
                                .background(CardNavy, RoundedCornerShape(6.dp))
                                .border(1.dp, BorderNavy, RoundedCornerShape(6.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = style.displayName.uppercase(),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.7f),
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                }
            }
        }

        // Caloric Target & Macros Grid
        if (state.macroTargets.caloriesKcal > 0) {
            MacroTargetsCard(
                macros = state.macroTargets,
                phaseColor = phaseColor,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(Modifier.height(24.dp))

        // Daily Consumable Rations Section
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
                    "DAILY CONSUMABLES",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp
                )
            }
            Text(
                text = "Satisfy all dietary rations to complete your daily quest objective.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(16.dp))

            state.mealPlan.forEachIndexed { index, meal ->
                val isConsumed = consumedMeals.contains(index)
                MealCard(
                    meal = meal,
                    index = index,
                    phaseColor = phaseColor,
                    isConsumed = isConsumed,
                    onToggleConsume = {
                        consumedMeals = if (isConsumed) {
                            consumedMeals - index
                        } else {
                            consumedMeals + index
                        }
                    }
                )
                Spacer(Modifier.height(12.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        // Diet Tips Section
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
                    "TACTICAL BUFFS",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp
                )
            }
            Text(
                text = "Passive guidelines related to your active transformation phase.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                state.tips.forEach { tip ->
                    TipCard(tip = tip)
                }
            }
        }

        Spacer(Modifier.height(96.dp))
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
            // Header: energy readout
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "DAILY ENERGY THRESHOLD",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF888888),
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "TARGET METRIC STATUS",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF444444),
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .background(phaseColor.copy(alpha = 0.08f), RoundedCornerShape(6.dp))
                        .border(1.dp, phaseColor.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "${macros.caloriesKcal} KCAL",
                        style = MaterialTheme.typography.titleLarge,
                        color = phaseColor,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Three Macro Columns
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val pKcal = macros.proteinG * 4
                val cKcal = macros.carbsG * 4
                val fKcal = macros.fatG * 9

                MacroColChip(
                    label = "PROTEIN",
                    value = "${macros.proteinG}G",
                    kcal = "${pKcal} KCAL",
                    color = StrengthRed,
                    modifier = Modifier.weight(1f)
                )
                MacroColChip(
                    label = "CARBS",
                    value = "${macros.carbsG}G",
                    kcal = "${cKcal} KCAL",
                    color = EnergyYellow,
                    modifier = Modifier.weight(1f)
                )
                MacroColChip(
                    label = "FAT",
                    value = "${macros.fatG}G",
                    kcal = "${fKcal} KCAL",
                    color = IntelPurple,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(16.dp))

            // Macro ratio progress bar
            val total = (macros.proteinG * 4 + macros.carbsG * 4 + macros.fatG * 9).toFloat()
            if (total > 0) {
                val proteinFrac = (macros.proteinG * 4) / total
                val carbsFrac = (macros.carbsG * 4) / total

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color(0xFF141414))
                        .border(0.5.dp, Color(0xFF2E2E2E), RoundedCornerShape(5.dp))
                ) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(proteinFrac)
                                .background(StrengthRed)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(carbsFrac / (1f - proteinFrac))
                                .background(EnergyYellow)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .background(IntelPurple)
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MacroLegend("PROTEIN", "${(proteinFrac * 100).toInt()}%", StrengthRed)
                    MacroLegend("CARBS", "${(carbsFrac * 100).toInt()}%", EnergyYellow)
                    MacroLegend("FAT", "${((1f - proteinFrac - carbsFrac) * 100).toInt()}%", IntelPurple)
                }
            }
        }
    }
}

@Composable
private fun MacroColChip(
    label: String,
    value: String,
    kcal: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.Black, RoundedCornerShape(6.dp))
            .border(0.5.dp, BorderNavy, RoundedCornerShape(6.dp))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF666666),
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = color,
                fontWeight = FontWeight.Black
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = kcal,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF444444),
                fontSize = 9.sp
            )
        }
    }
}

@Composable
private fun MacroLegend(label: String, percent: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(color)
        )
        Text(
            text = "$label $percent",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun MealCard(
    meal: String,
    index: Int,
    phaseColor: Color,
    isConsumed: Boolean,
    onToggleConsume: () -> Unit
) {
    val parsed = remember(meal) { parseMeal(meal) }
    val mealColors = listOf(
        Color(0xFFFFD54F), Color(0xFF81C784), Color(0xFF64B5F6),
        Color(0xFFBA68C8), Color(0xFFFF8A65)
    )
    val accentColor = mealColors.getOrElse(index) { phaseColor }

    val borderStrokeColor by animateColorAsState(
        targetValue = if (isConsumed) SuccessGreen.copy(alpha = 0.4f) else BorderNavy,
        animationSpec = tween(400),
        label = "mealCardBorder"
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (isConsumed) SuccessGreen.copy(alpha = 0.15f) else CardNavy,
        animationSpec = tween(400),
        label = "mealCardBg"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleConsume() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(1.dp, borderStrokeColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left timing accent bar
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(if (isConsumed) SuccessGreen else accentColor)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val iconResId = remember(parsed.emoji, parsed.type) {
                            when {
                                parsed.emoji.contains("🌅") || parsed.type.contains("breakfast", ignoreCase = true) -> com.fitquest.rpg.R.drawable.ic_sunrise
                                parsed.emoji.contains("☀️") || parsed.type.contains("lunch", ignoreCase = true) || parsed.type.contains("midday", ignoreCase = true) -> com.fitquest.rpg.R.drawable.ic_sun
                                parsed.emoji.contains("🌇") || parsed.type.contains("dinner", ignoreCase = true) || parsed.type.contains("evening", ignoreCase = true) -> com.fitquest.rpg.R.drawable.ic_sunset
                                parsed.emoji.contains("🌙") || parsed.emoji.contains("🌌") || parsed.type.contains("pre-bed", ignoreCase = true) || parsed.type.contains("night", ignoreCase = true) || parsed.type.contains("bed", ignoreCase = true) -> com.fitquest.rpg.R.drawable.ic_moon
                                else -> com.fitquest.rpg.R.drawable.ic_diet
                            }
                        }
                        Icon(
                            painter = painterResource(id = iconResId),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = parsed.type.uppercase(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Black,
                            color = if (isConsumed) Color.Gray else Color.White,
                            textDecoration = if (isConsumed) TextDecoration.LineThrough else TextDecoration.None,
                            letterSpacing = 0.5.sp
                        )
                    }

                    // Circle Check Indicator
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(if (isConsumed) SuccessGreen else Color.Black, CircleShape)
                            .border(1.dp, if (isConsumed) SuccessGreen else BorderNavy, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isConsumed) {
                            Text("✓", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                HorizontalDivider(color = BorderNavy.copy(alpha = 0.5f))

                // Ingredients List
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    parsed.items.forEach { ingredient ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(5.dp)
                                    .clip(CircleShape)
                                    .background(if (isConsumed) Color.Gray else accentColor)
                            )
                            Text(
                                text = ingredient,
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (isConsumed) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f) else MaterialTheme.colorScheme.onSurface,
                                textDecoration = if (isConsumed) TextDecoration.LineThrough else TextDecoration.None
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TipCard(tip: String) {
    val parsed = remember(tip) { parseTip(tip) }
    val iconResId = remember(parsed.emoji, parsed.text) {
        when {
            parsed.emoji.contains("💡") || parsed.text.contains("tip", ignoreCase = true) || parsed.text.contains("focus", ignoreCase = true) -> com.fitquest.rpg.R.drawable.ic_tips
            parsed.emoji.contains("💧") || parsed.text.contains("water", ignoreCase = true) || parsed.text.contains("hydrate", ignoreCase = true) || parsed.text.contains("hydration", ignoreCase = true) -> com.fitquest.rpg.R.drawable.ic_water
            parsed.emoji.contains("⏱️") || parsed.emoji.contains("⏳") || parsed.text.contains("timer", ignoreCase = true) || parsed.text.contains("time", ignoreCase = true) || parsed.text.contains("rest", ignoreCase = true) -> com.fitquest.rpg.R.drawable.ic_timer
            parsed.emoji.contains("💪") || parsed.text.contains("strength", ignoreCase = true) || parsed.text.contains("muscle", ignoreCase = true) -> com.fitquest.rpg.R.drawable.ic_strength
            parsed.emoji.contains("🧠") || parsed.text.contains("intel", ignoreCase = true) || parsed.text.contains("mind", ignoreCase = true) || parsed.text.contains("brain", ignoreCase = true) -> com.fitquest.rpg.R.drawable.ic_intelligence
            parsed.emoji.contains("🤸") || parsed.text.contains("flexibility", ignoreCase = true) || parsed.text.contains("stretch", ignoreCase = true) -> com.fitquest.rpg.R.drawable.ic_flexibility
            parsed.emoji.contains("🏃") || parsed.text.contains("stamina", ignoreCase = true) || parsed.text.contains("cardio", ignoreCase = true) || parsed.text.contains("run", ignoreCase = true) -> com.fitquest.rpg.R.drawable.ic_stamina
            parsed.emoji.contains("⚡") || parsed.text.contains("energy", ignoreCase = true) -> com.fitquest.rpg.R.drawable.ic_energy
            else -> com.fitquest.rpg.R.drawable.ic_tips
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardNavy),
        border = BorderStroke(1.dp, BorderNavy),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.Black, RoundedCornerShape(18.dp))
                    .border(0.5.dp, BorderNavy, RoundedCornerShape(18.dp))
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = parsed.text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 18.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

private data class ParsedMeal(
    val emoji: String,
    val type: String,
    val items: List<String>
)

private fun parseMeal(meal: String): ParsedMeal {
    val parts = meal.split(" — ")
    val firstPart = parts.getOrNull(0)?.trim() ?: "🥗 Meal"
    val details = parts.getOrNull(1)?.trim() ?: ""

    val firstPartWords = firstPart.split(" ")
    val emoji = firstPartWords.getOrNull(0) ?: "🥗"
    val type = firstPartWords.drop(1).joinToString(" ")

    val items = details.split("+").map { it.trim() }.filter { it.isNotEmpty() }
    return ParsedMeal(emoji, type, items)
}

private data class ParsedTip(
    val emoji: String,
    val text: String
)

private fun parseTip(tip: String): ParsedTip {
    val words = tip.split(" ")
    val emoji = words.getOrNull(0) ?: "💡"
    val text = words.drop(1).joinToString(" ")
    return ParsedTip(emoji, text)
}
