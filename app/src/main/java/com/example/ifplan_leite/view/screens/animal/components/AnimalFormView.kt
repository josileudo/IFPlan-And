package com.example.ifplan_leite.view.screens.animal.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.ifplan_leite.model.utils.CurrencyAmountInputVisualTransformation
import com.example.ifplan_leite.view.components.TextInputView

@Composable
fun AnimalFormView(modifier: Modifier = Modifier) {
    var pesoCorporal by remember { mutableStateOf("") }

    Column(
        modifier,
    ) {
        TextInputView(
            label = "Peso corporal (kg)",
            value = pesoCorporal,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = CurrencyAmountInputVisualTransformation(
                fixedCursorAtTheEnd = true
            ),
            onValueChange = {
                pesoCorporal = if (it.startsWith("0")) {
                    ""
                } else {
                    it
                }
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AnimalFormPreview() {
    AnimalFormView()
}