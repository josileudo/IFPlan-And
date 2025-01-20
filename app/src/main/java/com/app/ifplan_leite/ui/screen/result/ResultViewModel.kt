package com.app.ifplan_leite.ui.screen.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ifplan_leite.core.data.repository.SimulationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val simulationRepository: SimulationRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(ResultUiState())
    var uiState: StateFlow<ResultUiState> = _uiState.asStateFlow()

    fun onEvent(event: ResultUiEvent) {
        when(event) {
            is ResultUiEvent.OnFetchResultSimulationById -> onFetchResultSimulationById(event.id)
            else -> {}
        }
    }

    private fun onFetchResultSimulationById(id: Long) {
        println("*** onFetchResultSimulationById id = $id")
        viewModelScope.launch {
            val result = simulationRepository.getResultSimulationById(id)
            println("*** result = ${result}")
            _uiState.value = _uiState.value.copy(
               resultSimulationById = result
            )
        }
    }
}