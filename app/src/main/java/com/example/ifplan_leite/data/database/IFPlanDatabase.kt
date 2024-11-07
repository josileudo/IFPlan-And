package com.example.ifplan_leite.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ifplan_leite.data.dao.AnimalDao
import com.example.ifplan_leite.data.dao.AreaDao
import com.example.ifplan_leite.data.entities.Animal
import com.example.ifplan_leite.data.entities.Area

@Database(
    entities = [Animal::class, Area::class],
    version = 1,
    exportSchema = true
)

abstract class IFPlanDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
    abstract fun areaDao(): AreaDao

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
