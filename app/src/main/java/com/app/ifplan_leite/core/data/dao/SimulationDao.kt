package com.app.ifplan_leite.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.app.ifplan_leite.core.data.entities.ResultSimulation
import com.app.ifplan_leite.core.data.entities.Simulation
import com.app.ifplan_leite.core.data.model.SimulationWithDetails

@Dao
interface SimulationDao {
    @Insert
    suspend fun insertSimulation(simulation: Simulation): Long

    @Insert
    suspend fun insertResultSimulation(resultSimulation: ResultSimulation): Long

    @Transaction
    @Query("SELECT * FROM ifplan_simulation WHERE id = :simulationId")
    suspend fun getSimulationWithDetails(simulationId: Long): SimulationWithDetails

    @Query("SELECT id, creationDate, title, description FROM ifplan_simulation")
    suspend fun getAllSimulations(): List<Simulation>
}

