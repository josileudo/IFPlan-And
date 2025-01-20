package com.app.ifplan_leite.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ifplan_leite.core.data.entities.ResultSimulation
import com.app.ifplan_leite.core.data.entities.Simulation
import com.app.ifplan_leite.core.data.repository.SimulationRepository
import com.app.ifplan_leite.core.data.state.FilteredItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val simulationRepository: SimulationRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnFetchAllSimulations ->  fetchSimulationByField()
            is HomeUiEvent.OnFetchSimulationByField -> fetchSimulationByField(event.filter)
            is HomeUiEvent.OnSubmitSimulation -> insertSimulationItem()
            is HomeUiEvent.OnSubmitResultSimulation -> insertResultSimulation()
            else -> {}
        }
    }

    private fun insertSimulationItem() {
        val item = Simulation(
            title = "test 123",
            description = "test 123",
            creationDate = Date()
        )

        viewModelScope.launch {
            simulationRepository.insertSimulationItem(simulation = item)
        }
    }

    private fun insertResultSimulation() {
        var item = ResultSimulation(
            simulationId = 1,
            tenAguaSolo = 10.5,
            prodForragem = 20.0,
            capaSuporte = 30.5,
            taxaLotacao = 40.0,
            itu = 50.5,
            dpl = 60.0,
            pegadaHidrica = 70.5,
            prodDiaria = 80.0,
            prodLeiteDia = 90.5,
            prodLeiteAno = 100.0,
            perdReceitaEstresse = 110.5,
            coe = 120.0,
            cot = 130.5,
            receitaTotalAno = 140.0,
            mlArea = 150.5,
            trci = 160.0,
            payback = 170.5
        )

        viewModelScope.launch {
            simulationRepository.insertResultSimulation(resultSimulation = item)
        }
    }

    private fun fetchSimulationByField(filter: String = "") {
        viewModelScope.launch {
            val allSimulations = simulationRepository.getAllSimulations()

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