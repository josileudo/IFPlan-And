package com.app.ifplan_leite.core.data.repository

import androidx.lifecycle.viewModelScope
import com.app.ifplan_leite.core.data.dao.SimulationDao
import com.app.ifplan_leite.core.data.entities.ResultSimulation
import com.app.ifplan_leite.core.data.entities.Simulation
import java.util.Date
import javax.inject.Inject

data class SimulationRepository @Inject constructor(
    val simulationDao: SimulationDao
) {
    suspend fun getAllSimulations() = simulationDao.getAllSimulations()

    suspend fun getResultSimulationById(id: Long) {
        simulationDao.getSimulationWithDetails(id = id)
    }

    suspend fun insertSimulationItem(simulation: Simulation) {
        simulationDao.insertSimulation(simulation = simulation)
    }

    suspend fun insertResultSimulation(resultSimulation: ResultSimulation) {
        simulationDao.insertResultSimulation(resultSimulation = resultSimulation)
    }
}
