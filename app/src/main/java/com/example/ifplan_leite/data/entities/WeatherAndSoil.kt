package com.example.ifplan_leite.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_and_soil_database")
data class WeatherAndSoil(
        @PrimaryKey(autoGenerate = true) val id: Int = 1,
        val precipitation: Double = 0.0,
        val maxTemperature: Double = 0.0,
        val minTemperature: Double = 0.0,
        val relativeHumidity:  Double = 0.0,
        val velocityVents: Double = 0.0,
        val nDosage: Double = 0.0,
        val otherAndWater: Double = 0.0,
    )
