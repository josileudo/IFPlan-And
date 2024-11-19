package com.app.ifplan_leite.data.state

data class AreaState (
    val area: Double = 0.0,
    val picketsNumber: Double = 0.0,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val isSaving: Boolean = false
)

