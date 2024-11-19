package com.app.ifplan_leite.view.screens.area

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.ifplan_leite.R
import com.app.ifplan_leite.Routes
import com.app.ifplan_leite.model.TitleAndValue
import com.app.ifplan_leite.model.utils.formatterCurrency
import com.app.ifplan_leite.view.components.CardInfoComponent
import com.app.ifplan_leite.view_model.AreaViewModel

@Composable
fun AreaView(
    areaViewModel: AreaViewModel = hiltViewModel(),
    navController: NavController?= null
){
    val areaState = areaViewModel.areaState.collectAsState().value
    val mockValues = listOf(
        TitleAndValue(stringResource(R.string.area_ha), formatterCurrency(areaState.area, 1)),
        TitleAndValue(stringResource(R.string.pickets_number), formatterCurrency(areaState.picketsNumber, 1)),
    )

    CardInfoComponent(
        title = stringResource(R.string.area),
        isLoading = areaState.isSaving,
        error = areaState.error,
        listItems = mockValues,
        onClick = {
            navController?.navigate(Routes.areaInput)
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AreaViewPreview() {
    AreaView()
}


