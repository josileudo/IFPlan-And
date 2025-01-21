package com.app.ifplan_leite.ui.screen.weatherAndSoil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ifplan_leite.core.data.entities.WeatherAndSoil
import com.app.ifplan_leite.core.data.repository.WeatherAndSoilRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherAndSoilViewModel @Inject constructor(
    private val weatherAndSoilRepository: WeatherAndSoilRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(WeatherAndSoilUiState())
    var uiState: StateFlow<WeatherAndSoilUiState> = _uiState.asStateFlow()

    fun onEvent(event: WeatherAndSoilUiEvent) {
        when (event) {
            is WeatherAndSoilUiEvent.OnFetchWeatherAndSoilResult -> onFetchWeatherAndSoilResult()
            is WeatherAndSoilUiEvent.OnFetchWeatherAndSoilById -> onFetchWeatherAndSoilById(event.id)
            is WeatherAndSoilUiEvent.OnUpdateWeatherAndSoilFields -> onUpdateWeatherAndSoilFields(
                field = event.field,
                value = event.value
            )
            is WeatherAndSoilUiEvent.OnSaveWeatherAndSoil -> onSaveWeatherAndSoil()
            else -> {}
        }
    }

    private fun onUpdateWeatherAndSoilFields(field: String, value: Double) {
        _uiState.update {
            when (field) {
                "precipitation" -> it.copy(precipitation = value)
                "maxTemperature" -> it.copy(maxTemperature = value)
                "minTemperature" -> it.copy(minTemperature = value)
                "relativeHumidity" -> it.copy(relativeHumidity = value)
                "velocityVents" -> it.copy(velocityVents = value)
                "nDosage" -> it.copy(nDosage = value)
                "otherAndWater" -> it.copy(otherAndWater = value)
                "waterAvailableToIrrigation" -> it.copy(waterAvailableToIrrigation = value)
                else -> it
            }
        }
    }

    private fun onFetchWeatherAndSoilResult() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            weatherAndSoilRepository.getWeatherAndSoil()
                .catch { error ->
                    _uiState.update { it.copy(isLoading = false) }
                }
                .collect { weatherAndSoil ->
                    if (weatherAndSoil != null) {
                        _uiState.value = _uiState.value.copy(
                            weatherAndSoilResult = weatherAndSoil,
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun onSaveWeatherAndSoil() {
        viewModelScope.launch {
            try {
                val weatherAndSoilResult = WeatherAndSoil(
                    precipitation = uiState.value.precipitation,
                    maxTemperature = uiState.value.maxTemperature,
                    minTemperature = uiState.value.minTemperature,
                    relativeHumidity = uiState.value.relativeHumidity,
                    velocityVents = uiState.value.velocityVents,
                    nDosage = uiState.value.nDosage,
                    otherAndWater = uiState.value.otherAndWater,
                    waterAvailableToIrrigation = uiState.value.waterAvailableToIrrigation
                )
                _uiState.value = _uiState.value.copy(
                    weatherAndSoilResult = weatherAndSoilResult
                )
                println("***" + uiState.value)
//                weatherAndSoilRepository.saveWeatherAndSoil(weatherAndSoilResult)
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    private fun onFetchWeatherAndSoilById(weatherAndSoilId: Long) {

    }
}
