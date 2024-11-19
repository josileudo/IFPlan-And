package com.app.ifplan_leite.view.screens.systemsCostsResultEconomic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.compose.IFPlanLeiteTheme
import com.app.ifplan_leite.R
import com.app.ifplan_leite.model.TitleAndValue
import com.app.ifplan_leite.model.utils.formatterCurrency
import com.app.ifplan_leite.view.components.CardInfoComponent
import com.app.ifplan_leite.view_model.SimulateViewModel

@Composable
fun SystemsCostsResultEconomicView(
    simulateViewModel: SimulateViewModel = hiltViewModel()
){
    val simulateState = simulateViewModel.systemCostsResultEconomicState.collectAsState().value

    val items = listOf(
        TitleAndValue("Produção diária (L/dia)", formatterCurrency(simulateState.prodDiaria, 0)),
        TitleAndValue("Produção de leite (L/ha/dia)", formatterCurrency(simulateState.prodLeiteDia, 0)),
        TitleAndValue("Produção de leite (L/ha/ano) ",formatterCurrency(simulateState.prodLeiteAno, 0)),
        TitleAndValue("Perda receita estresse (R$/ano)", formatterCurrency(simulateState.perdReceitaEstresse)),
        TitleAndValue("COE (R$/L)", formatterCurrency(simulateState.coe)),
        TitleAndValue("COT (R$/L)", formatterCurrency(simulateState.cot, 2)),
        TitleAndValue("ML (R$/L)", formatterCurrency(simulateState.mlArea, 2)),
        TitleAndValue("Receita por área (R$/ha/ano)", formatterCurrency(simulateState.receitaTotalAno,2 )),
        TitleAndValue("TRCI (%a.a.)", formatterCurrency(simulateState.trci)),
        TitleAndValue("Payback (anos)", formatterCurrency(simulateState.payback, 2)),
    )

    CardInfoComponent(
        title = stringResource(R.string.sistems_costs_results_economic),
        listItems = items,
        showButton = false
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SystemsCostsResultEconomicViewPreview() {
    IFPlanLeiteTheme {
        SystemsCostsResultEconomicView()
    }
}


