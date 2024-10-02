package com.example.ifplan_leite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ifplan_leite.ui.theme.IFPlanLeiteTheme

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
                    composable(Routes.dashboard) { DashboardScreen() }
                    // ... other composable routes ...
                }

//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
private fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    IFPlanLeiteTheme {
//        Greeting("Android")
//    }
//}

//@Preview(showBackground =  true)
//@Composable
//fun HomeScreen() {
//    Scaffold(
////        modifier = Modifier.fillMaxSize()
//        bottomBar = {
//
//        }
//    ) { padding ->
//        Column (
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//        ){
//            // AREA
//            // ECONOMY
//            // WHEATER AND SOLO
//            // ANIMAL
//        }
//    }
//}