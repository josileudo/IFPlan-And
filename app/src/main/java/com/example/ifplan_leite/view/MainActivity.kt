package com.example.ifplan_leite.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ifplan_leite.Routes
import com.example.ifplan_leite.ui.theme.IFPlanLeiteTheme
import com.example.ifplan_leite.view.screens.animal.AnimalFormScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            IFPlanLeiteTheme {
                navController = rememberNavController()
                SetBarColor(MaterialTheme.colorScheme.background)

                NavHost(navController = navController, startDestination = Routes.home) {
                    composable(Routes.home) { HomeScreen(navController) }
                    composable(Routes.dashboard) { DashboardScreen(navController = navController) }
                    composable(Routes.animalInput) { AnimalFormScreen() }
                }
            }
        }
    }
}



@Composable
private fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}