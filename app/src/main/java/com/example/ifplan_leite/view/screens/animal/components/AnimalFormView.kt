package com.example.ifplan_leite.view.screens.animal.components

import CurrencyInputField
import CurrencyInputViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ifplan_leite.view_model.AnimalViewModel

@Composable
fun AnimalFormView(
    animalViewModel: AnimalViewModel = hiltViewModel(),
    currencyInputViewModel: CurrencyInputViewModel,
) {

    val animalState = animalViewModel.animalState.collectAsState()

    if(animalState.value.isSuccess) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            // PESO CORPORAL
            CurrencyInputField(
                label = "Peso corporal (kg)",
                onValueChange = { animalViewModel.updatePesoCorporal(it) },
                value = animalState.value.pesoCorporal
            )

            // PRODUÇÃO DE LEITE
            CurrencyInputField(
                label = "Produção de leite (L/vaca/dia)",
                value = animalState.value.milkProduction,
                onValueChange = {
                    animalViewModel.updateMilkProduction(it)
                },
            )

            // TEOR DE GORDURA
            CurrencyInputField(
                label = "Teor de gordura no leite (%)",
                value = animalState.value.milkFatContent,
                onValueChange = {
                    animalViewModel.updateMilkFatContent(it)
                },
            )

            // TEOR DE PB NO LEITE
            CurrencyInputField(
                label = "Teor de PB no leite (%)",
                value = animalState.value.pbFatMilk,
                onValueChange = {
                    animalViewModel.updatePbFatMilk(it)
                },
            )

            // DESLOCAMENTO HORIZONTAL
            CurrencyInputField(
                label = "Deslocamento horizontal (m)",
                value = animalState.value.horizontalShift,
                onValueChange = {
                    animalViewModel.updateHorizontalShift(it)
                },
            )

            // DESLOCAMENTO VERTICAL
            CurrencyInputField(
                label = "Deslocamento vertical (m)",
                value = animalState.value.verticalShift,
                onValueChange = {
                    animalViewModel.updateVerticalShift(it)
                },
            )

            // VACAS EM LACTAÇÃO
            CurrencyInputField(
                label = "Vacas em lactação (%)",
                value = animalState.value.lactatingCows,
                onValueChange = { newValue ->
                    animalViewModel.updateLactatingCows(newValue)
                    println("*** $newValue")
                    println("*** ${animalState.value.lactatingCows}")
                }
            )
        }
    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AnimalFormPreview() {
    // Mock do AnimalRepository
//    val mockAnimalRepository = AnimalRepository(object : AnimalDao {
//        override suspend fun insertAnimal(animal: AnimalData) {
//            // Mock sem funcionalidade
//        }
//
//        override suspend fun getAllAnimals(): List<AnimalData> {
//            return emptyList() // Retorna uma lista vazia ou mockada
//        }
//    })

    // Cria o AnimalViewModel com o mock do repositório
//    val mockAnimalViewModel = AnimalViewModel(animalRepository = mockAnimalRepository).apply {
//        updatePesoCorporal("10.0")
//        updateMilkProduction("20.0")
//        updateMilkFatContent("3.5")
//        updateLactatingCows("40.0")
//    }

//    AnimalFormView(animalViewModel = mockAnimalViewModel)
}