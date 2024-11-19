package com.app.ifplan_leite.data.repository

import com.app.ifplan_leite.data.dao.EconomyDao
import com.app.ifplan_leite.data.entities.Economy
import com.app.ifplan_leite.data.state.EconomyState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class EconomyRepository @Inject constructor(
    private val economyDao: EconomyDao
) {
    val _economyState = MutableStateFlow(EconomyState())
    val economyState: StateFlow<EconomyState> = _economyState.asStateFlow()
    var loadingJob: Job? = null
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        loadEconomyData()
    }

    fun getEconomy() = economyDao.getEconomy()

    fun loadEconomyData() {
        loadingJob?.cancel()
        coroutineScope.launch {
            try {
                _economyState.update { it.copy( isSaving = true) }

                // TODO: Add error string to string resources
                getEconomy()
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
        coroutineScope.launch {
            _economyState.update { it.copy( isSaving = true ) }
            try {
                val currState = _economyState.value
                with(currState) {
                    val economyValue = Economy(
                        investmentsPerLiters = investmentsPerLiters,
                        familyIncome = familyIncome,
                        depreciationRate = depreciationRate,
                    )
                    economyDao.insertOrUpdate(economyValue)
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

    suspend fun clearEconomy() = economyDao.deleteEconomy()
}
