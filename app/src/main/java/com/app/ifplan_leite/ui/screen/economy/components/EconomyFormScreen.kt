package com.app.ifplan_leite.ui.screen.economy.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.app.ifplan_leite.R
import com.app.ifplan_leite.ui.components.form.IfPlanFormContainer
import com.app.ifplan_leite.ui.screen.economy.EconomyUiEvent
import com.app.ifplan_leite.ui.screen.economy.EconomyUiState
import com.app.ifplan_leite.ui.screen.route.Routes

@Composable
fun EconomyFormScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    uiState: EconomyUiState? = null,
    onEvent: (EconomyUiEvent) -> Unit = {},
) {
    val economyState = uiState

//    LaunchedEffect(Unit) {
//        economyViewModel.loadEconomyData()
//    }

    Box(
        modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ) {
        IfPlanFormContainer(
            formTitle = stringResource(R.string.economy),
            onNavigateBack = { navController?.popBackStack() },
            onSaveClick = {
                onEvent(EconomyUiEvent.OnSaveEconomy)
                navController?.navigate(Routes.dashboard)
//                if(economyState.isSuccess) {
//                    navController?.navigate(Routes.dashboard)
//                }
            }
        ) {
            EconomyFormView(
                investmentsPerLiters = uiState?.investmentsPerLiters ?: 0.0,
                familyIncome = uiState?.familyIncome ?: 0.0,
                depreciationRate = uiState?.depreciationRate ?: 0.0,
                onFieldChange = { field, value ->
                    onEvent(EconomyUiEvent.OnUpdateEconomyFields(field = field, value = value))
                }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AnimalFormScreenPreview() {
    EconomyFormScreen()
}
