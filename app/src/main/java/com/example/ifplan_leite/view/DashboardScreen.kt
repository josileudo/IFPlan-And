package com.example.ifplan_leite.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.IFPlanLeiteTheme
import com.example.ifplan_leite.view.screens.animal.AnimalView
import com.example.ifplan_leite.view.screens.area.AreaView
import com.example.ifplan_leite.view.screens.economy.EconomyView
import com.example.ifplan_leite.view.screens.soilWaterPlantAnimal.SoilWaterPlantAnimalView
import com.example.ifplan_leite.view.screens.systemsCostsResultEconomic.SystemsCostsResultEconomicView
import com.example.ifplan_leite.view.screens.weatherAndSoil.WeatherAndSoilView
import com.example.ifplan_leite.view_model.SimulateViewModel
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    navController: NavController ?= null
) {
    Surface (modifier.fillMaxSize()) {
        Column(
            modifier
                .fillMaxWidth()
//                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            //MARK: Card to dashboard view
            CardsOfDashboard(navController)

            //MARK: Button to simulate result
            SimulateButton()
        }
    }
}

@Composable
fun CardsOfDashboard(navController: NavController?) {
    AreaView(navController = navController)
    EconomyView(navController = navController)
    WeatherAndSoilView(navController = navController)
    AnimalView(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimulateButton(
    simulateViewModel: SimulateViewModel = hiltViewModel(),
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Button(
            onClick = {
                showBottomSheet = true
                simulateViewModel.simulate()
            }
        ) {
            Text(
                text = "Simular",
                fontWeight = FontWeight.Bold
            )
        }
    }

    ResultsBottomSheet(
        isShow = showBottomSheet,
        sheetState = sheetState,
        onDismissRequest = {
            showBottomSheet = false
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsBottomSheet(
    isShow: Boolean = false,
    onDismissRequest: () -> Unit = {},
    sheetState: SheetState
) {
    val scope = rememberCoroutineScope()

    if(isShow) {
        ModalBottomSheet(
            onDismissRequest = { onDismissRequest() },
            sheetState = sheetState,
            modifier = Modifier.fillMaxHeight().padding(top = 8.dp)
        ) {
            val verticalScroll = rememberScrollState(0)
            Column(
                Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom=12.dp)
                    .verticalScroll(verticalScroll)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SoilWaterPlantAnimalView()
                    SystemsCostsResultEconomicView()
                }

                // TODO: Add a spacing
                Spacer(Modifier.padding(vertical = 12.dp))
                // MARK: Content result
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                if(!sheetState.isVisible) {
                                    onDismissRequest()
                                }
                            }
                        }
                    ) {
                        Text(text = "Fechar")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    IFPlanLeiteTheme {
        val navController = rememberNavController()
        DashboardScreen(navController = navController)
    }
}