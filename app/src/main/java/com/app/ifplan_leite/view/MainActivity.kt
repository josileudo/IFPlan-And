package com.app.ifplan_leite.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.compose.IFPlanLeiteTheme
import com.app.ifplan_leite.R
import com.app.ifplan_leite.ui.screen.route.Routes
import com.app.ifplan_leite.core.data.model.BottomNavItem
import com.app.ifplan_leite.ui.screen.animal.IfPlanAnimalScreen
import com.app.ifplan_leite.ui.screen.area.AreaFormScreen
import com.app.ifplan_leite.ui.screen.economy.EconomyFormScreen
import com.app.ifplan_leite.ui.screen.weatherAndSoil.WeatherAndSoilFormScreen
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
            setTheme(R.style.Theme_App_Splash)
            var selected by remember { mutableIntStateOf(0) }

            Scaffold { innerPadding ->
                IFPlanLeiteTheme(dynamicColor = false) {
                    SetBarColor(MaterialTheme.colorScheme.background)

                    NavHost(modifier = Modifier.padding(innerPadding), navController = navController, startDestination = Routes.home, builder =  {
                        composable(Routes.home) { HomeScreen(navController) }
                        composable(Routes.dashboard) { DashboardScreen(navController = navController) }
                        composable(Routes.animalInput) { IfPlanAnimalScreen(navController = navController) }
                        composable(Routes.areaInput) { AreaFormScreen(navController = navController) }
                        composable(Routes.economyInput) { EconomyFormScreen(navController = navController) }
                        composable(Routes.weatherAndSoilInput) { WeatherAndSoilFormScreen(navController = navController) }
                    })
                }
            }
        }
    }
}

var bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route = Routes.home,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    BottomNavItem(
        title = "Profile",
        route = Routes.dashboard,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    ),
)

@Composable
private fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}