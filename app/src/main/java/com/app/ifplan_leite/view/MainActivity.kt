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
import com.app.ifplan_leite.ui.screen.home.HomeScreen
import com.app.ifplan_leite.ui.screen.home.HomeViewModel
import com.app.ifplan_leite.ui.screen.result.DashboardScreen
import com.app.ifplan_leite.ui.screen.result.ResultViewModel
import com.app.ifplan_leite.ui.screen.route.BottomNavItem
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
            setOnExitAnimationListener{ screen ->
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

            setTheme(R.style.Theme_App_Splash)

                IFPlanLeiteTheme(dynamicColor = false) {
                    SetBarColor(MaterialTheme.colorScheme.background)
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
                            composable(BottomNavItem.Home.route) {
                                HomeScreen(navigationToNewSimulation = { selectSimulation ->
                                    //TODO Apply ternary after
                                    selectSimulation?.let { navController.navigate(it) }
                                },
                                    uiState = homeUiState,
                                    onEvent = homeViewModel::onEvent
                                ) }
                            composable<SimulateItems> {
                                val selectSimulateItems = it.toRoute<SimulateItems>()

                                DashboardScreen(
                                    modifier = Modifier.fillMaxWidth(),
                                    navController = navController,
                                    onEvent = resultViewModel::onEvent,
                                    uiState = resultUiState,
                                    simulateItems = selectSimulateItems,
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
fun ProfileScreen( ) {

}

@Composable
fun SettingsScreen( ) {

}

@Composable
private fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}