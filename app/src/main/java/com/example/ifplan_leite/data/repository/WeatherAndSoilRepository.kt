package com.example.ifplan_leite.data.repository

import com.example.ifplan_leite.data.dao.WeatherAndSoilDao
import com.example.ifplan_leite.data.entities.WeatherAndSoil
import com.example.ifplan_leite.data.state.WeatherAndSoilState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherAndSoilRepository @Inject constructor(
    private val weatherAndSoilDao: WeatherAndSoilDao
) {
    val _weatherAndSoilState = MutableStateFlow(WeatherAndSoilState())
    val weatherAndSoilState: StateFlow<WeatherAndSoilState> = _weatherAndSoilState.asStateFlow()
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    val loadingJob: Job? = null

    init {
        loadWeatherAndSoilData()
    }

    fun getWeatherAndSoil() = weatherAndSoilDao.getWeatherAndSoil()

    fun loadWeatherAndSoilData() {
        loadingJob?.cancel()
        coroutineScope.launch {
            try {
                _weatherAndSoilState.update { it.copy( isSaving = true) }

                // TODO: Add error string to string resources
                getWeatherAndSoil()
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
        coroutineScope.launch {
            _weatherAndSoilState.update { it.copy(isSaving = true) }
            try {
                val currState = _weatherAndSoilState.value
                with(currState) {
                    val newValue = WeatherAndSoil(
                        precipitation = precipitation,
                        maxTemperature = maxTemperature,
                        minTemperature = minTemperature,
                        relativeHumidity = relativeHumidity,
                        velocityVents = velocityVents,
                        nDosage = nDosage,
                        otherAndWater = otherAndWater
                    )
                    weatherAndSoilDao.insertOrUpdate(newValue)

                }

                _weatherAndSoilState.update {
                    it.copy(
                        isSuccess = true,
                        error = null,
                        isSaving = false
                    )
                }
            } catch (error: Exception) {
                // TODO: Add error to string resources
                _weatherAndSoilState.update {
                    it.copy(
                        error = "Error ao salvar dados $error.message",
                        isSuccess = false,
                        isSaving = false
                    )
                }
            }
        }
    }

    suspend fun clearWeatherAndSoil() = weatherAndSoilDao.deleteWeatherAndSoil()
}
