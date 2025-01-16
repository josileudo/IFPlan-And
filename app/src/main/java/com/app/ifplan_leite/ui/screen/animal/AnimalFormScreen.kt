package com.app.ifplan_leite.ui.screen.animal

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
import com.app.ifplan_leite.ui.screen.route.Routes
import com.app.ifplan_leite.ui.components.form.IfPlanFormContainer
import com.app.ifplan_leite.ui.screen.animal.components.AnimalFormView
import com.app.ifplan_leite.view.AnimalViewModel

@Composable
fun IfPlanAnimalFormScreen(
    modifier: Modifier = Modifier,
    animalViewModel: AnimalViewModel = hiltViewModel(),
    navController: NavController? = null
) {
    val animalState = animalViewModel.animalState.collectAsState().value

    LaunchedEffect(Unit) {
        animalViewModel.loadAnimalData()
    }

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
                animalViewModel.saveAnimal()

                if(animalState.isSuccess) {
//                    navController?.navigate()
                }
            }
        ) {
            AnimalFormView( animalViewModel = animalViewModel )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun IfPlanAnimalScreenPreview() {
    IfPlanAnimalFormScreen()
}
