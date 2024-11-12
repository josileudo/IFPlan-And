package com.example.ifplan_leite.view.screens.soilWaterPlantAnimal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.IFPlanLeiteTheme
import com.example.ifplan_leite.R
import com.example.ifplan_leite.model.TitleAndValue
import com.example.ifplan_leite.model.utils.formatterCurrency
import com.example.ifplan_leite.view.components.CardInfoComponent
import com.example.ifplan_leite.view_model.SimulateViewModel

@Composable
fun SoilWaterPlantAnimalView(
    simulateViewModel: SimulateViewModel = hiltViewModel()
){
    val state = simulateViewModel.soilWaterPlantAnimalState.collectAsState().value
    val items = listOf(
        TitleAndValue("Tensão da água no solo (bar)", formatterCurrency(state.tenAguaSolo)),
        TitleAndValue("Produção de forragem (kg MV/m2)",formatterCurrency(state.prodForragem)),
        TitleAndValue("Capacidade de suporte (animais)", formatterCurrency(state.capaSuporte, 1)),
        TitleAndValue("Taxa de lotação (vacas/ha)", formatterCurrency(state.taxaLotacao, 1)),
        TitleAndValue("ITU", formatterCurrency(state.itu, 1)),
        TitleAndValue("DPL (L/vaca/dia)", formatterCurrency(state.dpl, 1)),
        TitleAndValue("Pegada hídrica (L H2O/L leite)", formatterCurrency(state.pegadaHidrica, 0)),
    )

    CardInfoComponent(
        title = stringResource(R.string.soil_water_plants_animal),
        listItems = items,
        showButton = false
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SoilWaterPlantAnimalViewViewPreview() {
    IFPlanLeiteTheme {
        SoilWaterPlantAnimalView()
    }
}


