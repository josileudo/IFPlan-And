package com.app.ifplan_leite.data.state

data class SystemCostsResultEconomicState(
    val prodDiaria: Double = 0.0,
    val prodLeiteDia: Double = 0.0,
    val prodLeiteAno: Double = 0.0,
    val perdReceitaEstresse: Double = 0.0,
    val coe: Double = 0.0,
    val cot: Double = 0.0,
    val receitaTotalAno: Double = 0.0,
    val mlArea: Double = 0.0,
    val trci: Double = 0.0,
    val payback: Double = 0.0,
)
