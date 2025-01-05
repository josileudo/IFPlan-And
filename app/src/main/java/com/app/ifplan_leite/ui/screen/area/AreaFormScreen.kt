package com.app.ifplan_leite.ui.screen.area

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.ifplan_leite.ui.screen.route.Routes
import com.app.ifplan_leite.ui.components.form.IfPlanFormContainer
import com.app.ifplan_leite.ui.screen.area.components.AreaFormView
import com.app.ifplan_leite.view.AreaViewModel

@Composable
fun AreaFormScreen(
    modifier: Modifier = Modifier,
    areaViewModel: AreaViewModel = hiltViewModel(),
    navController: NavController? = null
) {
    val areaState = areaViewModel.areaState.collectAsState().value

    LaunchedEffect(Unit) {
        areaViewModel.loadAreaData()
    }

    Surface(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        IfPlanFormContainer(
            formTitle = "Área",
            onNavigateBack = { navController?.popBackStack() },
            onSaveClick = {
                areaViewModel.saveArea()

                if(areaState.isSuccess) {
                    navController?.navigate(Routes.dashboard)
                }
            }
        ) {
            AreaFormView( areaViewModel = areaViewModel )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AnimalFormScreenPreview() {
    AreaFormScreen()
}
