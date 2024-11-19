package com.example.ifplan_leite.view_model

import androidx.lifecycle.ViewModel
import com.example.ifplan_leite.data.repository.EconomyRepository
import com.example.ifplan_leite.data.state.EconomyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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


