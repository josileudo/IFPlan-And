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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.compose.IFPlanLeiteTheme
import com.app.ifplan_leite.core.data.model.mock.MockSimulateItems
import com.app.ifplan_leite.ui.components.button.IFPlanButton
import com.app.ifplan_leite.ui.components.card.IfPlanHomeCardList
import com.app.ifplan_leite.ui.components.search.IfPlanSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit = {},
    navigationToNewSimulation: () -> Unit = {},
) {
    LaunchedEffect(true) {
        onEvent(HomeUiEvent.OnFetchAllSimulations)
    }

    Scaffold(
        floatingActionButton = {
            IFPlanButton(iconRes = Icons.Default.Add, onClick = {
                navigationToNewSimulation()
            })
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            uiState.filteredItems?.titleQuery?.let {
                IfPlanSearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Procurar por simulação",
                    onValueChange = {  onEvent.invoke(HomeUiEvent.OnFetchSimulationByField(it)) },
                    value = it
                )
            }

            if(uiState.simulateItems?.isNotEmpty() == true) {
                uiState.simulateItems?.let {
                    IfPlanHomeCardList(
                        modifier = Modifier.fillMaxWidth(),
                        data = it
                    )
                }
            } else {
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