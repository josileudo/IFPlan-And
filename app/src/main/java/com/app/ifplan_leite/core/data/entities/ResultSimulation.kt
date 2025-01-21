package com.app.ifplan_leite.core.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result_simulation")
data class ResultSimulation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val simulationId: Long,
    val tenAguaSolo: Double = 0.0,
    val prodForragem: Double = 0.0,
    val capaSuporte: Double = 0.0,
    val taxaLotacao: Double = 0.0,
    val itu: Double = 0.0,
    val dpl: Double = 0.0,
    val pegadaHidrica: Double = 0.0,
    val prodDiaria: Double = 0.0,
    val prodLeiteDia: Double = 0.0,
    val prodLeiteAno: Double = 0.0,
    val perdReceitaEstresse: Double = 0.0,
    val coe: Double = 0.0,
    val cot: Double = 0.0,
    val receitaTotalAno: Double = 0.0,
    val mlArea: Double = 0.0,
    val trci: Double = 0.0,
    val payback: Double = 0.0
)
