package com.app.ifplan_leite.ui.screen.systemsCostsResultEconomic

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
fun SystemsCostsResultEconomicView(
    resultSimulation: ResultSimulation? = null
){
    val items =
    resultSimulation?.let {
        listOf(
            TitleAndValue("Produção diária (L/dia)", formatterCurrency(it.prodDiaria, 0)),
            TitleAndValue("Produção de leite (L/ha/dia)", formatterCurrency(it.prodLeiteDia, 0)),
            TitleAndValue("Produção de leite (L/ha/ano) ",
                formatterCurrency(it.prodLeiteAno, 0),
            ),
            TitleAndValue("Perda receita estresse (R$/ano)", formatterCurrency(it.perdReceitaEstresse)),
            TitleAndValue("COE (R$/L)", formatterCurrency(it.coe)),
            TitleAndValue("COT (R$/L)", formatterCurrency(it.cot, 2)),
            TitleAndValue("ML (R$/L)", formatterCurrency(it.mlArea, 2)),
            TitleAndValue("Receita por área (R$/ha/ano)", formatterCurrency(it.receitaTotalAno,2 )),
            TitleAndValue("TRCI (%a.a.)", formatterCurrency(it.trci)),
            TitleAndValue("Payback (anos)", formatterCurrency(it.payback, 7)),
        )
    }

    items?.let {
        IfPlanCardInfoResultContainer(
            title = stringResource(R.string.sistems_costs_results_economic),
            listItems = it,
            showButton = false
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SystemsCostsResultEconomicViewPreview() {
    IFPlanLeiteTheme {
        SystemsCostsResultEconomicView()
    }
}


