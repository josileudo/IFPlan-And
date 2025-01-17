package com.app.ifplan_leite.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ifplan_leite.core.data.model.mock.MockSimulateItems
import com.app.ifplan_leite.core.data.state.FilteredItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnFetchAllSimulations ->  fetchSimulationByField("")
            is HomeUiEvent.OnFetchSimulationByField -> fetchSimulationByField(event.filter)
            else -> {}
        }
    }

    fun fetchSimulationByField(filter: String) {
        viewModelScope.launch {
            val allSimulations = MockSimulateItems

           val filteredSimulations =  if(filter.isBlank()) {
               allSimulations
            } else {
                allSimulations.filter { item -> item.title.contains(filter, ignoreCase = true) }
            }

            _uiState.value = _uiState.value.copy(
                simulateItems = filteredSimulations,
                filteredItems = FilteredItems(titleQuery = filter)
            )
        }
    }
}