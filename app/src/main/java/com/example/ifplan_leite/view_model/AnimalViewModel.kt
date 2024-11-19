package com.example.ifplan_leite.view_model

import androidx.lifecycle.ViewModel
import com.example.ifplan_leite.data.repository.AnimalRepository
import com.example.ifplan_leite.data.state.AnimalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val animalRepository: AnimalRepository
) : ViewModel() {

    val animalState: StateFlow<AnimalState> = animalRepository.animalState

    fun updatePesoCorporal(value: Double) { animalRepository._animalState.update { it.copy( pesoCorporal = value ) }}
    fun updateMilkProduction(value: Double) { animalRepository._animalState.update { it.copy( milkProduction = value ) } }
    fun updateMilkFatContent(value: Double) { animalRepository._animalState.update { it.copy( milkFatContent = value ) } }
    fun updatePbFatMilk(value: Double) { animalRepository._animalState.update { it.copy( pbFatMilk = value ) } }
    fun updateHorizontalShift(value: Double) { animalRepository._animalState.update { it.copy( horizontalShift = value ) } }
    fun updateVerticalShift(value: Double) { animalRepository._animalState.update { it.copy( verticalShift = value ) } }
    fun updateLactatingCows(value: Double) { animalRepository._animalState.update { it.copy( lactatingCows = value ) } }

    init {
        loadAnimalData()
    }

    fun loadAnimalData() {
        animalRepository.loadAnimalData()
    }

    fun saveAnimal() {
       animalRepository.saveAnimal()
    }
}


