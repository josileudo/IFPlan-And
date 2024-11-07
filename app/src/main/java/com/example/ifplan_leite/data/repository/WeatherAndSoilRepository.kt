package com.example.ifplan_leite.data.repository

import com.example.ifplan_leite.data.dao.WeatherAndSoilDao
import com.example.ifplan_leite.data.entities.WeatherAndSoil
import javax.inject.Inject

class WeatherAndSoilRepository @Inject constructor(
    private val weatherAndSoilDao: WeatherAndSoilDao
) {
    fun getWeatherAndSoil() = weatherAndSoilDao.getWeatherAndSoil()

    suspend fun saveWeatherAndSoil(
        precipitation: Double,
        maxTemperature: Double,
        minTemperature: Double,
        relativeHumidity:  Double,
        velocityVents: Double,
        nDosage: Double,
        otherAndWater: Double
    ) {
        val weatherAndSoilValue = WeatherAndSoil(
            precipitation = precipitation,
            maxTemperature = maxTemperature,
            minTemperature = minTemperature,
            relativeHumidity = relativeHumidity,
            velocityVents = velocityVents,
            nDosage = nDosage,
            otherAndWater = otherAndWater
        )

        weatherAndSoilDao.insertOrUpdate(weatherAndSoilValue)
    }

    suspend fun clearWeatherAndSoil() = weatherAndSoilDao.deleteWeatherAndSoil()
}
