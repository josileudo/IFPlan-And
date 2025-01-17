package com.app.ifplan_leite.ui.screen.home

import com.app.ifplan_leite.core.data.state.FilteredItems
import com.app.ifplan_leite.core.data.state.SimulateItems

data class HomeUiState(
    val simulateItems: List<SimulateItems>? = null,
    val filteredItems: FilteredItems? = null,
)