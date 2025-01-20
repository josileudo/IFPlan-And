package com.app.ifplan_leite.ui.components.card

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.app.ifplan_leite.core.data.model.mock.MockSimulateItems
import com.app.ifplan_leite.core.data.state.SimulateItems

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun IfPlanHomeCardList(
    modifier: Modifier = Modifier,
    data: List<SimulateItems>,
    onSimulateClick: (SimulateItems) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(data.size) { index ->
            IfPlanHomeCard(
                modifier = modifier                    ,
                data = data[index],
                onClick = { onSimulateClick(data[index])}
            )
        }
    }
}


@Preview
@Composable
fun IfPlanHomeCardListPreview() {
    IfPlanHomeCardList(modifier = Modifier.fillMaxWidth(), data = MockSimulateItems)
}