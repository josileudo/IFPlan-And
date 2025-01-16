package com.app.ifplan_leite.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.compose.IFPlanLeiteTheme
import com.app.ifplan_leite.core.data.model.mock.MockSimulateItems
import com.app.ifplan_leite.ui.components.button.IFPlanButton
import com.app.ifplan_leite.ui.components.card.IfPlanHomeCardList
import com.app.ifplan_leite.ui.components.search.IfPlanSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigationToNewSimulation: () -> Unit = {} ) {
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
            IfPlanSearchBar()

            IfPlanHomeCardList(
                modifier = Modifier.fillMaxWidth(),
                data = MockSimulateItems
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    IFPlanLeiteTheme {
        HomeScreen()
    }
}