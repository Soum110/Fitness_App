package com.fitquest.rpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.fitquest.rpg.features.attributes.AttributesScreen
import com.fitquest.rpg.features.dashboard.DashboardScreen
import com.fitquest.rpg.features.diet.DietScreen
import com.fitquest.rpg.features.onboarding.OnboardingScreen
import com.fitquest.rpg.features.onboarding.OnboardingViewModel
import com.fitquest.rpg.features.profile.ProfileScreen
import com.fitquest.rpg.features.store.StoreScreen
import com.fitquest.rpg.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

sealed class Screen(val route: String) {
    object Onboarding  : Screen("onboarding")
    object Dashboard   : Screen("dashboard")
    object Diet        : Screen("diet")
    object Attributes  : Screen("attributes")
    object Store       : Screen("store")
    object Profile     : Screen("profile")
}

data class BottomNavItem(val screen: Screen, val icon: ImageVector, val label: String)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitQuestTheme {
                FitQuestNavigation()
            }
        }
    }
}

@Composable
fun FitQuestNavigation() {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem(Screen.Dashboard,  Icons.Default.Home,         "Home"),
        BottomNavItem(Screen.Diet,       Icons.Default.LocalDining,  "Diet"),
        BottomNavItem(Screen.Attributes, Icons.Default.Star,         "Stats"),
        BottomNavItem(Screen.Store,      Icons.Default.ShoppingCart, "Store"),
        BottomNavItem(Screen.Profile,    Icons.Default.Person,       "Profile")
    )

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDest = currentBackStack?.destination
    val showBottomNav = currentDest?.route != Screen.Onboarding.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DeepNavy,
        bottomBar = {
            if (showBottomNav) {
                NavigationBar(containerColor = CardNavy) {
                    bottomNavItems.forEach { item ->
                        val selected = currentDest?.hierarchy?.any { it.route == item.screen.route } == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor   = NeonPurple,
                                selectedTextColor   = NeonPurple,
                                indicatorColor      = NeonPurple.copy(alpha = 0.15f),
                                unselectedIconColor = Color(0xFF6B7280),
                                unselectedTextColor = Color(0xFF6B7280)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Onboarding.route,
            modifier = Modifier.padding(innerPadding)
        ) {
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

            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    onNavigateToStore      = { navController.navigate(Screen.Store.route) },
                    onNavigateToAttributes = { navController.navigate(Screen.Attributes.route) }
                )
            }

            composable(Screen.Diet.route) {
                DietScreen()
            }

            composable(Screen.Attributes.route) {
                AttributesScreen(onBack = { navController.popBackStack() })
            }

            composable(Screen.Store.route) {
                StoreScreen(onBack = { navController.popBackStack() })
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}
