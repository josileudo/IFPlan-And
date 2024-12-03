package com.app.ifplan_leite.view.screens.weatherAndSoil.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.ifplan_leite.R
import com.app.ifplan_leite.model.FormFieldModel
import com.app.ifplan_leite.model.utils.CurrencyInputField
import com.app.ifplan_leite.view_model.WeatherAndSoilViewModel

@Composable
fun WeatherAndSoilFormView(weatherAndSoilViewModel: WeatherAndSoilViewModel = hiltViewModel()) {
    val weatherAndSoilState = weatherAndSoilViewModel.weatherAndSoilState.collectAsState()

    if (weatherAndSoilState.value.isSuccess) {
        val formItems = listOf(
            // PRECIPITAÇÃO
            FormFieldModel(
                label = stringResource(R.string.precipitation),
                value = weatherAndSoilState.value.precipitation,
                onValueChange = { weatherAndSoilViewModel.updatePrecipitation(it) },
            ),

            // TEMPERATURA MÁXIMA
            FormFieldModel(
                label = stringResource(R.string.maxTemperature),
                value = weatherAndSoilState.value.maxTemperature,
                decimalsNumber = 1,
                onValueChange = { weatherAndSoilViewModel.updateMaxTemperature(it) },
            ),

            // TEMPERATURA MÍNIMA
            FormFieldModel(
                label = stringResource(R.string.minTemperature),
                value = weatherAndSoilState.value.minTemperature,
                decimalsNumber = 1,
                onValueChange = { weatherAndSoilViewModel.updateMinTemperature(it) },
            ),

            // UMIDADE RELATIVA
            FormFieldModel(
                label = stringResource(R.string.relativeHumidity),
                value = weatherAndSoilState.value.relativeHumidity,
                decimalsNumber = 1,
                onValueChange = { weatherAndSoilViewModel.updateRelativeHumidity(it) },
            ),

            // VELOCIDADE DO VENTO
            FormFieldModel(
                label = stringResource(R.string.velocityVents),
                value = weatherAndSoilState.value.velocityVents,
                decimalsNumber = 1,
                onValueChange = { weatherAndSoilViewModel.updateVelocityVents(it) },
            ),

            // DOSE DE N
            FormFieldModel(
                label = stringResource(R.string.nDosage),
                value = weatherAndSoilState.value.nDosage,
                decimalsNumber = 1,
                onValueChange = { weatherAndSoilViewModel.updateNDosage(it) },
            ),

            // ÁGUA E OUTROS USOS
            FormFieldModel(
                label = stringResource(R.string.otherAndWater),
                value = weatherAndSoilState.value.otherAndWater,
                onValueChange = { weatherAndSoilViewModel.updateOtherAndWater(it) },
            ),

            // ÁGUA DISPONIVEL PARA IRRIGACAO (m3/dia)
            FormFieldModel(
                label = stringResource(R.string.agua_disp_p_irriga_o_m3_dia),
                value = weatherAndSoilState.value.otherAndWater,
                decimalsNumber = 2,
                onValueChange = { weatherAndSoilViewModel.updateWaterAvailableToIrrigation(it) },
            )
        )

        FieldsConfiguration(formItems)
    }
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