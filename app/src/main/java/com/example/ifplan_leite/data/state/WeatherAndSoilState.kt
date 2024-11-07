package com.example.ifplan_leite.data.state

data class WeatherAndSoilState (
    val precipitation: Double = 0.0,
    val maxTemperature: Double = 0.0,
    val minTemperature: Double = 0.0,
    val relativeHumidity:  Double = 0.0,
    val velocityVents: Double = 0.0,
    val nDosage: Double = 0.0,
    val otherAndWater: Double = 0.0,
    val error: String? = null,
    val isSaving: Boolean = false,
    val isSuccess: Boolean = false,
)

