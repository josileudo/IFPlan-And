package com.app.ifplan_leite.ui.screen.economy

sealed class EconomyUiEvent {
    data object OnFetchEconomyResult : EconomyUiEvent()
    data object OnSaveEconomy : EconomyUiEvent()
    data class OnFetchEconomyById(var id: Long) : EconomyUiEvent()
    data class OnUpdateEconomyFields(var field: String, var value: Double) : EconomyUiEvent()
}