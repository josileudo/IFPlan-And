package com.app.ifplan_leite.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.ifplan_leite.data.dao.AnimalDao
import com.app.ifplan_leite.data.dao.AreaDao
import com.app.ifplan_leite.data.dao.EconomyDao
import com.app.ifplan_leite.data.dao.WeatherAndSoilDao
import com.app.ifplan_leite.data.entities.Animal
import com.app.ifplan_leite.data.entities.Area
import com.app.ifplan_leite.data.entities.Economy
import com.app.ifplan_leite.data.entities.WeatherAndSoil

@Database(
    entities = [Animal::class, Area::class, Economy::class, WeatherAndSoil::class],
    version = 1,
    exportSchema = true
)

abstract class IFPlanDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
    abstract fun areaDao(): AreaDao
    abstract fun economyDao(): EconomyDao
    abstract fun weatherAndSoilDao(): WeatherAndSoilDao

    companion object {
        @Volatile
        private var Instance: IFPlanDatabase? = null

        fun getDatabase(context: Context): IFPlanDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    IFPlanDatabase::class.java,
                    "ifplan_database"
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
