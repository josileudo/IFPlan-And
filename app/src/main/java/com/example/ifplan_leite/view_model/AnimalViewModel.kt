package com.example.ifplan_leite.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifplan_leite.data.entities.Animal
import com.example.ifplan_leite.data.repository.AnimalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val animalRepository: AnimalRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AnimalUiState())
    val uiState: StateFlow<AnimalUiState> = _uiState.asStateFlow()

//    val animals = animalRepository
//        .getAnimal().stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = null
//        )
    var pesoCorporal by mutableDoubleStateOf(0.0)
        private set
    var milkProduction by mutableDoubleStateOf(0.0)
        private set
    var milkFatContent by mutableDoubleStateOf(0.0)
        private set
    var pbFatMilk by mutableDoubleStateOf(0.0)
        private set
    var horizontalShift by mutableDoubleStateOf(0.0)
        private set
    var verticalShift by mutableDoubleStateOf(0.0)
        private set
    var lactatingCows by mutableDoubleStateOf(0.0)
        private set

    fun updatePesoCorporal(value: Double) { pesoCorporal = value }
    fun updateMilkProduction(value: Double) { milkProduction = value }
    fun updateMilkFatContent(value: Double) { milkFatContent = value }
    fun updatePbFatMilk(value: Double) { pbFatMilk = value}
    fun updateHorizontalShift(value: Double) { horizontalShift = value }
    fun updateVerticalShift(value: Double) { verticalShift = value }
    fun updateLactatingCows(value: Double) { lactatingCows = value }

    init {
        viewModelScope.launch {
            animalRepository.getAnimal().collect { animal ->
                println("*** animal get $animal")
            }
        }
    }

    fun saveAnimal() {
        viewModelScope.launch {
            try {
                animalRepository.saveAnimal(
                    pesoCorporal = pesoCorporal,
                    milkProduction = milkProduction,
                    milkFatContent = milkFatContent,
                    pbFatMilk = pbFatMilk,
                    horizontalShift = horizontalShift,
                    verticalShift = verticalShift,
                    lactatingCows = lactatingCows
                )

                println("Animal salvo com sucesso!")
                _uiState.update { it.copy(
                    isSuccess = true,
                    error = null
                )}
            } catch(error: Exception) {
                _uiState.update {it.copy(
                    error = "Error ao salvar dados $error.message",
                    isSuccess = false
                )}
            }
        }
    }

    data class AnimalUiState(
        val isSuccess: Boolean = false,
        val error: String? = null,
        val isSaving: Boolean = false
    )
}


