package com.app.ifplan_leite.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.app.ifplan_leite.data.entities.Animal
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {
    @Query("SELECT * FROM animal_database WHERE id = 1")
    fun getAnimal(): Flow<Animal?>

    @Transaction
    suspend fun insertOrUpdate(animal: Animal) {
        val exists = getAnimalSync() != null
        println("*** exists $exists")
        if (exists) {
            update(animal)
        } else {
            insert(animal)
        }
    }

    @Query("SELECT * FROM animal_database WHERE id = 1")
    suspend fun getAnimalSync(): Animal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(animal: Animal)

    @Update
    suspend fun update(animal: Animal)

    @Query("DELETE FROM animal_database")
    suspend fun deleteAnimal()
}