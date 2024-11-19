package com.app.ifplan_leite.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "economy_database")
data class Economy(
        @PrimaryKey(autoGenerate = true) val id: Int = 1,
        val investmentsPerLiters: Double,
        val familyIncome: Double,
        val depreciationRate: Double,
    )
