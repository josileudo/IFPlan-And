package com.app.ifplan_leite.ui.components.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import com.app.compose.IFPlanLeiteTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun IfPlanSearchBar(
    placeholder: String = "Procurar por simulação"
) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    // MARK: Home screen
    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        query = query,
        onQueryChange = { query = it },
        onSearch = {
            println(it)
        },
        active = active,
        trailingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search")
        },
        placeholder = { Text(placeholder) },
        onActiveChange = { active = it }
    ) { }
}

@Preview(uiMode = 0,)
@Composable
fun IfPlanSearchBarPreview() {
    IFPlanLeiteTheme {
        IfPlanSearchBar()
    }
}