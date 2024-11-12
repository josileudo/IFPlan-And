package com.example.ifplan_leite.view.screens.economy.components

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
import com.example.ifplan_leite.model.utils.CurrencyInputField
import com.example.ifplan_leite.view_model.EconomyViewModel

@Composable
fun EconomyFormView( economyViewModel: EconomyViewModel = hiltViewModel()) {
    val economyState = economyViewModel.economyState.collectAsState()

    if(economyState.value.isSuccess) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            // INVESTIMENTOS POR LITROS
            CurrencyInputField(
                label = stringResource(R.string.investment_per_liters),
                onValueChange = { economyViewModel.updateInvestmentsPerLiters(it) },
                value = economyState.value.investmentsPerLiters,
            )

            // RENDA FAMILIAR
            CurrencyInputField(
                label = stringResource(R.string.family_income),
                value = economyState.value.familyIncome,
                onValueChange = {
                    economyViewModel.updateFamilyIncome(it)
                },
            )

            // TAXA DE DEPRECIAÇÃO
            CurrencyInputField(
                label = stringResource(R.string.depreciation_rate),
                value = economyState.value.depreciationRate,
                onValueChange = {
                    economyViewModel.updateDepreciationRate(it)
                },
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