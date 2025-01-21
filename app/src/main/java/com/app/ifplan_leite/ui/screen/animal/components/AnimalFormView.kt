package com.app.ifplan_leite.ui.screen.animal.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ifplan_leite.core.data.model.FormFieldModel
import com.app.ifplan_leite.core.data.model.utils.CurrencyInputField

@Composable
fun AnimalFormView(
    pesoCorporal: Double,
    milkProduction: Double,
    milkFatContent: Double,
    pbFatMilk: Double,
    horizontalShift: Double,
    verticalShift: Double,
    lactatingCows: Double,
    onFieldChange: (field: String, value: Double) -> Unit = { s: String, d: Double -> }
) {
//    if(animalState.value.isSuccess) {
        val formItems = listOf(
            // PESO CORPORAL
            FormFieldModel(
                label = "Peso corporal (kg)",
                onValueChange = { onFieldChange("pesoCorporal", it) },
                decimalsNumber = 2,
                value = pesoCorporal
            ),
            // PRODUÇÃO DE LEITE
            FormFieldModel(
                label = "Produção de leite (L/vaca/dia)",
                value = milkProduction,
                decimalsNumber = 1,
                onValueChange =
                    { onFieldChange("milkProduction", it) },
            ),
            // TEOR DE GORDURA NO LEITE
            FormFieldModel(
                label = "Teor de gordura no leite (%)",
                value = milkFatContent,
                decimalsNumber = 1,
                onValueChange = {
                    onFieldChange("milkFatContent", it)
                }
            ),
            // TEOR DE PB NO LEITE
            FormFieldModel(
                label = "Teor de PB no leite (%)",
                value = pbFatMilk,
                decimalsNumber = 1,
                onValueChange = {
                    onFieldChange("pbFatMilk", it)
                }
            ),
            // DESLOCAMENTO HORIZONTAL
            FormFieldModel(
                label = "Deslocamento horizontal (m)",
                value = horizontalShift,
                onValueChange = {
                    onFieldChange("horizontalShift", it)
                }
            ),
            // DESLOCAMENTO VERTICAL
            FormFieldModel(
                label = "Deslocamento vertical (m)",
                value =  verticalShift,
                onValueChange = {
                    onFieldChange("verticalShift", it)
                }
            ),
            // VACAS EM LACTAÇÃO
            FormFieldModel(
                label = "Vacas em lactação (%)",
                value =  lactatingCows,
                decimalsNumber = 1,
                onValueChange = { onFieldChange("lactatingCows", it)  }
            )
        )

        FieldsConfiguration(formItems)
//    }
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