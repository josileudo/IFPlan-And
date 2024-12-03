package com.app.ifplan_leite.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TitleValueComponent(title: String, value: String, modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier.fillMaxWidth().padding(0.dp,8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            ){
            // TODO: After Create a func to use it
            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = value.ifEmpty { "0.0" },
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.SemiBold
            )
        }

        HorizontalDivider(
            modifier.fillMaxWidth(),
            color = DividerDefaults.color,
            thickness = 3.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitleValueComponentPreview() {
    TitleValueComponent("Title", "100")
}