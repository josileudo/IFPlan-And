package com.app.ifplan_leite.view

import androidx.lifecycle.ViewModel
import com.app.ifplan_leite.core.data.repository.AreaRepository
import com.app.ifplan_leite.core.data.state.AreaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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


