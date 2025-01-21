package com.app.ifplan_leite.ui.screen.animal

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.app.ifplan_leite.R
import com.app.ifplan_leite.core.data.model.TitleAndValue
import com.app.ifplan_leite.core.data.model.utils.formatterCurrency
import com.app.ifplan_leite.ui.components.card.IfPlanCardInfoResultContainer
import com.app.ifplan_leite.ui.screen.route.Routes

@Composable
fun AnimalScreen(
    uiState: AnimalUiState,
    navController: NavController? = null
) {
    val state = uiState.animalResult
    val mockValues = listOf(
        TitleAndValue(
            stringResource(R.string.body_weight_kg),
            formatterCurrency(state?.pesoCorporal ?: 0.0, 3)
        ),
        TitleAndValue(
            stringResource(R.string.milk_production_lvd),
            formatterCurrency(state?.milkProduction ?: 0.0, 1)
        ),
        TitleAndValue(
            "Teor de gordura no leite (%)",
            formatterCurrency(state?.milkFatContent ?: 0.0, 1)
        ),
        TitleAndValue(
            "Teor de PB no leite (%)",
            formatterCurrency(state?.pbFatMilk ?: 0.0, 1)),
        TitleAndValue(
            "Deslocamento horizontal (m)",
            formatterCurrency(state?.horizontalShift ?: 0.0, 1)
        ),
        TitleAndValue(
            "Deslocamento Vertical (m)",
            formatterCurrency(state?.verticalShift ?: 0.0, 1)
        ),
        TitleAndValue(
            "Vacas em lactação (%)",
            formatterCurrency(state?.lactatingCows ?: 0.0, 1)),
    )

    IfPlanCardInfoResultContainer(
        title = stringResource(R.string.animal),
//        isLoading = animalState.isSaving,
//        error = animalState.error,
        listItems = mockValues,
        onClick = {
            navController?.navigate(Routes.animalInput)
        }
    )
}
