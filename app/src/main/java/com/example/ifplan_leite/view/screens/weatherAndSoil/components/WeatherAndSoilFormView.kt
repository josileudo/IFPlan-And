package com.example.ifplan_leite.view.screens.weatherAndSoil.components

import CurrencyInputField
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
import com.example.ifplan_leite.R
import com.example.ifplan_leite.view_model.WeatherAndSoilViewModel

@Composable
fun WeatherAndSoilFormView( weatherAndSoilViewModel: WeatherAndSoilViewModel = hiltViewModel()) {
    val weatherAndSoilState = weatherAndSoilViewModel.weatherAndSoilState.collectAsState()

    if(weatherAndSoilState.value.isSuccess) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            // PRECIPITAÇÃO
            CurrencyInputField(
                label = stringResource(R.string.precipitation),
                value = weatherAndSoilState.value.precipitation,
                onValueChange = { weatherAndSoilViewModel.updatePrecipitation(it) },
            )

            // TEMPERATURA MÁXIMA
            CurrencyInputField(
                label = stringResource(R.string.maxTemperature),
                value = weatherAndSoilState.value.maxTemperature,
                onValueChange = { weatherAndSoilViewModel.updateMaxTemperature(it)   },
            )

            // TEMPERATURA MÍNIMA
            CurrencyInputField(
                label = stringResource(R.string.minTemperature),
                value = weatherAndSoilState.value.minTemperature,
                onValueChange = { weatherAndSoilViewModel.updateMinTemperature(it) },
            )

            // UMIDADE RELATIVA
            CurrencyInputField(
                label = stringResource(R.string.relativeHumidity),
                value = weatherAndSoilState.value.relativeHumidity,
                onValueChange = { weatherAndSoilViewModel.updateRelativeHumidity(it) },
            )

            // VELOCIDADE DO VENTO
            CurrencyInputField(
                label = stringResource(R.string.velocityVents),
                value = weatherAndSoilState.value.velocityVents,
                onValueChange = { weatherAndSoilViewModel.updateVelocityVents(it) },
            )

            // DOSE DE N
            CurrencyInputField(
                label = stringResource(R.string.nDosage),
                value = weatherAndSoilState.value.nDosage,
                onValueChange = { weatherAndSoilViewModel.updateNDosage(it) },
            )

            // ÁGUA E OUTROS USOS
            CurrencyInputField(
                label = stringResource(R.string.otherAndWater),
                value = weatherAndSoilState.value.otherAndWater,
                onValueChange = { weatherAndSoilViewModel.updateOtherAndWater(it) },
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