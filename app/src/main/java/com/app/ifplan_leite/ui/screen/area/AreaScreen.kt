package com.app.ifplan_leite.ui.screen.area

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.app.ifplan_leite.R
import com.app.ifplan_leite.core.data.model.TitleAndValue
import com.app.ifplan_leite.core.data.model.utils.formatterCurrency
import com.app.ifplan_leite.ui.components.card.IfPlanCardInfoResultContainer
import com.app.ifplan_leite.ui.screen.route.Routes

@Composable
fun AreaScreen(
    navController: NavController? = null,
    uiState: AreaUiState
) {
    val mockValues = listOf(
        TitleAndValue(
            stringResource(R.string.area_ha),
            formatterCurrency(uiState.areaResult?.area ?: 0.0, 1)
        ),
        TitleAndValue(
            stringResource(R.string.pickets_number),
            formatterCurrency(uiState.areaResult?.picketsNumber ?: 0.0, 1)
        ),
    )

    IfPlanCardInfoResultContainer(
        title = stringResource(R.string.area),
//        isLoading = areaState.isSaving,
//        error = areaState.error,
        listItems = mockValues,
        onClick = {
            navController?.navigate(Routes.areaInput)
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AreaViewPreview() {
    AreaScreen(uiState = AreaUiState())
}


