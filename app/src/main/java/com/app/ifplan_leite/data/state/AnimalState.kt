package com.app.ifplan_leite.data.state

data class AnimalState (
    val pesoCorporal: Double = 0.0,
    val milkProduction: Double = 0.0,
    val milkFatContent: Double = 0.0,
    val pbFatMilk: Double = 0.0,
    val horizontalShift: Double = 0.0,
    val verticalShift: Double = 0.0,
    val lactatingCows: Double = 0.0,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val isSaving: Boolean = false
)

