package com.app.ifplan_leite.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.ifplan_leite.ui.components.navigation.BottomNavigationBar
import com.app.ifplan_leite.ui.screen.home.HomeScreen
import com.app.ifplan_leite.ui.screen.route.BottomNavItem
import com.app.ifplan_leite.ui.screen.route.Routes
import com.app.ifplan_leite.view.DashboardScreen


@Composable
fun MainScreen(navController: NavHostController) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    Scaffold(
        // Check what's of screens have bottom bar
        bottomBar = {
            if(currentRoute in listOf(
                BottomNavItem.Home.route,
                BottomNavItem.Profile.route,
                BottomNavItem.Settings.route
            )) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen(navigationToNewSimulation = { navController.navigate(Routes.dashboard) }) }
            composable(BottomNavItem.Profile.route) { ProfileScreen() }
            composable(BottomNavItem.Settings.route) { SettingsScreen() }

            composable(Routes.dashboard) { DashboardScreen(navController = navController) }
        }
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Profile Screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Settings Screen")
    }
}