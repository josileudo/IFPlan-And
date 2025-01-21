package com.app.ifplan_leite.ui.screen.area

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ifplan_leite.core.data.entities.Area
import com.app.ifplan_leite.core.data.repository.AreaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private var _uiState = MutableStateFlow(AreaUiState())
    var uiState: StateFlow<AreaUiState> = _uiState.asStateFlow()

    fun onEvent(event: AreaUiEvent) {
        when (event) {
            is AreaUiEvent.OnFetchAreaResult -> onFetchAreaResult()
            is AreaUiEvent.OnFetchAreaById -> onFetchAreaById(event.id)
            is AreaUiEvent.OnUpdateAreaFields -> onUpdateAreaFields(
                field = event.field,
                value = event.value
            )
            is AreaUiEvent.OnSaveArea -> onSaveArea()
            else -> {}
        }
    }

    private fun onUpdateAreaFields(field: String, value: Double) {
        _uiState.update {
            when (field) {
                "area" -> it.copy(area = value)
                "picketsNumber" -> it.copy(picketsNumber = value)
                else -> it
            }
        }
    }

    private fun onFetchAreaResult() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            areaRepository.getArea()
                .catch { error ->
                    _uiState.update { it.copy(isLoading = false) }
                }
                .collect { area ->
                    if (area != null) {
                        _uiState.value = _uiState.value.copy(
                            areaResult = area,
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun onSaveArea() {
        viewModelScope.launch {
            try {
                val areaResult =
                    Area(area = uiState.value.area, picketsNumber = uiState.value.picketsNumber)

                _uiState.value = _uiState.value.copy(
                    areaResult = areaResult
                )
                println("***" + uiState.value)
//                areaRepository.saveArea(areaResult)
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    private fun onFetchAreaById(areaId: Long) {

    }
}


