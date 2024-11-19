package com.app.ifplan_leite.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.app.ifplan_leite.data.entities.WeatherAndSoil
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherAndSoilDao {
    @Query("SELECT * FROM weather_and_soil_database WHERE id = 1")
    fun getWeatherAndSoil(): Flow<WeatherAndSoil?>

    @Transaction
    suspend fun insertOrUpdate(weatherAndSoil: WeatherAndSoil) {
        val exists = getWeatherAndSoilSync() != null
        if (exists) {
            update(weatherAndSoil)
        } else {
            insert(weatherAndSoil)
        }
    }

    @Query("SELECT * FROM weather_and_soil_database WHERE id = 1")
    suspend fun getWeatherAndSoilSync(): WeatherAndSoil?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherAndSoil: WeatherAndSoil)

    @Update
    suspend fun update(weatherAndSoil: WeatherAndSoil)

    @Query("DELETE FROM weather_and_soil_database")
    suspend fun deleteWeatherAndSoil()
}