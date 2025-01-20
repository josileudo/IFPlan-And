package com.app.ifplan_leite.ui.screen.soilWaterPlantAnimal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.compose.IFPlanLeiteTheme
import com.app.ifplan_leite.R
import com.app.ifplan_leite.core.data.entities.ResultSimulation
import com.app.ifplan_leite.core.data.model.TitleAndValue
import com.app.ifplan_leite.core.data.model.utils.formatterCurrency
import com.app.ifplan_leite.ui.components.card.IfPlanCardInfoResultContainer
import com.app.ifplan_leite.view.SimulateViewModel

@Composable
fun SoilWaterPlantAnimalView(
    resultSimulation: ResultSimulation? = null
){
    val items =
        resultSimulation?.let {
            listOf(
                TitleAndValue("Tensão da água no solo (bar)", formatterCurrency(it.tenAguaSolo)),
                TitleAndValue(
                    "Produção de forragem (kg MV/m2)",
                    formatterCurrency(it.prodForragem)
                ),
                TitleAndValue(
                    "Capacidade de suporte (animais)",
                    formatterCurrency(it.capaSuporte, 1)
                ),
                TitleAndValue("Taxa de lotação (vacas/ha)", formatterCurrency(it.taxaLotacao, 1)),
                TitleAndValue("ITU", formatterCurrency(it.itu, 1)),
                TitleAndValue("DPL (L/vaca/dia)", formatterCurrency(it.dpl, 1)),
                TitleAndValue(
                    "Pegada hídrica (L H2O/L leite)",
                    formatterCurrency(it.pegadaHidrica, 2)
                ),
            )
        }

    items?.let {
        IfPlanCardInfoResultContainer(
            title = stringResource(R.string.soil_water_plants_animal),
            listItems = it,
            showButton = false
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SoilWaterPlantAnimalViewViewPreview() {
    IFPlanLeiteTheme {
        SoilWaterPlantAnimalView()
    }
}


