package com.fitquest.rpg.features.auth

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.res.painterResource
import com.fitquest.rpg.ui.theme.*

@Composable
fun AuthScreen(
    onAuthSuccess: (isNewUser: Boolean) -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Entrance animation
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(tween(700)) + slideInVertically(tween(700)) { it / 3 }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(72.dp))

                // ── Logo / Branding ──────────────────────────────────────────
                Icon(
                    painter = painterResource(id = com.fitquest.rpg.R.drawable.ic_quest),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    "FitQuest",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
                Text(
                    "SHADOW RISING",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    letterSpacing = 4.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Your journey is waiting, Hunter.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF888888),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(40.dp))

                // ── Auth Card ────────────────────────────────────────────────
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(CardNavy)
                        .border(1.dp, BorderNavy, RoundedCornerShape(8.dp))
                        .padding(24.dp)
                ) {
                    Column {
                        // Mode toggle
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color.Black)
                                .border(1.dp, BorderNavy, RoundedCornerShape(6.dp))
                                .padding(2.dp)
                        ) {
                            listOf("Login", "Register").forEachIndexed { idx, label ->
                                val isSelected = (idx == 0) == state.isLoginMode
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(if (isSelected) Color.White else Color.Transparent)
                                        .clickable {
                                            if ((idx == 0) != state.isLoginMode) viewModel.toggleMode()
                                        }
                                        .padding(vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        label,
                                        color = if (isSelected) Color.Black else Color(0xFF888888),
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(24.dp))

                        // Email field
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it; viewModel.clearError() },
                            label = { Text("Email") },
                            leadingIcon = { Icon(Icons.Default.Email, null, tint = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
                            ),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = BorderNavy,
                                focusedLabelColor = Color.White,
                                cursorColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )

                        Spacer(Modifier.height(16.dp))

                        // Password field
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it; viewModel.clearError() },
                            label = { Text("Password") },
                            leadingIcon = { Icon(Icons.Default.Lock, null, tint = Color.White) },
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                        contentDescription = if (passwordVisible) "Hide" else "Show",
                                        tint = Color(0xFF888888)
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                    if (state.isLoginMode) {
                                        viewModel.signIn(email, password) { onAuthSuccess(false) }
                                    } else {
                                        viewModel.register(email, password) { onAuthSuccess(true) }
                                    }
                                }
                            ),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = BorderNavy,
                                focusedLabelColor = Color.White,
                                cursorColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )

                        // Error message
                        AnimatedVisibility(visible = state.errorMessage != null) {
                            Spacer(Modifier.height(12.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFF1F0B0B))
                                    .border(1.dp, Color(0xFF4A1010), RoundedCornerShape(6.dp))
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = Color(0xFFEF5350),
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    state.errorMessage ?: "",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFFEF5350)
                                )
                            }
                        }

                        Spacer(Modifier.height(24.dp))

                        // Primary action button
                        Button(
                            onClick = {
                                focusManager.clearFocus()
                                if (state.isLoginMode) {
                                    viewModel.signIn(email, password) { onAuthSuccess(false) }
                                } else {
                                    viewModel.register(email, password) { onAuthSuccess(true) }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            enabled = !state.isLoading,
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black,
                                disabledContainerColor = Color(0xFF1F1F1F),
                                disabledContentColor = Color(0xFF888888)
                            )
                        ) {
                            if (state.isLoading) {
                                CircularProgressIndicator(
                                    color = Color.Black,
                                    modifier = Modifier.size(20.dp),
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text(
                                    if (state.isLoginMode) "Login & Arise ⚔️" else "Create Account ✨",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(32.dp))

                // Footer note
                Text(
                    "Your progress is saved securely in the cloud.\nSwitch devices anytime — your data follows you.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )

                Spacer(Modifier.height(32.dp))
            }
        }
    }
}
