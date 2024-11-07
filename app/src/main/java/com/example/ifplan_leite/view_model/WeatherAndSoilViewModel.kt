package com.example.ifplan_leite.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifplan_leite.data.repository.WeatherAndSoilRepository
import com.example.ifplan_leite.data.state.WeatherAndSoilState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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
    private val _weatherAndSoilState = MutableStateFlow(WeatherAndSoilState())
    val weatherAndSoilState: StateFlow<WeatherAndSoilState> = _weatherAndSoilState.asStateFlow()
    var loadingJob: Job? = null

    fun updatePrecipitation(value: Double) { _weatherAndSoilState.update { it.copy( precipitation = value ) }}
    fun updateMaxTemperature(value: Double) { _weatherAndSoilState.update { it.copy( maxTemperature = value ) } }
    fun updateMinTemperature(value: Double) { _weatherAndSoilState.update { it.copy( minTemperature = value ) } }
    fun updateRelativeHumidity(value: Double) { _weatherAndSoilState.update { it.copy( relativeHumidity = value ) } }
    fun updateVelocityVents(value: Double) { _weatherAndSoilState.update { it.copy( velocityVents = value ) } }
    fun updateNDosage(value: Double) { _weatherAndSoilState.update { it.copy( nDosage = value ) } }
    fun updateOtherAndWater(value: Double) { _weatherAndSoilState.update { it.copy( otherAndWater = value ) } }

    init {
        loadWeatherAndSoilData()
    }

    fun loadWeatherAndSoilData() {
        loadingJob?.cancel()
        viewModelScope.launch {
            try {
                _weatherAndSoilState.update { it.copy( isSaving = true) }

                // TODO: Add error string to string resources
                weatherAndSoilRepository.getWeatherAndSoil()
                    .catch { error ->
                        _weatherAndSoilState.update { it.copy(
                            error = "Error ao carregar dados $error.message",
                            isSuccess = false,
                            isSaving = false
                        ) }
                    }
                    .collect { weatherAndSoil ->
                        if(weatherAndSoil != null) {
                            _weatherAndSoilState.update { it.copy(
                                precipitation = weatherAndSoil.precipitation,
                                maxTemperature = weatherAndSoil.maxTemperature,
                                minTemperature = weatherAndSoil.minTemperature,
                                relativeHumidity = weatherAndSoil.relativeHumidity,
                                velocityVents = weatherAndSoil.velocityVents,
                                nDosage = weatherAndSoil.nDosage,
                                otherAndWater = weatherAndSoil.otherAndWater,
                                isSuccess = true,
                                error = null,
                                isSaving = false
                                )
                            }
                        } else {
                            _weatherAndSoilState.update { it.copy(
                                isSuccess = true,
                                isSaving = false
                            ) }
                        }
                    }
            } catch (error: Exception) {
                // TODO: Add error string to string resources
                _weatherAndSoilState.update { weatherAndSoil ->
                    weatherAndSoil.copy(
                        error = "Error ao carregar dados $error.message",
                        isSuccess = false,
                        isSaving = false
                    )
                }
            }
        }
    }

    fun saveWeatherAndSoil() {
        viewModelScope.launch {
            _weatherAndSoilState.update { it.copy( isSaving = true ) }
            try {
                val currState = _weatherAndSoilState.value
                with(currState) {
                    weatherAndSoilRepository.saveWeatherAndSoil(
                        precipitation = precipitation,
                        maxTemperature = maxTemperature,
                        minTemperature = minTemperature,
                        relativeHumidity = relativeHumidity,
                        velocityVents = velocityVents,
                        nDosage = nDosage,
                        otherAndWater = otherAndWater
                    )
                }

                _weatherAndSoilState.update {
                    it.copy(
                        isSuccess = true,
                        error = null,
                        isSaving = false
                    )
                }
            } catch(error: Exception) {
            // TODO: Add error to string resources
                _weatherAndSoilState.update {it.copy(
                    error = "Error ao salvar dados $error.message",
                    isSuccess = false,
                    isSaving = false
                )}
            }
        }
    }
}


