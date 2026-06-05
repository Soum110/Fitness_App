package com.fitquest.rpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.fitquest.rpg.features.attributes.AttributesScreen
import com.fitquest.rpg.features.auth.AuthScreen
import com.fitquest.rpg.features.auth.AuthViewModel
import com.fitquest.rpg.features.dashboard.DashboardScreen
import com.fitquest.rpg.features.diet.DietScreen
import com.fitquest.rpg.features.onboarding.OnboardingScreen
import com.fitquest.rpg.features.onboarding.OnboardingViewModel
import com.fitquest.rpg.features.profile.ProfileScreen
import com.fitquest.rpg.features.store.StoreScreen
import com.fitquest.rpg.features.roadmap.RoadmapScreen
import com.fitquest.rpg.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

sealed class Screen(val route: String) {
    object Auth       : Screen("auth")
    object Onboarding : Screen("onboarding")
    object Dashboard  : Screen("dashboard")
    object Diet       : Screen("diet")
    object Attributes : Screen("attributes")
    object Store      : Screen("store")
    object Profile    : Screen("profile")
    object Roadmap    : Screen("roadmap")
}

data class BottomNavItem(val screen: Screen, val icon: ImageVector, val label: String)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitQuestTheme {
                FitQuestNavigation(firebaseAuth)
            }
        }
    }
}

@Composable
fun FitQuestNavigation(firebaseAuth: FirebaseAuth) {
    val navController = rememberNavController()

    // Determine start destination based on auth state
    val startDestination = remember {
        val user = firebaseAuth.currentUser
        when {
            user == null -> Screen.Auth.route        // Not logged in → Auth
            else -> Screen.Dashboard.route           // Logged in → Dashboard (onboarding check happens inside)
        }
    }

    val bottomNavItems = listOf(
        BottomNavItem(Screen.Dashboard,  Icons.Default.Home,         "Home"),
        BottomNavItem(Screen.Diet,       Icons.Default.LocalDining,  "Diet"),
        BottomNavItem(Screen.Attributes, Icons.Default.Star,         "Stats"),
        BottomNavItem(Screen.Store,      Icons.Default.ShoppingCart, "Store"),
        BottomNavItem(Screen.Profile,    Icons.Default.Person,       "Profile")
    )

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDest = currentBackStack?.destination
    val showBottomNav = currentDest?.route !in listOf(Screen.Auth.route, Screen.Onboarding.route)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black,
        bottomBar = {
            if (showBottomNav) {
                NavigationBar(
                    containerColor = Color.Black,
                    tonalElevation = 0.dp,
                    modifier = Modifier.background(Color.Black).drawBehind {
                        drawLine(
                            color = BorderNavy,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 1.dp.toPx()
                        )
                    }
                ) {
                    bottomNavItems.forEach { item ->
                        val selected = currentDest?.hierarchy?.any { it.route == item.screen.route } == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(item.screen.route) {
                                    popUpTo(Screen.Dashboard.route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor   = Color.White,
                                selectedTextColor   = Color.White,
                                indicatorColor      = Color.Transparent,
                                unselectedIconColor = Color(0xFF666666),
                                unselectedTextColor = Color(0xFF666666)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            // ── Auth ────────────────────────────────────────────────────────
            composable(Screen.Auth.route) {
                val authVm = hiltViewModel<AuthViewModel>()
                AuthScreen(
                    onAuthSuccess = { isNewUser ->
                        if (isNewUser) {
                            // New registration → onboarding
                            navController.navigate(Screen.Onboarding.route) {
                                popUpTo(Screen.Auth.route) { inclusive = true }
                            }
                        } else {
                            // Existing user → dashboard (will load their Firestore data)
                            navController.navigate(Screen.Dashboard.route) {
                                popUpTo(Screen.Auth.route) { inclusive = true }
                            }
                        }
                    },
                    viewModel = authVm
                )
            }

            // ── Onboarding ──────────────────────────────────────────────────
            composable(Screen.Onboarding.route) {
                val vm = hiltViewModel<OnboardingViewModel>()
                OnboardingScreen(
                    onComplete = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    },
                    viewModel = vm
                )
            }

            // ── Main Tabs ───────────────────────────────────────────────────
            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    onNavigateToStore = {
                        navController.navigate(Screen.Store.route) {
                            popUpTo(Screen.Dashboard.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onNavigateToAttributes = {
                        navController.navigate(Screen.Attributes.route) {
                            popUpTo(Screen.Dashboard.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

            composable(Screen.Diet.route) {
                DietScreen()
            }

            composable(Screen.Attributes.route) {
                AttributesScreen(
                    onBack = { navController.popBackStack() },
                    onNavigateToRoadmap = { navController.navigate(Screen.Roadmap.route) }
                )
            }

            composable(Screen.Roadmap.route) {
                RoadmapScreen(onBack = { navController.popBackStack() })
            }

            composable(Screen.Store.route) {
                StoreScreen(onBack = { navController.popBackStack() })
            }

            composable(Screen.Profile.route) {
                val authVm = hiltViewModel<AuthViewModel>()
                ProfileScreen(
                    onLogout = {
                        authVm.signOut {
                            navController.navigate(Screen.Auth.route) {
                                popUpTo(0) { inclusive = true } // Clear entire back stack
                            }
                        }
                    }
                )
            }
        }
    }
}
