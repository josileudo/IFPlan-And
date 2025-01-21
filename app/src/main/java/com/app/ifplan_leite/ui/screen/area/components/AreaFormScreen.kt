package com.app.ifplan_leite.ui.screen.area.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.app.ifplan_leite.ui.components.form.IfPlanFormContainer
import com.app.ifplan_leite.ui.screen.area.AreaUiEvent
import com.app.ifplan_leite.ui.screen.area.AreaUiState
import com.app.ifplan_leite.ui.screen.route.Routes

@Composable
fun AreaFormScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    uiState: AreaUiState? = null,
    onEvent: (AreaUiEvent) -> Unit = {},
) {

//    LaunchedEffect(Unit) {
//        areaViewModel.loadAreaData()
//    }

    Surface(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        IfPlanFormContainer(
            formTitle = "Área",
            onNavigateBack = { navController?.popBackStack() },
            onSaveClick = {
                onEvent(AreaUiEvent.OnSaveArea)
                navController?.navigate(Routes.dashboard)
            }
        ) {
            AreaFormView(
                area = uiState?.area ?: 0.0,
                picketsNumber = uiState?.picketsNumber ?: 0.0,
                onFieldChange = { field, value ->
                    onEvent(AreaUiEvent.OnUpdateAreaFields(field, value))
                }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AnimalFormScreenPreview() {
    AreaFormScreen()
}
