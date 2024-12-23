package com.app.ifplan_leite.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animal_database")
data class Animal(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    val pesoCorporal: Double,
    val milkProduction: Double,
    val milkFatContent: Double,
    val pbFatMilk: Double,
    val horizontalShift: Double,
    val verticalShift: Double,
    val lactatingCows: Double
)
