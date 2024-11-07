package com.example.ifplan_leite.view.screens.weatherAndSoil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ifplan_leite.R
import com.example.ifplan_leite.Routes
import com.example.ifplan_leite.model.TitleAndValue
import com.example.ifplan_leite.view.components.CardInfoComponent
import com.example.ifplan_leite.view_model.WeatherAndSoilViewModel

@Composable
fun WeatherAndSoilView(
    weatherAndSoilViewModel: WeatherAndSoilViewModel = hiltViewModel(),
    navController: NavController?= null
){
    val weatherAndSoilState = weatherAndSoilViewModel.weatherAndSoilState.collectAsState().value
    val mockValues = listOf(
        TitleAndValue(stringResource(R.string.precipitation), weatherAndSoilState.precipitation.toString()),
        TitleAndValue(stringResource(R.string.maxTemperature), weatherAndSoilState.maxTemperature.toString()),
        TitleAndValue(stringResource(R.string.minTemperature), weatherAndSoilState.minTemperature.toString()),
        TitleAndValue(stringResource(R.string.relativeHumidity), weatherAndSoilState.relativeHumidity.toString()),
        TitleAndValue(stringResource(R.string.velocityVents), weatherAndSoilState.velocityVents.toString()),
        TitleAndValue(stringResource(R.string.nDosage), weatherAndSoilState.nDosage.toString()),
        TitleAndValue(stringResource(R.string.otherAndWater), weatherAndSoilState.otherAndWater.toString()),
    )

    CardInfoComponent(
        title = stringResource(R.string.weatherAndSoil),
        isLoading = weatherAndSoilState.isSaving,
        error = weatherAndSoilState.error,
        listItems = mockValues,
        onClick = {
            navController?.navigate(Routes.weatherAndSoilInput)
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeatherAndSoilViewPreview() {
    WeatherAndSoilView()
}


