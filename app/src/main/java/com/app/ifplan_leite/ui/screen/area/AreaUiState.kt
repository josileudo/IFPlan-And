package com.app.ifplan_leite.ui.screen.area

import com.app.ifplan_leite.core.data.entities.Area

data class AreaUiState(
    val areaResult: Area? = null,
    val isLoading: Boolean = false,
    val area: Double = 0.0,
    val picketsNumber: Double = 0.0,
)
