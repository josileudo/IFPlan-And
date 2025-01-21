package com.app.ifplan_leite.ui.screen.weatherAndSoil

sealed class WeatherAndSoilUiEvent {
    data object OnFetchWeatherAndSoilResult : WeatherAndSoilUiEvent()
    data object OnSaveWeatherAndSoil : WeatherAndSoilUiEvent()
    data class OnFetchWeatherAndSoilById(var id: Long) : WeatherAndSoilUiEvent()
    data class OnUpdateWeatherAndSoilFields(var field: String, var value: Double) :
        WeatherAndSoilUiEvent()
}