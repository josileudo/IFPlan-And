package com.example.ifplan_leite.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.ifplan_leite.data.entities.Animal
import com.example.ifplan_leite.data.repository.AnimalRepository
import com.example.ifplan_leite.data.state.AnimalState
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
    private val _animalState = MutableStateFlow(AnimalState())
    val animalState: StateFlow<AnimalState> = _animalState.asStateFlow()

//    val animals = animalRepository
//        .getAnimal().stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = null
//        )

    fun updatePesoCorporal(value: Double) { _animalState.update { it.copy( pesoCorporal = value ) }}
    fun updateMilkProduction(value: Double) { _animalState.update { it.copy( milkProduction = value ) } }
    fun updateMilkFatContent(value: Double) { _animalState.update { it.copy( milkFatContent = value ) } }
    fun updatePbFatMilk(value: Double) { _animalState.update { it.copy( pbFatMilk = value ) } }
    fun updateHorizontalShift(value: Double) { _animalState.update { it.copy( horizontalShift = value ) } }
    fun updateVerticalShift(value: Double) { _animalState.update { it.copy( verticalShift = value ) } }
    fun updateLactatingCows(value: Double) { _animalState.update { it.copy( lactatingCows = value ) } }

    init {
        loadingAnimalData()

        if(animalState.value.isSuccess) {
            println("*** animalViewModelstate ${animalState.value}")
        }
    }

    fun loadingAnimalData() {
        viewModelScope.launch {
            try {
                animalRepository.getAnimal().collect { animal ->
                    animal?.let { latestAnimal ->

                        _animalState.update { it.copy( isSaving = true) }

                        println("*** Animal carregado com sucesso! $latestAnimal")
                        _animalState.update { curState ->
                            curState.copy(
                                pesoCorporal = latestAnimal.pesoCorporal,
                                milkProduction = latestAnimal.milkProduction,
                                milkFatContent = latestAnimal.milkFatContent,
                                pbFatMilk = latestAnimal.pbFatMilk,
                                horizontalShift = latestAnimal.horizontalShift,
                                verticalShift = latestAnimal.verticalShift,
                                lactatingCows = latestAnimal.lactatingCows,
                                isSuccess = true,
                                error = null,
                                isSaving = false
                            )
                        }
                    }
                }
            } catch (error: Exception) {
                _animalState.update { animal ->
                    animal.copy(
                        error = "Error ao carregar dados $error.message",
                        isSuccess = false
                    )
                }
            }

        }
    }

    fun saveAnimal() {
        viewModelScope.launch {
            try {
                val currState = _animalState.value
                animalRepository.saveAnimal(
                    pesoCorporal = currState.pesoCorporal,
                    milkProduction = currState.milkProduction,
                    milkFatContent = currState.milkFatContent,
                    pbFatMilk = currState.pbFatMilk,
                    horizontalShift = currState.horizontalShift,
                    verticalShift = currState.verticalShift,
                    lactatingCows = currState.lactatingCows
                )

                println("Animal salvo com sucesso!")
                _animalState.update { it.copy(
                    isSuccess = true,
                    error = null
                )}

                loadingAnimalData()
            } catch(error: Exception) {
                _animalState.update {it.copy(
                    error = "Error ao salvar dados $error.message",
                    isSuccess = false
                )}
            }
        }
    }
}


