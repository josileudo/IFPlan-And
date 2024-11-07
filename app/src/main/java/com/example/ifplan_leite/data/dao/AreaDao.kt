package com.example.ifplan_leite.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.ifplan_leite.data.entities.Area
import kotlinx.coroutines.flow.Flow

@Dao
interface AreaDao {
    @Query("SELECT * FROM area_database WHERE id = 1")
    fun getArea(): Flow<Area?>

    @Transaction
    suspend fun insertOrUpdate(area: Area) {
        val exists = getAreaSync() != null
        if (exists) {
            update(area)
        } else {
            insert(area)
        }
    }

    @Query("SELECT * FROM area_database WHERE id = 1")
    suspend fun getAreaSync(): Area?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(area: Area)

    @Update
    suspend fun update(area: Area)

    @Query("DELETE FROM area_database")
    suspend fun deleteArea()
}