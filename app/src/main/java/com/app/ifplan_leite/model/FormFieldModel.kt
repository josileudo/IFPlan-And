package com.app.ifplan_leite.model

import androidx.compose.ui.Modifier

data class FormFieldModel(
    val modifier: Modifier = Modifier,
    val label: String,
    val value: Double,
    val decimalsNumber: Int = 3,
    val onValueChange: (Double) -> Unit,
)
