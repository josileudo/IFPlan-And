package com.example.ifplan_leite.view.screens.animal

import CurrencyInputViewModel
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ifplan_leite.view.components.FormInputValuesComponent
import com.example.ifplan_leite.view.screens.animal.components.AnimalFormView
import com.example.ifplan_leite.view_model.AnimalViewModel

@Composable
fun AnimalFormScreen(
    modifier: Modifier = Modifier,
    animalViewModel: AnimalViewModel = hiltViewModel()
) {
    Box(
        modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ){
        FormInputValuesComponent(
            formTitle = "Animal",
            onSaveClick = {
                animalViewModel.saveAnimal()
            }
        ) {
            AnimalFormView(
                animalViewModel = animalViewModel,
                currencyInputViewModel = CurrencyInputViewModel()
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AnimalFormScreenPreview() {
    AnimalFormScreen()
}
