package com.fitquest.rpg.features.store

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.filled.Lock
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
                containerColor = Color.White,
                contentColor = Color.Black,
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add custom reward")
            }
        },
        containerColor = Color.Black
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("REWARD STORE", style = MaterialTheme.typography.headlineMedium, letterSpacing = 2.sp)
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
            val activeQuests = state.allCards.filter { it.isRedeemed && it.hasTask && !it.taskCompleted }
            val redeemed = state.allCards
                .filter { it.isRedeemed && (!it.hasTask || it.taskCompleted) }
                .distinctBy { it.title }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f).fillMaxWidth()
            ) {
                if (activeQuests.isNotEmpty()) {
                    item(span = { GridItemSpan(2) }) {
                        Text(
                            "Active Quests",
                            style = MaterialTheme.typography.titleMedium,
                            color = NeonPurple,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                    items(activeQuests, key = { "q-${it.id}" }) { card ->
                        RewardCardItem(
                            card = card,
                            canAfford = false,
                            onRedeem = {},
                            onDelete = null,
                            isActiveQuest = true,
                            onIncrementProgress = { viewModel.incrementQuestProgress(card) },
                            onClaimBonus = { viewModel.claimCheatDayBonus(card) }
                        )
                    }
                }

                if (available.isNotEmpty()) {
                    item(span = { GridItemSpan(2) }) {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Available Rewards",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                    items(available, key = { it.id }) { card ->
                        val isLocked = card.lastRedeemedAtMs?.let { lastTime ->
                            System.currentTimeMillis() - lastTime < 2 * 24 * 60 * 60 * 1000L
                        } ?: false
                        RewardCardItem(
                            card = card,
                            canAfford = state.availableAP >= card.apCost && !isLocked,
                            isLocked = isLocked,
                            onRedeem = { viewModel.redeemCard(card) },
                            onDelete = if (!card.isPredefined) {{ viewModel.deleteCard(card) }} else null
                        )
                    }
                }

                if (redeemed.isNotEmpty()) {
                    item(span = { GridItemSpan(2) }) {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Redeemed / Complete",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                    items(redeemed, key = { "r-${it.id}" }) { card ->
                        val redemptionCount = state.allCards
                            .firstOrNull { !it.isRedeemed && it.title == card.title }
                            ?.timesRedeemed ?: card.timesRedeemed
                        RewardCardItem(
                            card = card,
                            canAfford = false,
                            redemptionCount = redemptionCount,
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
    onDelete: (() -> Unit)?,
    isActiveQuest: Boolean = false,
    onIncrementProgress: (() -> Unit)? = null,
    onClaimBonus: (() -> Unit)? = null,
    isLocked: Boolean = false,
    redemptionCount: Int = 0
) {


    val borderColor = when {
        card.isRedeemed && card.taskCompleted -> SuccessGreen.copy(alpha = 0.5f)
        card.isRedeemed && !card.taskCompleted -> Color.White.copy(alpha = 0.7f)
        canAfford -> GoldAP.copy(alpha = 0.6f)
        else -> BorderNavy
    }

    val borderStroke = BorderStroke(1.dp, borderColor)
    val cardBg = SolidColor(CardNavy)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (card.isRedeemed && !isActiveQuest) 0.5f else 1f),
        border = borderStroke,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .background(cardBg)
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_gift),
                    contentDescription = null,
                    tint = borderColor,
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    card.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    maxLines = 2,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
                if (card.description.isNotEmpty()) {
                    Text(
                        card.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        maxLines = 4,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }

                if (isActiveQuest) {
                    Spacer(Modifier.height(4.dp))
                    HorizontalDivider(color = BorderNavy)
                    Spacer(Modifier.height(4.dp))

                    Text(
                        "QUEST ACTIVE",
                        color = NeonPurple,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelMedium,
                        letterSpacing = 1.sp
                    )

                    if (card.taskType == "COUNTER") {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Progress: ${card.taskProgress} / ${card.taskTarget}",
                                fontWeight = FontWeight.Medium,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(Modifier.width(8.dp))
                            IconButton(
                                onClick = { onIncrementProgress?.invoke() },
                                modifier = Modifier
                                    .size(28.dp)
                                    .background(Color.White, RoundedCornerShape(4.dp))
                            ) {
                                Text("+", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            }
                        }
                        if (card.taskProgress > card.taskTarget) {
                            Text(
                                "Overachieved: +${card.taskProgress - card.taskTarget}!",
                                color = GoldAP,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelSmall
                            )
                            Text(
                                "Bonus: +${(card.taskProgress - card.taskTarget) * card.overachieveXpPerCount} XP, +${(card.taskProgress - card.taskTarget) * card.overachieveApPerCount} AP",
                                color = GoldAP,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    } else if (card.taskType == "CHEAT_DAY_ROUTINE") {
                        Button(
                            onClick = { onClaimBonus?.invoke() },
                            colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Claim Routine Followed", color = Color.White, style = MaterialTheme.typography.labelMedium)
                        }
                        Text(
                            "Rewards: +${card.bonusXp} XP, +${card.bonusAp} AP",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                } else if (card.isRedeemed) {
                    Text("✅ REDEEMED", color = SuccessGreen, style = MaterialTheme.typography.labelMedium, letterSpacing = 1.sp)
                    if (card.hasTask && card.taskCompleted) {
                        Text(
                            "Quest Completed!",
                            color = GoldAP,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                } else {
                    Button(
                        onClick = onRedeem,
                        enabled = canAfford,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (canAfford) Color.White else Color(0xFF141414),
                            contentColor = if (canAfford) Color.Black else Color(0xFF666666),
                            disabledContainerColor = Color(0xFF141414),
                            disabledContentColor = Color(0xFF666666)
                        ),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        Text(
                            "⚡ ${card.apCost} AP",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    if (!canAfford) {
                        Text(
                            text = if (isLocked) "Locked" else "Need ${card.apCost} AP",
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

            if (redemptionCount > 0) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .background(GoldAP.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                        .border(1.dp, GoldAP.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "x$redemptionCount",
                        color = GoldAP,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if (isLocked) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Black.copy(alpha = 0.75f))
                        .clickable(enabled = false) {},
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Locked",
                            tint = GoldAP,
                            modifier = Modifier.size(28.dp)
                        )
                        val timeRemaining = card.lastRedeemedAtMs?.let { lastTime ->
                            val diff = (2 * 24 * 60 * 60 * 1000L) - (System.currentTimeMillis() - lastTime)
                            if (diff > 0) {
                                val hours = diff / (60 * 60 * 1000L)
                                val minutes = (diff % (60 * 60 * 1000L)) / (60 * 1000L)
                                if (hours > 0) "${hours}h ${minutes}m left" else "${minutes}m left"
                            } else null
                        }
                        Text(
                            text = "LOCKED",
                            color = GoldAP,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        if (timeRemaining != null) {
                            Text(
                                text = timeRemaining,
                                color = Color.White.copy(alpha = 0.7f),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
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
        shape = RoundedCornerShape(8.dp),
        properties = androidx.compose.ui.window.DialogProperties(usePlatformDefaultWidth = true),
        title = { Text("Create Custom Reward", color = Color.White, fontWeight = FontWeight.Bold) },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = emoji,
                    onValueChange = { if (it.length <= 2) emoji = it },
                    label = { Text("Emoji") },
                    singleLine = true,
                    modifier = Modifier.width(80.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = BorderNavy,
                        focusedLabelColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp)
                )
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Reward Title *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = BorderNavy,
                        focusedLabelColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp)
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = BorderNavy,
                        focusedLabelColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp)
                )
                OutlinedTextField(
                    value = apCost,
                    onValueChange = { apCost = it.filter { c -> c.isDigit() } },
                    label = { Text("AP Cost *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = BorderNavy,
                        focusedLabelColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp)
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
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                shape = RoundedCornerShape(6.dp)
            ) { Text("Add Reward", fontWeight = FontWeight.Bold) }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
            ) { Text("Cancel") }
        }
    )
}
