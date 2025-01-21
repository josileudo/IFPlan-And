package com.app.ifplan_leite.ui.screen.animal.components

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
import com.app.ifplan_leite.R
import com.app.ifplan_leite.ui.components.form.IfPlanFormContainer
import com.app.ifplan_leite.ui.screen.animal.AnimalUiEvent
import com.app.ifplan_leite.ui.screen.animal.AnimalUiState
import com.app.ifplan_leite.ui.screen.animal.AnimalViewModel
import com.app.ifplan_leite.ui.screen.area.AreaUiEvent
import com.app.ifplan_leite.ui.screen.area.AreaUiState
import com.app.ifplan_leite.ui.screen.route.Routes

@Composable
fun AnimalFormScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    uiState: AnimalUiState?= null,
    onEvent: (AnimalUiEvent) -> Unit = {},
) {
    val animalState = uiState

//    LaunchedEffect(Unit) {
//        animalViewModel.loadAnimalData()
//    }

    Box(
        modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ){
        IfPlanFormContainer(
            formTitle = stringResource(R.string.animal),
            onNavigateBack = { navController?.popBackStack() },
            onSaveClick = {
                onEvent(AnimalUiEvent.OnSaveAnimal)
                navController?.navigate(Routes.dashboard)
//                if(animalState.isSuccess) {
//                    navController?.navigate()
//                }
            }
        ) {
            AnimalFormView(
                pesoCorporal = uiState?.pesoCorporal ?: 0.0,
                milkProduction = uiState?.milkProduction ?: 0.0,
                milkFatContent = uiState?.milkFatContent ?: 0.0,
                pbFatMilk = uiState?.pbFatMilk ?: 0.0,
                horizontalShift = uiState?.horizontalShift ?: 0.0,
                verticalShift = uiState?.verticalShift ?: 0.0,
                lactatingCows = uiState?.lactatingCows ?: 0.0,
                onFieldChange = { field, value ->
                    onEvent(AnimalUiEvent.OnUpdateAnimalFields(field, value))
                } )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AnimalScreenPreview() {
    AnimalFormScreen()
}
