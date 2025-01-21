package com.app.ifplan_leite.ui.components.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarConfig(formTitle: String, onNavigateBack: () -> Unit) {
    TopAppBar(
        title = { BottomSheetTitle(title = formTitle) },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar"
                )
            }
        }
    )
}

@Composable
private fun BottomSheetTitle(modifier: Modifier = Modifier, title: String) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = title,
            color = MaterialTheme.typography.headlineLarge.color,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        )
    }
}