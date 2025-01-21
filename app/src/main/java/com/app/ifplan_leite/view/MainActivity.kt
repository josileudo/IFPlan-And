package com.app.ifplan_leite.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.app.compose.IFPlanLeiteTheme
import com.app.ifplan_leite.R
import com.app.ifplan_leite.core.data.state.SimulateItems
import com.app.ifplan_leite.ui.components.navigation.BottomNavigationBar
import com.app.ifplan_leite.ui.screen.animal.AnimalViewModel
import com.app.ifplan_leite.ui.screen.animal.components.AnimalFormScreen
import com.app.ifplan_leite.ui.screen.area.AreaViewModel
import com.app.ifplan_leite.ui.screen.area.components.AreaFormScreen
import com.app.ifplan_leite.ui.screen.economy.EconomyViewModel
import com.app.ifplan_leite.ui.screen.economy.components.EconomyFormScreen
import com.app.ifplan_leite.ui.screen.home.HomeScreen
import com.app.ifplan_leite.ui.screen.home.HomeViewModel
import com.app.ifplan_leite.ui.screen.result.DashboardScreen
import com.app.ifplan_leite.ui.screen.result.ResultViewModel
import com.app.ifplan_leite.ui.screen.route.BottomNavItem
import com.app.ifplan_leite.ui.screen.route.Routes
import com.app.ifplan_leite.ui.screen.weatherAndSoil.WeatherAndSoilViewModel
import com.app.ifplan_leite.ui.screen.weatherAndSoil.components.WeatherAndSoilFormScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        actionBar?.hide()
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )

                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd {
                    screen.remove()
                }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f,
                    0.0f
                )

                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd {
                    screen.remove()
                }

                zoomX.start()
                zoomY.start()
            }
        }

        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            val currentBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry.value?.destination?.route

            val homeViewModel: HomeViewModel = hiltViewModel()
            val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

            val resultViewModel: ResultViewModel = hiltViewModel()
            val resultUiState by resultViewModel.uiState.collectAsStateWithLifecycle()

            val areaViewModel: AreaViewModel = hiltViewModel()
            val areaUiState by areaViewModel.uiState.collectAsStateWithLifecycle()
            val animalViewModel: AnimalViewModel = hiltViewModel()
            val animalUiState by animalViewModel.uiState.collectAsStateWithLifecycle()
            val economyViewModel: EconomyViewModel = hiltViewModel()
            val economyUiState by economyViewModel.uiState.collectAsStateWithLifecycle()
            val weatherAndSoilViewModel: WeatherAndSoilViewModel = hiltViewModel()
            val weatherAndSoilUiState by weatherAndSoilViewModel.uiState.collectAsStateWithLifecycle()

            setTheme(R.style.Theme_App_Splash)
            IFPlanLeiteTheme(dynamicColor = false) {
                SetBarColor(MaterialTheme.colorScheme.background)
                Scaffold(
                    // Check what's of screens have bottom bar
                    bottomBar = {
                        if (currentRoute in listOf(
                                BottomNavItem.Home.route,
                                BottomNavItem.Profile.route,
                                BottomNavItem.Settings.route
                            )
                        ) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = BottomNavItem.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(BottomNavItem.Home.route) {
                            HomeScreen(
                                navigationToNewSimulation = { selectSimulation ->
                                    //TODO Apply ternary after
                                    selectSimulation?.let {
                                        navController.navigate(it)
                                    } ?: navController.navigate(Routes.dashboard)
                                },
                                uiState = homeUiState,
                                onEvent = homeViewModel::onEvent
                            )
                        }
                        composable<SimulateItems> {
                            val selectSimulateItems = it.toRoute<SimulateItems>()
                            DashboardScreen(
                                modifier = Modifier.fillMaxWidth(),
                                navController = navController,
                                onEvent = resultViewModel::onEvent,
                                uiState = resultUiState,
                                simulateItems = selectSimulateItems,
                                areaUiState = areaUiState,
                                animalUiState = animalUiState,
                                economyUiState = economyUiState,
                                weatherAndSoilUiState = weatherAndSoilUiState
                            )
                        }
                        composable(Routes.dashboard) {
                            DashboardScreen(
                                modifier = Modifier.fillMaxWidth(),
                                navController = navController,
                                onEvent = resultViewModel::onEvent,
                                uiState = resultUiState,
                                areaUiState = areaUiState,
                                animalUiState = animalUiState,
                                economyUiState = economyUiState,
                                weatherAndSoilUiState = weatherAndSoilUiState
                            )
                        }
                        composable(Routes.animalInput) {
                            AnimalFormScreen(
                                navController = navController,
                                uiState = animalUiState,
                                onEvent = animalViewModel::onEvent
                            )
                        }
                        composable(Routes.areaInput) {
                            AreaFormScreen(
                                navController = navController,
                                uiState = areaUiState,
                                onEvent = areaViewModel::onEvent
                            )
                        }
                        composable(Routes.economyInput) {
                            EconomyFormScreen(
                                navController = navController,
                                uiState = economyUiState,
                                onEvent = economyViewModel::onEvent
                            )
                        }
                        composable(Routes.weatherAndSoilInput) {
                            WeatherAndSoilFormScreen(
                                navController = navController,
                                uiState = weatherAndSoilUiState,
                                onEvent = weatherAndSoilViewModel::onEvent
                            )
                        }
                        composable(BottomNavItem.Profile.route) { ProfileScreen() }
                        composable(BottomNavItem.Settings.route) { SettingsScreen() }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {

}

@Composable
fun SettingsScreen() {

}

@Composable
private fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}