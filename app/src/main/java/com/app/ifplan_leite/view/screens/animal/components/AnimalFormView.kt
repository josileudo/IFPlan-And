package com.app.ifplan_leite.view.screens.animal.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.ifplan_leite.model.FormFieldModel
import com.app.ifplan_leite.model.utils.CurrencyInputField
import com.app.ifplan_leite.view_model.AnimalViewModel

@Composable
fun AnimalFormView( animalViewModel: AnimalViewModel = hiltViewModel()) {
    val animalState = animalViewModel.animalState.collectAsState()

    if(animalState.value.isSuccess) {
        val formItems = listOf(
            // PESO CORPORAL
            FormFieldModel(
                label = "Peso corporal (kg)",
                onValueChange = { animalViewModel.updatePesoCorporal(it) },
                value = animalState.value.pesoCorporal
            ),
            // PRODUÇÃO DE LEITE
            FormFieldModel(
                label = "Produção de leite (L/vaca/dia)",
                value = animalState.value.milkProduction,
                decimalsNumber = 1,
                onValueChange = {
                    animalViewModel.updateMilkProduction(it)
                },
            ),
            // TEOR DE GORDURA NO LEITE
            FormFieldModel(
                label = "Teor de gordura no leite (%)",
                value = animalState.value.milkFatContent,
                decimalsNumber = 1,
                onValueChange = {
                    animalViewModel.updateMilkFatContent(it)
                }
            ),
            // TEOR DE PB NO LEITE
            FormFieldModel(
                label = "Teor de PB no leite (%)",
                value = animalState.value.pbFatMilk,
                decimalsNumber = 1,
                onValueChange = {
                    animalViewModel.updatePbFatMilk(it)
                }
            ),
            // DESLOCAMENTO HORIZONTAL
            FormFieldModel(
                label = "Deslocamento horizontal (m)",
                value = animalState.value.horizontalShift,
                onValueChange = {
                    animalViewModel.updateHorizontalShift(it)
                }
            ),
            // DESLOCAMENTO VERTICAL
            FormFieldModel(
                label = "Deslocamento vertical (m)",
                value = animalState.value.verticalShift,
                onValueChange = {
                    animalViewModel.updateVerticalShift(it)
                }
            ),
            // VACAS EM LACTAÇÃO
            FormFieldModel(
                label = "Vacas em lactação (%)",
                value = animalState.value.lactatingCows,
                decimalsNumber = 1,
                onValueChange = { animalViewModel.updateLactatingCows(it)  }
            )
        )

        FieldsConfiguration(formItems)
    }
}

@Composable
private fun FieldsConfiguration( formItems: List<FormFieldModel>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for(item in formItems) {
            CurrencyInputField(
                label = item.label,
                value = item.value,
                decimalsNumber = item.decimalsNumber,
                onValueChange = item.onValueChange,
                lastItem = item.label == formItems.last().label
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