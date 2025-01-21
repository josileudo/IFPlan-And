package com.app.ifplan_leite.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.compose.IFPlanLeiteTheme
import com.app.ifplan_leite.core.data.model.utils.formatToSimpleDate
import com.app.ifplan_leite.core.data.state.SimulateItems
import com.app.ifplan_leite.ui.components.button.IFPlanButton
import com.app.ifplan_leite.ui.components.card.IfPlanHomeCardList
import com.app.ifplan_leite.ui.components.search.IfPlanSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit = {},
    navigationToNewSimulation: (SimulateItems?) -> Unit = {}
) {
    LaunchedEffect(true) {
        onEvent(HomeUiEvent.OnFetchAllSimulations)
//        onEvent(HomeUiEvent.OnSubmitResultSimulation)
    }

    Scaffold(
        floatingActionButton = {
            IFPlanButton(iconRes = Icons.Default.Add, onClick = {
                navigationToNewSimulation(null)
            })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            uiState.filteredItems?.titleQuery?.let {
                IfPlanSearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Procurar por simulação",
                    onValueChange = { onEvent.invoke(HomeUiEvent.OnFetchSimulationByField(it)) },
                    value = it
                )
            }

            if (uiState.simulateItems?.isNotEmpty() == true) {
                uiState.simulateItems.let {
                    val result: List<SimulateItems> = it.map { item ->
                        SimulateItems(
                            id = item.id,
                            title = item.title,
                            creationDate = item.creationDate.formatToSimpleDate(),
                            description = item.description
                        )
                    }

                    IfPlanHomeCardList(
                        modifier = Modifier.fillMaxWidth(),
                        data = result,
                        onSimulateClick = { simulateItems ->
                            navigationToNewSimulation(simulateItems)
                        }
                    )
                }
            } else {
                // TODO Create a component to show a message
                Text(text = "Não possue simulações")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    IFPlanLeiteTheme {
        HomeScreen(uiState = HomeUiState(), onEvent = {}, navigationToNewSimulation = {})
    }
}