package com.example.ifplan_leite.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ifplan_leite.data.repository.AreaRepository
import com.example.ifplan_leite.data.state.AreaState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
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
    val areaState: StateFlow<AreaState> = areaRepository.areaState

    fun updateArea(value: Double) { areaRepository._areaState.update { it.copy( area = value ) }}
    fun updatePicketsNumber(value: Double) { areaRepository._areaState.update { it.copy( picketsNumber = value ) } }

    init {
        loadAreaData()
    }

     fun loadAreaData() {
        areaRepository.loadAreaData()
    }

     fun saveArea() {
        areaRepository.saveArea()
    }
}


