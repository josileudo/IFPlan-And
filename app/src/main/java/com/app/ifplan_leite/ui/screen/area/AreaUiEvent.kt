package com.app.ifplan_leite.ui.screen.area

sealed class AreaUiEvent {
    data object OnFetchAreaResult : AreaUiEvent()
    data object OnSaveArea : AreaUiEvent()
    data class OnFetchAreaById(var id: Long) : AreaUiEvent()
    data class OnUpdateAreaFields(var field: String, var value: Double) : AreaUiEvent()
}