package com.example.ifplan_leite.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ifplan_leite.model.Animal

class AnimalViewModel() : ViewModel() {
    var pesoCorporal by mutableDoubleStateOf(0.0)
        private set
    var milkProduction by mutableDoubleStateOf(0.0)
        private set
    var milkFatContent by mutableDoubleStateOf(0.0)
        private set
    var pbFatMilk by mutableDoubleStateOf(0.0)
        private set
    var horizontalShift by mutableDoubleStateOf(0.0)
        private set
    var verticalShift by mutableDoubleStateOf(0.0)
        private set
    var lactatingCows by mutableDoubleStateOf(0.0)
        private set

    fun updatePesoCorporal(value: Double) { pesoCorporal = value }
    fun updateMilkProduction(value: Double) { milkProduction = value }
    fun updateMilkFatContent(value: Double) { milkFatContent = value }
    fun updatePbFatMilk(value: Double) { pbFatMilk = value}
    fun updateHorizontalShift(value: Double) { horizontalShift = value }
    fun updateVerticalShift(value: Double) { verticalShift = value }
    fun updateLactatingCows(value: Double) { lactatingCows = value }

//    fun onEvent(event: AnimalEvent) {
//        when(event) {
//            is AnimalEvent.SaveAnimal -> {
//                val pesoCorporal = state.
//            }
//        }
//    }

    fun saveAnimal() {
        println("*** Animal -> ${Animal(
            pesoCorporal,
            milkFatContent,
            milkProduction,
            pbFatMilk,
            horizontalShift,
            verticalShift,
            lactatingCows
        )}")
//        viewModelScope.launch {
//            val animalData = AnimalData(
//                pesoCorporal = pesoCorporal,
//                milkProduction = milkProduction,
//                milkFatContent = milkFatContent,
//                pbFatMilk = pbFatMilk,
//                horizontalShift = horizontalShift,
//                verticalShift = verticalShift,
//                lactatingCows = lactatingCows
//            )
//            animalRepository.insertAnimal(animalData)
//        }
    }
}


