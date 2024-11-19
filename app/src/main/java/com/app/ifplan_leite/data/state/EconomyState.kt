package com.app.ifplan_leite.data.state

data class EconomyState (
    val investmentsPerLiters: Double = 0.0,
    val familyIncome: Double = 0.0,
    val depreciationRate: Double = 0.0,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val isSaving: Boolean = false
)

