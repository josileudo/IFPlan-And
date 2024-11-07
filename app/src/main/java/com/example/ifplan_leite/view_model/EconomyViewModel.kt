package com.example.ifplan_leite.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifplan_leite.data.repository.EconomyRepository
import com.example.ifplan_leite.data.state.EconomyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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
    private val _economyState = MutableStateFlow(EconomyState())
    val economyState: StateFlow<EconomyState> = _economyState.asStateFlow()
    var loadingJob: Job? = null

    fun updateInvestmentsPerLiters(value: Double) { _economyState.update { it.copy( investmentsPerLiters = value ) }}
    fun updateFamilyIncome(value: Double) { _economyState.update { it.copy( familyIncome = value ) } }
    fun updateDepreciationRate(value: Double) { _economyState.update { it.copy( depreciationRate = value ) } }

    init {
        loadEconomyData()
    }

    fun loadEconomyData() {
        loadingJob?.cancel()
        viewModelScope.launch {
            try {
                _economyState.update { it.copy( isSaving = true) }

                // TODO: Add error string to string resources
                economyRepository.getEconomy()
                    .catch { error ->
                        _economyState.update { it.copy(
                            error = "Error ao carregar dados $error.message",
                            isSuccess = false,
                            isSaving = false
                        ) }
                    }
                    .collect { economy ->
                        if(economy != null) {
                            _economyState.update { it.copy(
                                    investmentsPerLiters = economy.investmentsPerLiters,
                                    familyIncome = economy.familyIncome,
                                    depreciationRate = economy.depreciationRate,
                                    isSuccess = true,
                                    error = null,
                                    isSaving = false
                                )
                            }
                        } else {
                            _economyState.update { it.copy(
                                isSuccess = true,
                                isSaving = false
                            ) }
                        }
                    }
            } catch (error: Exception) {
                // TODO: Add error string to string resources
                _economyState.update { economy ->
                    economy.copy(
                        error = "Error ao carregar dados $error.message",
                        isSuccess = false,
                        isSaving = false
                    )
                }
            }
        }
    }

    fun saveEconomy() {
        viewModelScope.launch {
            _economyState.update { it.copy( isSaving = true ) }
            try {
                val currState = _economyState.value
                with(currState) {
                    economyRepository.saveEconomy(
                        investmentsPerLiters = investmentsPerLiters,
                        familyIncome = familyIncome,
                        depreciationRate = depreciationRate,
                    )
                }

                _economyState.update {
                    it.copy(
                        isSuccess = true,
                        error = null
                    )
                }
            } catch(error: Exception) {
            // TODO: Add error to string resources
                _economyState.update {it.copy(
                    error = "Error ao salvar dados $error.message",
                    isSuccess = false,
                    isSaving = false
                )}
            }
        }
    }
}


