package com.app.ifplan_leite.ui.screen.weatherAndSoil

import com.app.ifplan_leite.core.data.entities.WeatherAndSoil

data class WeatherAndSoilUiState(
    val weatherAndSoilResult: WeatherAndSoil? = null,
    val isLoading: Boolean = false,

    val precipitation: Double = 0.0,
    val maxTemperature: Double = 0.0,
    val minTemperature: Double = 0.0,
    val relativeHumidity: Double = 0.0,
    val velocityVents: Double = 0.0,
    val nDosage: Double = 0.0,
    val otherAndWater: Double = 0.0,
    val waterAvailableToIrrigation: Double = 0.0,
)
