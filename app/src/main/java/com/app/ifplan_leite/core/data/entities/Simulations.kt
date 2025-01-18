package com.app.ifplan_leite.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "ifplan_simulation")
data class Simulation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val creationDate: Date = Date(),
    val description: String,
)
