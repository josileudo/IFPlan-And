package com.example.ifplan_leite.view.screens.animal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ifplan_leite.R
import com.example.ifplan_leite.Routes
import com.example.ifplan_leite.model.TitleAndValue
import com.example.ifplan_leite.view.components.CardInfoComponent
import com.example.ifplan_leite.view_model.AnimalViewModel

@Composable
fun AnimalView(
    animalViewModel: AnimalViewModel = hiltViewModel(),
    navController: NavController?= null
){
    val animalState = animalViewModel.animalState.collectAsState().value

    val mockValues = listOf(
        TitleAndValue(stringResource(R.string.body_weight_kg), animalState.pesoCorporal.toString()),
        TitleAndValue(stringResource(R.string.milk_production_lvd),animalState.milkProduction.toString()),
        TitleAndValue("Teor de gordura no leite (%)", animalState.milkFatContent.toString()),
        TitleAndValue("Teor de PB no leite (%)", animalState.pbFatMilk.toString()),
        TitleAndValue("Deslocamento horizontal (m)", animalState.horizontalShift.toString()),
        TitleAndValue("Deslocamento Vertical (m)", animalState.verticalShift.toString()),
        TitleAndValue("Vacas em lactação (%)", animalState.lactatingCows.toString()),
    )

    CardInfoComponent(
        title = stringResource(R.string.animal),
        isLoading = animalState.isSaving,
        error = animalState.error,
        listItems = mockValues,
        onClick = {
            navController?.navigate(Routes.animalInput)
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimalViewPreview() {
    AnimalView( )
}


