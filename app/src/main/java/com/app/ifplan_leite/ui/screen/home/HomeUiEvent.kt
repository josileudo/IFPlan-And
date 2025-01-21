package com.app.ifplan_leite.ui.screen.home

sealed class HomeUiEvent {
    data object OnFetchAllSimulations : HomeUiEvent()
    data class OnFetchSimulationByField(var filter: String) : HomeUiEvent()
    data object OnSubmitSimulation : HomeUiEvent()
    data object OnSubmitResultSimulation : HomeUiEvent()
}