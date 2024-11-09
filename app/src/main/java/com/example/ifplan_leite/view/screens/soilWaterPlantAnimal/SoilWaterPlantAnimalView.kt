package com.example.ifplan_leite.view.screens.soilWaterPlantAnimal

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ifplan_leite.R
import com.example.ifplan_leite.model.TitleAndValue
import com.example.ifplan_leite.ui.theme.IFPlanLeiteTheme
import com.example.ifplan_leite.view.components.CardInfoComponent

@Composable
fun SoilWaterPlantAnimalView(){
//    val areaState = areaViewModel.areaState.collectAsState().value
    val items = listOf(
        TitleAndValue("Tensão da água no solo (bar)", "0"),
        TitleAndValue("Produção de forragem (kg MV/m2)", "0"),
        TitleAndValue("Capacidade de suporte (animais)", "0"),
        TitleAndValue("Taxa de lotação (vacas/ha)", "0"),
        TitleAndValue("ITU", "0"),
        TitleAndValue("DPL (L/vaca/dia)", "0"),
        TitleAndValue("Pegada hídrica (L H2O/L leite)", "0"),
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


