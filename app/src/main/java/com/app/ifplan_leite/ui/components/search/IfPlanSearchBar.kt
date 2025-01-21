package com.app.ifplan_leite.ui.components.search

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.compose.IFPlanLeiteTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun IfPlanSearchBar(
    modifier: Modifier = Modifier,
    placeholder: String = "Procurar por simulação",
    onValueChange: (String) -> Unit = {},
    value: String = ""
) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    // MARK: Home screen
    Column {
        TextField(
            modifier = modifier
                .border(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.background,
                    width = 1.dp
                ),
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
        )
    }
}


@Preview(uiMode = 0)
@Composable
fun IfPlanSearchBarPreview() {
    IFPlanLeiteTheme {
        IfPlanSearchBar(modifier = Modifier.fillMaxWidth())
    }
}