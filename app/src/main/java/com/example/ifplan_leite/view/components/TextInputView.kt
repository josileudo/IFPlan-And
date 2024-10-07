package com.example.ifplan_leite.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextInputView(
        modifier: Modifier = Modifier,
        maxLines: Int = 1,
        value: String,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        label: String = "",
        visualTransformation: VisualTransformation = VisualTransformation.None,
        onValueChange: (String) -> Unit = {}
) {
    TextField(
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        modifier = modifier.fillMaxWidth(),
        value = value,
        visualTransformation = visualTransformation,
        onValueChange = onValueChange
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextInputViewPreview() {
    TextInputView(modifier = Modifier, value = "123")
}