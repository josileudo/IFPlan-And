package com.app.ifplan_leite.core.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.app.ifplan_leite.core.data.entities.ResultSimulation
import com.app.ifplan_leite.core.data.entities.Simulation
import kotlinx.serialization.Serializable

data class SimulationWithDetails(
    @Embedded val simulation: Simulation,
    @Relation(
        parentColumn = "id",
        entityColumn = "simulationId"
    )
    val resultSimulation: ResultSimulation
)