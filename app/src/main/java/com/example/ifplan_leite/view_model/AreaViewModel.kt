package com.example.ifplan_leite.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifplan_leite.data.repository.AreaRepository
import com.example.ifplan_leite.data.state.AreaState
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
class AreaViewModel @Inject constructor(
    private val areaRepository: AreaRepository
) : ViewModel() {
    private val _areaState = MutableStateFlow(AreaState())
    val areaState: StateFlow<AreaState> = _areaState.asStateFlow()
    var loadingJob: Job? = null

    fun updateArea(value: Double) { _areaState.update { it.copy( area = value ) }}
    fun updatePicketsNumber(value: Double) { _areaState.update { it.copy( picketsNumber = value ) } }

    init {
        loadAreaData()
    }

    fun loadAreaData() {
        loadingJob?.cancel()
        viewModelScope.launch {
            try {
                _areaState.update { it.copy( isSaving = true) }

                areaRepository.getArea()
                    .catch { error ->
                        _areaState.update { it.copy(
                            error = "Error ao carregar dados $error.message",
                            isSuccess = false,
                            isSaving = false
                        ) }
                    }
                    .collect { area ->
                        if(area != null) {
                            _areaState.update { it.copy(
                                    area = area.area,
                                    picketsNumber = area.picketsNumber,
                                    isSuccess = true,
                                    error = null,
                                    isSaving = false
                                )
                            }
                        } else {
                            _areaState.update { it.copy(
                                isSuccess = true,
                                isSaving = false
                            ) }
                        }
                    }
            } catch (error: Exception) {
                _areaState.update { area ->
                    area.copy(
                        error = "Error ao carregar dados $error.message",
                        isSuccess = false,
                        isSaving = false
                    )
                }
            }
        }
    }

    fun saveArea() {
        viewModelScope.launch {
            _areaState.update { it.copy( isSaving = true ) }
            try {
                val currState = _areaState.value
                with(currState) {
                    areaRepository.saveArea(
                        area = currState.area,
                        picketsNumber = currState.picketsNumber,
                    )
                }

                _areaState.update {
                    it.copy(
                        isSuccess = true,
                        error = null
                    )
                }
            } catch(error: Exception) {
                _areaState.update {it.copy(
                    error = "Error ao salvar dados $error.message",
                    isSuccess = false,
                    isSaving = false
                )}
            }
        }
    }
}


