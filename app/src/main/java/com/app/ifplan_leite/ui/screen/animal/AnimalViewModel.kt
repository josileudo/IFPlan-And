package com.app.ifplan_leite.ui.screen.animal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ifplan_leite.core.data.entities.Animal
import com.app.ifplan_leite.core.data.repository.AnimalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val animalRepository: AnimalRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(AnimalUiState())
    var uiState: StateFlow<AnimalUiState> = _uiState.asStateFlow()

    fun onEvent(event: AnimalUiEvent) {
        when(event) {
            is AnimalUiEvent.OnFetchAnimalResult -> onFetchAnimalResult()
            is AnimalUiEvent.OnFetchAnimalById -> onFetchAnimalById(event.id)
            is AnimalUiEvent.OnUpdateAnimalFields -> onUpdateAnimalFields(field = event.field, value = event.value)
            is AnimalUiEvent.OnSaveAnimal -> onSaveAnimal()
            else -> {}
        }
    }

    private fun onUpdateAnimalFields( field: String,  value: Double) {
        _uiState.update {
            when(field) {
                "pesoCorporal" -> it.copy(pesoCorporal = value)
                "milkProduction" -> it.copy(milkProduction = value)
                "milkFatContent" -> it.copy(milkFatContent = value)
                "pbFatMilk" -> it.copy(pbFatMilk = value)
                "horizontalShift" -> it.copy(horizontalShift = value)
                "verticalShift" -> it.copy(verticalShift = value)
                "lactatingCows" -> it.copy(lactatingCows = value)
                else -> it
            }
        }
    }

    private fun onFetchAnimalById(animalId: Long) {

    }

    private fun onFetchAnimalResult() {
        viewModelScope.launch {
            _uiState.update { it.copy( isLoading = true) }

            animalRepository.getAnimal()
                .catch { error ->
                    _uiState.update { it.copy( isLoading = false ) }
                }
                .collect { animal ->
                    if(animal != null) {
                        _uiState.value = _uiState.value.copy(
                            animalResult = animal,
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun onSaveAnimal() {
        viewModelScope.launch {
            try {
                val animalResult =
                    Animal(
                        pesoCorporal = uiState.value.pesoCorporal,
                        milkProduction = uiState.value.milkProduction,
                        milkFatContent = uiState.value.milkFatContent,
                        pbFatMilk = uiState.value.pbFatMilk,
                        horizontalShift = uiState.value.horizontalShift,
                        verticalShift = uiState.value.verticalShift,
                        lactatingCows = uiState.value.lactatingCows
                    )
                _uiState.value = _uiState.value.copy(
                    animalResult = animalResult
                )
//                animalRepository.saveAnimal(animalResult)
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}


