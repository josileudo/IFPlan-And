package com.app.ifplan_leite.ui.screen.home

import com.app.ifplan_leite.core.data.entities.Simulation
import com.app.ifplan_leite.core.data.state.FilteredItems

data class HomeUiState(
    val simulateItems: List<Simulation>? = null,
    val filteredItems: FilteredItems? = null,
)