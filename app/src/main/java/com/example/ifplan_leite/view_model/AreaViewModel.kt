package com.example.ifplan_leite.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifplan_leite.data.repository.AreaRepository
import com.example.ifplan_leite.data.state.AreaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaViewModel @Inject constructor(
    private val areaRepository: AreaRepository
) : ViewModel() {
    private val _areaState = MutableStateFlow(AreaState())
    val areaState: StateFlow<AreaState> = _areaState.asStateFlow()

    fun updateArea(value: Double) { _areaState.update { it.copy( area = value ) }}
    fun updatePicketsNumber(value: Double) { _areaState.update { it.copy( picketsNumber = value ) } }

    init {
        loadingAreaData()
    }

    fun loadingAreaData() {
        viewModelScope.launch {
            try {
                areaRepository.getArea().collect { area ->
                    area?.let { latestAnimal ->

                        _areaState.update { it.copy( isSaving = true) }
                        _areaState.update { curState ->
                            curState.copy(
                                area = latestAnimal.area,
                                picketsNumber = latestAnimal.picketsNumber,
                                isSuccess = true,
                                error = null,
                                isSaving = false
                            )
                        }
                    }
                }
            } catch (error: Exception) {
                _areaState.update { area ->
                    area.copy(
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
                val currState = _areaState.value
                areaRepository.saveArea(
                    area = currState.area,
                    picketsNumber = currState.picketsNumber,
                )

                println("Area salvo com sucesso!")
                _areaState.update { it.copy(
                    isSuccess = true,
                    error = null
                )}

                loadingAreaData()
            } catch(error: Exception) {
                _areaState.update {it.copy(
                    error = "Error ao salvar dados $error.message",
                    isSuccess = false
                )}
            }
        }
    }
}


