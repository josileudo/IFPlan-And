package com.app.ifplan_leite.data.state

data class SoilWaterPlantAnimalState(
    val tenAguaSolo: Double = 0.0,
    val prodForragem: Double = 0.0,
    val capaSuporte: Double = 0.0,
    val taxaLotacao: Double = 0.0,
    val itu: Double = 0.0,
    val dpl: Double = 0.0,
    val pegadaHidrica: Double = 0.0,
)
