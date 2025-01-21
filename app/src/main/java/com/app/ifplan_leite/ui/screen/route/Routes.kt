package com.app.ifplan_leite.ui.screen.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

object Routes {
    var dashboard = "dashboard_screen"
    var animalInput = "animal_input_screen"
    var areaInput = "area_input_screen"
    var economyInput = "economy_input_screen"
    var weatherAndSoilInput = "weather_and_soil_screen"
}

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Home : BottomNavItem(
        route = "home",
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    object Profile : BottomNavItem(
        route = "profile",
        title = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )

    object Settings : BottomNavItem(
        route = "settings",
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Profile,
    BottomNavItem.Settings
)

//
//@Serializable
//data object Splash
//
//@Serializable
//data object Welcome
//
//@Serializable
//data object Home
//
//@Serializable
//data object Dashboard