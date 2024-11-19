package com.app.ifplan_leite.view_model

import androidx.lifecycle.ViewModel
import com.app.ifplan_leite.data.repository.WeatherAndSoilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WeatherAndSoilViewModel @Inject constructor(
    private val weatherAndSoilRepository: WeatherAndSoilRepository
) : ViewModel() {
    val weatherAndSoilState = weatherAndSoilRepository.weatherAndSoilState

    fun updatePrecipitation(value: Double) { weatherAndSoilRepository._weatherAndSoilState.update { it.copy( precipitation = value ) }}
    fun updateMaxTemperature(value: Double) { weatherAndSoilRepository._weatherAndSoilState.update { it.copy( maxTemperature = value ) } }
    fun updateMinTemperature(value: Double) { weatherAndSoilRepository._weatherAndSoilState.update { it.copy( minTemperature = value ) } }
    fun updateRelativeHumidity(value: Double) { weatherAndSoilRepository._weatherAndSoilState.update { it.copy( relativeHumidity = value ) } }
    fun updateVelocityVents(value: Double) { weatherAndSoilRepository._weatherAndSoilState.update { it.copy( velocityVents = value ) } }
    fun updateNDosage(value: Double) { weatherAndSoilRepository._weatherAndSoilState.update { it.copy( nDosage = value ) } }
    fun updateOtherAndWater(value: Double) { weatherAndSoilRepository._weatherAndSoilState.update { it.copy( otherAndWater = value ) } }

    init {
        loadWeatherAndSoilData()
    }

    fun loadWeatherAndSoilData() {
        weatherAndSoilRepository.getWeatherAndSoil()
    }

    fun saveWeatherAndSoil() {
        weatherAndSoilRepository.saveWeatherAndSoil()
    }
}


