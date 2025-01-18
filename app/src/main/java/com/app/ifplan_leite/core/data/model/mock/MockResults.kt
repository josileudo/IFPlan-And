package com.app.ifplan_leite.core.data.model.mock

import com.app.ifplan_leite.core.data.model.utils.formatToSimpleDate
import com.app.ifplan_leite.core.data.state.SimulateItems
import java.util.Date

val MockSimulateItems = listOf(
    SimulateItems(id=1, title = "Simulação 1", creationDate = Date().formatToSimpleDate(), description = "Teste de loteamento a"),
    SimulateItems(id=2, title = "Simulação 2", creationDate = Date().formatToSimpleDate(), description = "Teste de loteamento b"),
    SimulateItems(id=3, title = "Simulação 3", creationDate = Date().formatToSimpleDate(), description = "Teste de loteamento c"),
)