package com.app.ifplan_leite.ui.screen.economy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ifplan_leite.core.data.entities.Economy
import com.app.ifplan_leite.core.data.repository.EconomyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EconomyViewModel @Inject constructor(
    private val economyRepository: EconomyRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(EconomyUiState())
    var uiState: StateFlow<EconomyUiState> = _uiState.asStateFlow()

    fun onEvent(event: EconomyUiEvent) {
        when (event) {
            is EconomyUiEvent.OnFetchEconomyResult -> onFetchEconomyResult()
            is EconomyUiEvent.OnFetchEconomyById -> onFetchEconomyById(event.id)
            is EconomyUiEvent.OnUpdateEconomyFields -> onUpdateEconomyFields(
                field = event.field,
                value = event.value
            )

            is EconomyUiEvent.OnSaveEconomy -> onSaveEconomy()
            else -> {}
        }
    }

    private fun onUpdateEconomyFields(field: String, value: Double) {
        _uiState.update {
            when (field) {
                "investmentsPerLiters" -> it.copy(investmentsPerLiters = value)
                "familyIncome" -> it.copy(familyIncome = value)
                "depreciationRate" -> it.copy(depreciationRate = value)
                else -> it
            }
        }
    }

    private fun onFetchEconomyResult() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            economyRepository.getEconomy()
                .catch { error ->
                    _uiState.update { it.copy(isLoading = false) }
                }
                .collect { economy ->
                    if (economy != null) {
                        _uiState.value = _uiState.value.copy(
                            economyResult = economy,
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun onSaveEconomy() {
        viewModelScope.launch {
            try {
                val economyResult = Economy(
                    familyIncome = _uiState.value.familyIncome,
                    investmentsPerLiters = _uiState.value.investmentsPerLiters,
                    depreciationRate = _uiState.value.depreciationRate
                )
                _uiState.value = _uiState.value.copy(
                    economyResult = economyResult
                )
//                 economyRepository.save Economy( economyResult)
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    private fun onFetchEconomyById(economyId: Long) {

    }
}
