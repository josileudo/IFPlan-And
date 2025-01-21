package com.app.ifplan_leite.ui.screen.result

sealed class ResultUiEvent {
    data class OnFetchResultSimulationById(var id: Long) : ResultUiEvent()
}