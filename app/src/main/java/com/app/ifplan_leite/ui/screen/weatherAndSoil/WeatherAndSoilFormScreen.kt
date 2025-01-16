package com.app.ifplan_leite.ui.screen.weatherAndSoil

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.ifplan_leite.R
import com.app.ifplan_leite.ui.screen.route.Routes
import com.app.ifplan_leite.ui.components.form.IfPlanFormContainer
import com.app.ifplan_leite.ui.screen.weatherAndSoil.components.WeatherAndSoilFormView
import com.app.ifplan_leite.view.WeatherAndSoilViewModel

@Composable
fun WeatherAndSoilFormScreen(
    modifier: Modifier = Modifier,
    weatherAndSoilViewModel: WeatherAndSoilViewModel = hiltViewModel(),
    navController: NavController? = null
) {
    val weatherAndSoilState = weatherAndSoilViewModel.weatherAndSoilState.collectAsState().value

    LaunchedEffect(Unit) {
        weatherAndSoilViewModel.loadWeatherAndSoilData()
    }

    Box(
        modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ){
        IfPlanFormContainer(
            formTitle = stringResource(R.string.weatherAndSoil),
            onNavigateBack = { navController?.popBackStack() },
            onSaveClick = {
                weatherAndSoilViewModel.saveWeatherAndSoil()

                if(weatherAndSoilState.isSuccess) {
//                    navController?.navigate(Routes.dashboard)
                }
            }
        ) {
            WeatherAndSoilFormView( weatherAndSoilViewModel = weatherAndSoilViewModel )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AnimalFormScreenPreview() {
    WeatherAndSoilFormScreen()
}
