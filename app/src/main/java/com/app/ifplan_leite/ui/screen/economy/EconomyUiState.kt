package com.app.ifplan_leite.ui.screen.economy

import com.app.ifplan_leite.core.data.entities.Economy

data class EconomyUiState(
    val economyResult: Economy? = null,
    val isLoading: Boolean = false,
    val investmentsPerLiters: Double = 0.0,
    val familyIncome: Double = 0.0,
    val depreciationRate: Double = 0.0,
)
