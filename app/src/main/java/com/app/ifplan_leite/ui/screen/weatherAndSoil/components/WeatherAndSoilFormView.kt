package com.app.ifplan_leite.ui.screen.weatherAndSoil.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ifplan_leite.R
import com.app.ifplan_leite.core.data.model.FormFieldModel
import com.app.ifplan_leite.core.data.model.utils.CurrencyInputField

@Composable
fun WeatherAndSoilFormView(
    precipitation: Double ,
    maxTemperature: Double,
    minTemperature: Double,
    relativeHumidity: Double,
    velocityVents: Double,
    nDosage: Double,
    otherAndWater: Double,
    waterAvailableToIrrigation: Double,
    onFieldChange: (String, Double) -> Unit = { s: String, d:Double -> }
) {
//    if (isSuccess) {
    val formItems = listOf(
        // PRECIPITAÇÃO
        FormFieldModel(
            label = stringResource(R.string.precipitation),
            value = precipitation,
            onValueChange = { onFieldChange("precipitation", it) },
        ),

        // TEMPERATURA MÁXIMA
        FormFieldModel(
            label = stringResource(R.string.maxTemperature),
            value = maxTemperature,
            decimalsNumber = 1,
            onValueChange = { onFieldChange("maxTemperature", it) },
        ),

        // TEMPERATURA MÍNIMA
        FormFieldModel(
            label = stringResource(R.string.minTemperature),
            value = minTemperature,
            decimalsNumber = 1,
            onValueChange = { onFieldChange("minTemperature", it) },
        ),

        // UMIDADE RELATIVA
        FormFieldModel(
            label = stringResource(R.string.relativeHumidity),
            value = relativeHumidity,
            decimalsNumber = 1,
            onValueChange = { onFieldChange("relativeHumidity", it) },
        ),

        // VELOCIDADE DO VENTO
        FormFieldModel(
            label = stringResource(R.string.velocityVents),
            value = velocityVents,
            decimalsNumber = 1,
            onValueChange = { onFieldChange("VelocityVents", it) },
        ),

        // DOSE DE N
        FormFieldModel(
            label = stringResource(R.string.nDosage),
            value = nDosage,
            decimalsNumber = 1,
            onValueChange = { onFieldChange("nDosage", it) },
        ),

        // ÁGUA E OUTROS USOS
        FormFieldModel(
            label = stringResource(R.string.otherAndWater),
            value = otherAndWater,
            onValueChange = { onFieldChange("otherAndWater", it) },
        ),

        // ÁGUA DISPONIVEL PARA IRRIGACAO (m3/dia)
        FormFieldModel(
            label = stringResource(R.string.agua_disp_p_irriga_o_m3_dia),
            value = waterAvailableToIrrigation,
            decimalsNumber = 2,
            onValueChange = { onFieldChange("waterAvailableToIrrigation", it) },
        )
    )

    FieldsConfiguration(formItems)
//    }
}

@Composable
private fun FieldsConfiguration(formItems: List<FormFieldModel>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        for (item in formItems) {
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