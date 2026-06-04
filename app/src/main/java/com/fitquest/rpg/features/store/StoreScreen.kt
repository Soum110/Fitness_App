package com.fitquest.rpg.features.store

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.fitquest.rpg.core.domain.model.RewardCard
import com.fitquest.rpg.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreen(
    onBack: () -> Unit,
    viewModel: StoreViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    // Handle messages
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state.redeemSuccess, state.errorMessage) {
        state.redeemSuccess?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearMessages()
        }
        state.errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearMessages()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = viewModel::showAddDialog,
                containerColor = NeonPurple,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add custom reward")
            }
        },
        containerColor = DeepNavy
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(DeepNavy)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("🏪 REWARD STORE", style = MaterialTheme.typography.headlineMedium, letterSpacing = 2.sp)
                    Text("Spend your earned Action Points", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                // AP Balance
                Column(horizontalAlignment = Alignment.End) {
                    Text("YOUR BALANCE", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, letterSpacing = 2.sp)
                    Text("⚡ ${state.availableAP} AP", style = MaterialTheme.typography.headlineSmall, color = GoldAP, fontWeight = FontWeight.Black)
                }
            }

            HorizontalDivider(color = BorderNavy)

            // Card grid
            val available = state.allCards.filter { !it.isRedeemed }
            val redeemed = state.allCards.filter { it.isRedeemed }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (available.isNotEmpty()) {
                    item(span = { GridItemSpan(2) }) {
                        Text(
                            "✨ Available Rewards",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                    items(available, key = { it.id }) { card ->
                        RewardCardItem(
                            card = card,
                            canAfford = state.availableAP >= card.apCost,
                            onRedeem = { viewModel.redeemCard(card) },
                            onDelete = if (!card.isPredefined) {{ viewModel.deleteCard(card) }} else null
                        )
                    }
                }

                if (redeemed.isNotEmpty()) {
                    item(span = { GridItemSpan(2) }) {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "✅ Redeemed",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                    items(redeemed, key = { "r-${it.id}" }) { card ->
                        RewardCardItem(
                            card = card,
                            canAfford = false,
                            onRedeem = {},
                            onDelete = null
                        )
                    }
                }
            }
        }
    }

    // Add custom card dialog
    if (state.showAddDialog) {
        AddCustomCardDialog(
            onDismiss = viewModel::hideAddDialog,
            onConfirm = viewModel::addCustomCard
        )
    }
}

@Composable
private fun RewardCardItem(
    card: RewardCard,
    canAfford: Boolean,
    onRedeem: () -> Unit,
    onDelete: (() -> Unit)?
) {
    val shimmer = rememberInfiniteTransition(label = "cardShimmer")
    val shimmerAlpha by shimmer.animateFloat(
        initialValue = 0.7f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse),
        label = "shimmerA"
    )

    val borderColor = when {
        card.isRedeemed -> SuccessGreen.copy(alpha = 0.4f)
        canAfford -> GoldAP.copy(alpha = shimmerAlpha * 0.6f)
        else -> BorderNavy
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (card.isRedeemed) 0.6f else 1f),
        colors = CardDefaults.cardColors(containerColor = CardNavy),
        border = BorderStroke(1.dp, borderColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(card.emoji, fontSize = 36.sp)
            Text(
                card.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            if (card.description.isNotEmpty()) {
                Text(
                    card.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    maxLines = 2
                )
            }

            if (card.isRedeemed) {
                Text("✅ REDEEMED", color = SuccessGreen, style = MaterialTheme.typography.labelMedium, letterSpacing = 1.sp)
            } else {
                Button(
                    onClick = onRedeem,
                    enabled = canAfford,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (canAfford) NeonPurple else SurfaceNavy,
                        disabledContainerColor = SurfaceNavy
                    ),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Text(
                        "⚡ ${card.apCost} AP",
                        color = if (canAfford) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                }
                if (!canAfford) {
                    Text(
                        "Need ${card.apCost} AP",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (onDelete != null) {
                TextButton(onClick = onDelete, contentPadding = PaddingValues(0.dp)) {
                    Text("Remove", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}

@Composable
private fun AddCustomCardDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, String, Int) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var emoji by remember { mutableStateOf("🎁") }
    var apCost by remember { mutableStateOf("100") }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = CardNavy,
        title = { Text("✨ Create Custom Reward", color = NeonPurple) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = emoji,
                    onValueChange = { if (it.length <= 2) emoji = it },
                    label = { Text("Emoji") },
                    singleLine = true,
                    modifier = Modifier.width(80.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = NeonPurple)
                )
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Reward Title *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = NeonPurple)
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = NeonPurple)
                )
                OutlinedTextField(
                    value = apCost,
                    onValueChange = { apCost = it.filter { c -> c.isDigit() } },
                    label = { Text("AP Cost *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = NeonPurple)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank() && apCost.isNotBlank()) {
                        onConfirm(title, description, emoji, apCost.toIntOrNull() ?: 100)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = NeonPurple)
            ) { Text("Add Reward") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
