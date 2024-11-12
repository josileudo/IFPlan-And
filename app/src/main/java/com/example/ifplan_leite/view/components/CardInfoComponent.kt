package com.example.ifplan_leite.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ifplan_leite.R
import com.example.ifplan_leite.model.TitleAndValue

@Composable
fun CardInfoComponent (
    modifier: Modifier = Modifier,
    title: String = "",
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
    showButton: Boolean = true,
    error: String? = null,
    listItems:  List<TitleAndValue> = emptyList(),
) {
    Card(modifier.fillMaxWidth()) {
        Column(modifier.padding(16.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                modifier = modifier.padding(bottom = 8.dp)
            )

            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier.align(Alignment.CenterHorizontally)
                    )
                }

                error != null -> {
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = modifier.align(Alignment.CenterHorizontally)
                    )
                } else -> {
                    for (item in listItems) {
                        TitleValueComponent(item.title, item.value)
                    }

                if(showButton) {
                    Box(
                        modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Button(onClick = {
                            onClick()
                        }) {
                            Text(text = stringResource(R.string.edit), fontWeight = FontWeight.Bold)
                        }
                    }
                    }
                }
            }
        }
    }
}