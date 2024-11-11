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
     val economyState: StateFlow<EconomyState> = economyRepository.economyState

    fun updateInvestmentsPerLiters(value: Double) { economyRepository._economyState.update { it.copy( investmentsPerLiters = value ) }}
    fun updateFamilyIncome(value: Double) { economyRepository._economyState.update { it.copy( familyIncome = value ) } }
    fun updateDepreciationRate(value: Double) { economyRepository._economyState.update { it.copy( depreciationRate = value ) } }

    init {
        loadEconomyData()
    }

    fun loadEconomyData() {
        economyRepository.loadEconomyData()
    }

    fun saveEconomy() {
        economyRepository.saveEconomy()
    }
}


