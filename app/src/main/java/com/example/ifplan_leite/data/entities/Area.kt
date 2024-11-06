package com.example.ifplan_leite.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "area_database")
data class Area(
    @PrimaryKey(autoGenerate = true) val id: Long = 2,
    val area: Double,
    val picketsNumber: Double,
)
