package com.app.ifplan_leite.core.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.ifplan_leite.core.data.dao.AnimalDao
import com.app.ifplan_leite.core.data.dao.AreaDao
import com.app.ifplan_leite.core.data.dao.EconomyDao
import com.app.ifplan_leite.core.data.dao.SimulationDao
import com.app.ifplan_leite.core.data.dao.WeatherAndSoilDao
import com.app.ifplan_leite.core.data.entities.Animal
import com.app.ifplan_leite.core.data.entities.Area
import com.app.ifplan_leite.core.data.entities.Economy
import com.app.ifplan_leite.core.data.entities.ResultSimulation
import com.app.ifplan_leite.core.data.entities.Simulation
import com.app.ifplan_leite.core.data.entities.WeatherAndSoil
import com.app.ifplan_leite.core.data.model.utils.IFPlanConverters

@Database(
    entities = [
                    Animal::class,
                    Area::class,
                    Economy::class,
                    WeatherAndSoil::class,
                    Simulation::class,
                    ResultSimulation::class
               ],
    version = 1,
    exportSchema = true
)

@TypeConverters(IFPlanConverters::class)
abstract class IFPlanDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
    abstract fun areaDao(): AreaDao
    abstract fun economyDao(): EconomyDao
    abstract fun weatherAndSoilDao(): WeatherAndSoilDao
    abstract fun simulationDao(): SimulationDao

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
