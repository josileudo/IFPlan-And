package com.example.ifplan_leite.view.screens.systemsCostsResultEconomic

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ifplan_leite.R
import com.example.ifplan_leite.model.TitleAndValue
import com.example.ifplan_leite.ui.theme.IFPlanLeiteTheme
import com.example.ifplan_leite.view.components.CardInfoComponent

@Composable
fun SystemsCostsResultEconomicView(){
//    val areaState = areaViewModel.areaState.collectAsState().value
    val items = listOf(
        TitleAndValue("Produção diária (L/dia)", "0"),
        TitleAndValue("Produção de leite (L/ha/dia)", "0"),
        TitleAndValue("Produção de leite (L/ha/ano) ","0"),
        TitleAndValue("Perda receita estresse (R$/ano)", "0"),
        TitleAndValue("COE (R$/L)", "0"),
        TitleAndValue("COT (R$/L)", "0"),
        TitleAndValue("ML (R$/L)", "0"),
        TitleAndValue("Receita por área (R$/ha/ano)", "0"),
        TitleAndValue("TRCI (%a.a.)", "0"),
        TitleAndValue("Payback (anos)", "0"),
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


