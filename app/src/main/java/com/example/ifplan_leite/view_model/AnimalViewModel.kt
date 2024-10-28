package com.example.ifplan_leite.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ifplan_leite.model.Animal
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

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

    private fun cleanInputToDouble(input: String): Double {
        println("*** cleanInputTe ${ input}")
        println("*** cleanInputTe formatted ${ input.replace("[,.\u00A0]".toRegex(), "").toDoubleOrNull() ?: 0.0}")
        // Remove símbolos de moeda, espaços e separadores de milhar
        val cleanInput = input.replace("[R$ ,\\u00A0]".toRegex(), "")
        val number = cleanInput.toDoubleOrNull() ?: return 0.0
        return number / 100
    }

    fun formatCurrency(value: Double): String {
        return NumberFormat.getNumberInstance(Locale("pt", "BR")).format(value)
    }

    fun cleanDoubleInput(value: Double): String {
        // Converte o Double para String sem notação científica
        val numberString = value.toBigDecimal().toPlainString()

        // Remove separadores de milhar, vírgulas, pontos e espaços (mantém apenas os números inteiros)
        return numberString.replace("[.,\\u00A0]".toRegex(), "")
    }

    fun updatePesoCorporal(value: Double) { pesoCorporal = value }
    fun updateMilkProduction(value: Double) { milkProduction = value }
    fun updateMilkFatContent(value: Double) { milkFatContent = value }
    fun updatePbFatMilk(value: Double) { pbFatMilk = value}
    fun updateHorizontalShift(value: Double) { horizontalShift = value }
    fun updateVerticalShift(value: Double) { verticalShift = value }
    fun updateLactatingCows(value: Double) { lactatingCows = value }

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