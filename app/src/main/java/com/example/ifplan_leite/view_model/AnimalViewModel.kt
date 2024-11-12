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


