package com.app.ifplan_leite.ui.screen.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.app.compose.IFPlanLeiteTheme
import com.app.ifplan_leite.core.data.model.mock.MockSimulateItems
import com.app.ifplan_leite.core.data.state.SimulateItems
import com.app.ifplan_leite.ui.components.button.IFPlanButton
import com.app.ifplan_leite.ui.components.navigation.TopBarConfig
import com.app.ifplan_leite.ui.screen.animal.IfPlanAnimalScreen
import com.app.ifplan_leite.ui.screen.area.AreaView
import com.app.ifplan_leite.ui.screen.economy.EconomyView
import com.app.ifplan_leite.ui.screen.route.BottomNavItem
import com.app.ifplan_leite.ui.screen.soilWaterPlantAnimal.SoilWaterPlantAnimalView
import com.app.ifplan_leite.ui.screen.systemsCostsResultEconomic.SystemsCostsResultEconomicView
import com.app.ifplan_leite.ui.screen.weatherAndSoil.WeatherAndSoilView
import com.app.ifplan_leite.view.SimulateViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    simulateItems: SimulateItems? = null,
    navController: NavController? = null,
    onEvent: (ResultUiEvent) -> Unit = {},
    uiState: ResultUiState? = null
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Dados", "Resultado")

    LaunchedEffect(true) {
        simulateItems?.id?.let {
            onEvent(ResultUiEvent.OnFetchResultSimulationById(it))
        }
    }

    Scaffold(
        topBar = {
            TopBarConfig(
                formTitle = simulateItems?.title ?: "Criar nova simulação",
                onNavigateBack = { navController?.popBackStack() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SingleChoiceSegmentedButtonRow(modifier = modifier) {
                options.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options.size
                        ),
                        onClick = { selectedIndex = index },
                        selected = index == selectedIndex,
                        label = { Text(label) }
                    )
                }
            }

            Spacer(modifier = modifier)

            //MARK: Card to dashboard view
            if(selectedIndex == 0) {
                CardsOfDashboard(navController = navController)
            } else {
                Column(
                    Modifier
//                        .padding(horizontal = 12.dp)
//                        .padding(bottom=12.dp)
//                        .verticalScroll(verticalScroll)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        SoilWaterPlantAnimalView(resultSimulation = uiState?.resultSimulationById)
                        SystemsCostsResultEconomicView(resultSimulation = uiState?.resultSimulationById)
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
                        IFPlanButton(
                            modifier = modifier.fillMaxWidth(),
                            iconRes = Icons.Rounded.Check,
                            text = "Salvar simulação",
//                            onClick =  {
//                                scope.launch {
//                                    sheetState.hide()
//                                }.invokeOnCompletion {
//                                    if(!sheetState.isVisible) {
//                                        onDismissRequest()
//                                        navController?.navigate(BottomNavItem.Home.route)
//                                    }
//                                }
//                            }
                        )
                    }
                }
            }

            //MARK: Button to simulate result
            SimulateButton(navController = navController)
        }
    }
}

@Composable
fun CardsOfDashboard(navController: NavController?) {
    AreaView(navController = navController)
    EconomyView(navController = navController)
    WeatherAndSoilView(navController = navController)
    IfPlanAnimalScreen(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimulateButton(
    simulateViewModel: SimulateViewModel = hiltViewModel(),
    navController: NavController? = null
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
        },
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsBottomSheet(
    modifier: Modifier = Modifier,
    isShow: Boolean = false,
    onDismissRequest: () -> Unit = {},
    sheetState: SheetState,
    navController: NavController? = null
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
                    IFPlanButton(
                        modifier = modifier.fillMaxWidth(),
                        iconRes = Icons.Rounded.Check,
                        text = "Salvar simulação",
                        onClick =  {
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                if(!sheetState.isVisible) {
                                    onDismissRequest()
                                    navController?.navigate(BottomNavItem.Home.route)
                                }
                            }
                        }
                    )
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
        DashboardScreen(navController = navController, simulateItems = MockSimulateItems[0])
    }
}