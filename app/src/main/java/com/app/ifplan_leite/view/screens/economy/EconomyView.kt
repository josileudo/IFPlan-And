package com.app.ifplan_leite.view.screens.economy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.ifplan_leite.R
import com.app.ifplan_leite.Routes
import com.app.ifplan_leite.model.TitleAndValue
import com.app.ifplan_leite.view.components.CardInfoComponent
import com.app.ifplan_leite.view_model.EconomyViewModel

@Composable
fun EconomyView(
    economyViewModel: EconomyViewModel = hiltViewModel(),
    navController: NavController?= null
){
    val economyState = economyViewModel.economyState.collectAsState().value
    val mockValues = listOf(
        TitleAndValue(stringResource(R.string.investment_per_liters), economyState.investmentsPerLiters.toString()),
        TitleAndValue(stringResource(R.string.family_income), economyState.familyIncome.toString()),
        TitleAndValue(stringResource(R.string.depreciation_rate), economyState.depreciationRate.toString()),
    )

    CardInfoComponent(
        title = stringResource(R.string.economy),
        isLoading = economyState.isSaving,
        error = economyState.error,
        listItems = mockValues,
        onClick = {
            navController?.navigate(Routes.economyInput)
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EconomyViewPreview() {
    EconomyView()
}


