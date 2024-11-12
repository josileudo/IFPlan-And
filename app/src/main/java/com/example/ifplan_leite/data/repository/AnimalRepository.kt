package com.example.ifplan_leite.data.repository

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.ifplan_leite.data.dao.AnimalDao
import com.example.ifplan_leite.data.entities.Animal
import com.example.ifplan_leite.data.state.AnimalState
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

class AnimalRepository @Inject constructor(
    private val animalDao: AnimalDao
) {
    val _animalState = MutableStateFlow(AnimalState())
    val animalState: StateFlow<AnimalState> = _animalState.asStateFlow()
    var loadingJob: Job? = null
    var coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        loadAnimalData()
    }

    private fun getAnimal() = animalDao.getAnimal()

    fun loadAnimalData() {
        loadingJob?.cancel()
        coroutineScope.launch {
            try {
                _animalState.update { it.copy(isSaving = true) }

                getAnimal()
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
                            _animalState.update {
                                it.copy(
                                    isSuccess = true,
                                    isSaving = false
                                )
                            }
                        }
                    }
            } catch (e: Exception) {
                _animalState.update {
                    it.copy(
                        error = "*** Erro inesperado: ${e.message}",
                        isSuccess = false,
                        isSaving = false
                    )
                }
            }
        }
    }

    fun saveAnimal() {
        coroutineScope.launch {
            try {
                _animalState.update { it.copy(isSaving = true) }

                with(_animalState.value) {
                   val animal = Animal(
                        pesoCorporal = pesoCorporal,
                        milkProduction = milkProduction,
                        milkFatContent = milkFatContent,
                        pbFatMilk = pbFatMilk,
                        horizontalShift = horizontalShift,
                        verticalShift = verticalShift,
                        lactatingCows = lactatingCows
                    )
                    animalDao.insertOrUpdate(animal)
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

    suspend fun clearAnimal() = animalDao.deleteAnimal()
}
