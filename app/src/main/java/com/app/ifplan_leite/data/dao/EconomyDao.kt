package com.app.ifplan_leite.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.app.ifplan_leite.data.entities.Economy
import kotlinx.coroutines.flow.Flow

@Dao
interface EconomyDao {
    @Query("SELECT * FROM economy_database WHERE id = 1")
    fun getEconomy(): Flow<Economy?>

    @Transaction
    suspend fun insertOrUpdate(economy: Economy) {
        val exists = getEconomySync() != null
        if (exists) {
            update(economy)
        } else {
            insert(economy)
        }
    }

    @Query("SELECT * FROM economy_database WHERE id = 1")
    suspend fun getEconomySync(): Economy?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(economy: Economy)

    @Update
    suspend fun update(economy: Economy)

    @Query("DELETE FROM economy_database")
    suspend fun deleteEconomy()
}