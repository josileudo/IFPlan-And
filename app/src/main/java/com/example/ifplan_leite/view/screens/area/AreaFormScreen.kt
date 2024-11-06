package com.example.ifplan_leite.view.screens.area

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ifplan_leite.view.components.FormInputValuesComponent
import com.example.ifplan_leite.view.screens.animal.components.AnimalFormView
import com.example.ifplan_leite.view.screens.area.components.AreaFormView
import com.example.ifplan_leite.view_model.AnimalViewModel
import com.example.ifplan_leite.view_model.AreaViewModel

@Composable
fun AreaFormScreen(
    modifier: Modifier = Modifier,
    areaViewModel: AreaViewModel = hiltViewModel(),
    navController: NavController? = null
) {
    val areaState = areaViewModel.areaState.collectAsState().value
    Box(
        modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ){
        FormInputValuesComponent(
            formTitle = "Area",
            onSaveClick = {
                areaViewModel.saveAnimal()

                if(areaState.isSuccess) {
                    navController?.navigate("dashboard_screen")
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
