package com.app.ifplan_leite.ui.screen.weatherAndSoil.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.app.ifplan_leite.R
import com.app.ifplan_leite.ui.components.form.IfPlanFormContainer
import com.app.ifplan_leite.ui.screen.route.Routes
import com.app.ifplan_leite.ui.screen.weatherAndSoil.WeatherAndSoilUiEvent
import com.app.ifplan_leite.ui.screen.weatherAndSoil.WeatherAndSoilUiState

@Composable
fun WeatherAndSoilFormScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    uiState: WeatherAndSoilUiState? = null,
    onEvent: (WeatherAndSoilUiEvent) -> Unit = {},
) {
//    LaunchedEffect(Unit) {
//        weatherAndSoilViewModel.loadWeatherAndSoilData()
//    }

    Box(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        IfPlanFormContainer(
            formTitle = stringResource(R.string.weatherAndSoil),
            onNavigateBack = { navController?.popBackStack() },
            onSaveClick = {
                onEvent(WeatherAndSoilUiEvent.OnSaveWeatherAndSoil)
                navController?.navigate(Routes.dashboard)
//                if(weatherAndSoilState.isSuccess) {
//                    navController?.navigate(Routes.dashboard)
//                }
            }
        ) {
            WeatherAndSoilFormView(
                precipitation = uiState?.precipitation ?: 0.0,
                maxTemperature = uiState?.maxTemperature ?: 0.0,
                minTemperature = uiState?.minTemperature ?: 0.0,
                relativeHumidity = uiState?.relativeHumidity ?: 0.0,
                velocityVents = uiState?.velocityVents ?: 0.0,
                nDosage = uiState?.nDosage ?: 0.0,
                otherAndWater = uiState?.otherAndWater ?: 0.0,
                waterAvailableToIrrigation = uiState?.waterAvailableToIrrigation ?: 0.0,
                onFieldChange = { field, value ->
                    onEvent(WeatherAndSoilUiEvent.OnUpdateWeatherAndSoilFields(field, value))
                })
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AnimalFormScreenPreview() {
    WeatherAndSoilFormScreen()
}
