package com.example.ifplan_leite.view_model

import android.util.Log
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
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
    var loadingJob: Job? = null

    fun updatePesoCorporal(value: Double) { _animalState.update { it.copy( pesoCorporal = value ) }}
    fun updateMilkProduction(value: Double) { _animalState.update { it.copy( milkProduction = value ) } }
    fun updateMilkFatContent(value: Double) { _animalState.update { it.copy( milkFatContent = value ) } }
    fun updatePbFatMilk(value: Double) { _animalState.update { it.copy( pbFatMilk = value ) } }
    fun updateHorizontalShift(value: Double) { _animalState.update { it.copy( horizontalShift = value ) } }
    fun updateVerticalShift(value: Double) { _animalState.update { it.copy( verticalShift = value ) } }
    fun updateLactatingCows(value: Double) { _animalState.update { it.copy( lactatingCows = value ) } }

    init {
        loadAnimalData()
    }

    fun loadAnimalData() {
        loadingJob?.cancel() // Cancela job anterior se existir
        loadingJob = viewModelScope.launch {
            try {
                Log.d("*** AnimalViewModel", "Starting to load animal data")
                _animalState.update { it.copy(isSaving = true) }

                animalRepository.getAnimal()
                    .catch { error ->
                        Log.e("*** AnimalViewModel", "Error collecting animal data", error)
                        _animalState.update {
                            it.copy(
                                error = "*** Erro ao carregar dados: ${error.message}",
                                isSuccess = false,
                                isSaving = false
                            )
                        }
                    }
                    .collect { animal ->
                        Log.d("AnimalViewModel", "Received animal data: $animal")
                        if (animal != null) {
                            _animalState.update {
                                it.copy(
                                    pesoCorporal = animal.pesoCorporal,
                                    milkProduction = animal.milkProduction,
                                    milkFatContent = animal.milkFatContent,
                                    pbFatMilk = animal.pbFatMilk,
                                    horizontalShift = animal.horizontalShift,
                                    verticalShift = animal.verticalShift,
                                    lactatingCows = animal.lactatingCows,
                                    isSuccess = true,
                                    error = null,
                                    isSaving = false
                                )
                            }
                        } else {
                            // Se não há dados, inicializamos com valores padrão
                            _animalState.update {
                                it.copy(
                                    isSuccess = true,
                                    isSaving = false
                                )
                            }
                        }
                    }
            } catch (e: Exception) {
                Log.e("*** AnimalViewModel", "Error in loadAnimalData", e)
                _animalState.update {
                    it.copy(
                        error = "Erro inesperado: ${e.message}",
                        isSuccess = false,
                        isSaving = false
                    )
                }
            }
        }
    }


    fun saveAnimal() {
        viewModelScope.launch {
            try {
                _animalState.update { it.copy(isSaving = true) }

                with(_animalState.value) {
                    animalRepository.saveAnimal(
                        pesoCorporal = pesoCorporal,
                        milkProduction = milkProduction,
                        milkFatContent = milkFatContent,
                        pbFatMilk = pbFatMilk,
                        horizontalShift = horizontalShift,
                        verticalShift = verticalShift,
                        lactatingCows = lactatingCows
                    )
                }

                _animalState.update {
                    it.copy(
                        isSuccess = true,
                        isSaving = false
                    )
                }
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Error saving animal", e)
                _animalState.update {
                    it.copy(
                        error = "Erro ao salvar: ${e.message}",
                        isSuccess = false,
                        isSaving = false
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        loadingJob?.cancel()
    }
}


