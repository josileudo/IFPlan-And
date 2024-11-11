package com.example.ifplan_leite.view_model

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.ifplan_leite.data.state.AnimalState
import com.example.ifplan_leite.data.state.AreaState
import com.example.ifplan_leite.data.state.EconomyState
import com.example.ifplan_leite.data.state.WeatherAndSoilState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SimulateViewModel @Inject constructor(
    private val areaState: StateFlow<AreaState>,
    private val animalState: StateFlow<AnimalState>,
    private val economyState: StateFlow<EconomyState>,
    private val weatherAndSoilState: StateFlow<WeatherAndSoilState>
): ViewModel() {

    fun simulate() {
        Log.d("*** SimulateViewModel", areaState.value.area.toString())
    }
}