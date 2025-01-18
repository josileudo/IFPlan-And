package com.app.ifplan_leite.ui.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.compose.Gray100
import com.app.compose.IFPlanLeiteTheme
import com.app.ifplan_leite.core.data.model.mock.MockSimulateItems
import com.app.ifplan_leite.core.data.state.SimulateItems
import com.app.ifplan_leite.ui.components.button.IFPlanButton
import com.app.ui.theme.Typography

@Composable
fun IfPlanHomeCard(
    modifier: Modifier = Modifier,
    data: SimulateItems,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Gray100)
            .border(
                width =  1.dp,
                color = Gray100,
                shape = RoundedCornerShape(12.dp)
            ),
        onClick = { onClick() }
    ) {
        Row (
            modifier = modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = data.title,
                    style = Typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = data.creationDate,
                    style = Typography.titleSmall.copy(fontWeight = FontWeight.Normal)
                )
            }

            IFPlanButton(iconRes = Icons.Filled.ArrowDropDown, size = "xm")
        }
    }
}

@Preview
@Composable
fun IfPlanHomeCardPreview() {
    IFPlanLeiteTheme {
        IfPlanHomeCard(modifier = Modifier.fillMaxWidth(), data = MockSimulateItems[0])
    }
}