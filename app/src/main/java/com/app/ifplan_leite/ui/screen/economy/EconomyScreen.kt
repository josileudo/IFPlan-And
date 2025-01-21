package com.app.ifplan_leite.ui.screen.economy

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.app.ifplan_leite.R
import com.app.ifplan_leite.core.data.model.TitleAndValue
import com.app.ifplan_leite.ui.components.card.IfPlanCardInfoResultContainer
import com.app.ifplan_leite.ui.screen.route.Routes

@Composable
fun EconomyScreen(
    navController: NavController? = null,
    uiState: EconomyUiState
) {
    val economyState = uiState
    val mockValues = listOf(
        TitleAndValue(
            stringResource(R.string.investment_per_liters),
            economyState.investmentsPerLiters.toString()
        ),
        TitleAndValue(stringResource(R.string.family_income), economyState.familyIncome.toString()),
        TitleAndValue(
            stringResource(R.string.depreciation_rate),
            economyState.depreciationRate.toString()
        ),
    )

    IfPlanCardInfoResultContainer(
        title = stringResource(R.string.economy),
//        isLoading = economyState.isSaving,
//        error = economyState.error,
        listItems = mockValues,
        onClick = {
            navController?.navigate(Routes.economyInput)
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EconomyViewPreview() {
    EconomyScreen(uiState = EconomyUiState())
}


