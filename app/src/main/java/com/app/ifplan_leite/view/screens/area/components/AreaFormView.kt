package com.app.ifplan_leite.view.screens.area.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.app.ifplan_leite.view_model.AreaViewModel

@Composable
fun AreaFormView(
    areaViewModel: AreaViewModel = hiltViewModel(),
) {
    val areaState = areaViewModel.areaState.collectAsState()

    if(areaState.value.isSuccess) {
        val formItems = listOf(
            FormFieldModel(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.area_ha),
                value = areaState.value.area,
                decimalsNumber = 1,
                onValueChange = { areaViewModel.updateArea(it) }
            ),
            FormFieldModel(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.pickets_number),
                value = areaState.value.picketsNumber,
                decimalsNumber = 1,
                onValueChange = { areaViewModel.updatePicketsNumber(it) },
            )
        )

        FieldsConfiguration(formItems)
    }
}

@Composable
fun FieldsConfiguration(formItems: List<FormFieldModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        for(item in formItems) {
            CurrencyInputField(
                modifier = Modifier.fillMaxWidth(),
                label =  item.label,
                value = item.value,
                decimalsNumber =  item.decimalsNumber,
                onValueChange = item.onValueChange,
                lastItem = formItems.last().label == item.label
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