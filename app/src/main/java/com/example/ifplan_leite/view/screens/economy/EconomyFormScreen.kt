package com.example.ifplan_leite.view.screens.economy

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ifplan_leite.R
import com.example.ifplan_leite.Routes
import com.example.ifplan_leite.view.components.FormInputValuesComponent
import com.example.ifplan_leite.view.screens.economy.components.EconomyFormView
import com.example.ifplan_leite.view_model.EconomyViewModel

@Composable
fun EconomyFormScreen(
    modifier: Modifier = Modifier,
    economyViewModel: EconomyViewModel = hiltViewModel(),
    navController: NavController? = null
) {
    val economyState = economyViewModel.economyState.collectAsState().value

    LaunchedEffect(Unit) {
        economyViewModel.loadEconomyData()
    }

    Box(
        modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FormInputValuesComponent(
            formTitle = stringResource(R.string.economy),
            onNavigateBack = { navController?.popBackStack() },
            onSaveClick = {
                economyViewModel.saveEconomy()

                if(economyState.isSuccess) {
                    navController?.navigate(Routes.dashboard)
                }
            }
        ) {
            EconomyFormView( economyViewModel = economyViewModel )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AnimalFormScreenPreview() {
    EconomyFormScreen()
}
