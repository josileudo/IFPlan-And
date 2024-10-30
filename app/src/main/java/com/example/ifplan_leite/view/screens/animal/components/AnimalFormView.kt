package com.example.ifplan_leite.view.screens.animal.components

import CurrencyInputField
import CurrencyInputViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplan_leite.view_model.AnimalViewModel

@Composable
fun AnimalFormView(
    modifier: Modifier = Modifier,
    animalViewModel: AnimalViewModel,
    currencyInputViewModel: CurrencyInputViewModel,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // PESO CORPORAL
        CurrencyInputField(
            label = "Peso corporal (kg)",
            viewModel = currencyInputViewModel,
            onValueChange = {
                animalViewModel.updatePesoCorporal(it)
            },
        )

        // PRODUÇÃO DE LEITE
        CurrencyInputField(
            label = "Produção de leite (L/vaca/dia)",
            viewModel = currencyInputViewModel,
            onValueChange = {
                animalViewModel.updateMilkProduction(it)
            },
        )

        // TEOR DE GORDURA
        CurrencyInputField(
            label = "Teor de gordura no leite (%)",
            viewModel = currencyInputViewModel,
            onValueChange = {
                animalViewModel.updateMilkFatContent(it)
            },
        )

        // TEOR DE PB NO LEITE
        CurrencyInputField(
            label = "Teor de PB no leite (%)",
            viewModel = currencyInputViewModel,
            onValueChange = {
                animalViewModel.updatePbFatMilk(it)
            },
        )

        // DESLOCAMENTO HORIZONTAL
        CurrencyInputField(
            label = "Deslocamento horizontal (m)",
            viewModel = currencyInputViewModel,
            onValueChange = {
                animalViewModel.updateHorizontalShift(it)
            },
        )

        // DESLOCAMENTO VERTICAL
        CurrencyInputField(
            label = "Deslocamento vertical (m)",
            viewModel = currencyInputViewModel,
            onValueChange = {
                animalViewModel.updateVerticalShift(it)
            },
        )

        // VACAS EM LACTAÇÃO
        CurrencyInputField(
            label = "Vacas em lactação (%)",
            viewModel = currencyInputViewModel,
            onValueChange = { newValue ->
                animalViewModel.updateLactatingCows(newValue)
                println("*** $newValue")
                println("*** ${animalViewModel.lactatingCows}")
            }
        )
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