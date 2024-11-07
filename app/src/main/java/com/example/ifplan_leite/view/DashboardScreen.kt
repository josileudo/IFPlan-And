package com.example.ifplan_leite.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ifplan_leite.view.screens.animal.AnimalView
import com.example.ifplan_leite.view.screens.area.AreaView
import com.example.ifplan_leite.view.screens.economy.EconomyView


@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    navController: NavController ?= null
) {
    Box(modifier.fillMaxSize().imePadding()) {
        Column(
            modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AreaView(navController = navController)
            EconomyView(navController = navController)
            AnimalView(navController = navController)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, )
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}