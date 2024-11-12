package com.example.ifplan_leite.model.utils

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.ViewModel
import com.ban.currencyamountinput.CurrencyAmountInputVisualTransformation
import com.example.ifplan_leite.view.components.TextInputView
import kotlin.math.pow

class CurrencyInputViewModel : ViewModel() {
    private val _amount = mutableStateOf(0.0)
    val amount: State<Double> = _amount

    fun updateAmount(newAmount: Double) {
        _amount.value = newAmount
    }
}

@Composable
fun CurrencyInputField(
    viewModel: CurrencyInputViewModel = CurrencyInputViewModel(),
    onValueChange: (Double) -> Unit,
    label: String = "",
    value: Double = 0.0,
    decimalsNumber: Int = 2
) {

    var textFieldValue by remember { mutableStateOf(
        FormatDoubleToString(value, decimalsNumber)
    ) }

    TextInputView(
        value = textFieldValue,
        onValueChange = { newValue ->
            val cleanValue = newValue.filter { it.isDigit() }
            textFieldValue = cleanValue
            val factor = 10.0.pow(decimalsNumber.toDouble())
            val doubleValue = cleanValue.toDoubleOrNull()?.div(factor) ?: 0.0
            viewModel.updateAmount(doubleValue)
            onValueChange(doubleValue)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = label,
        visualTransformation = CurrencyAmountInputVisualTransformation(decimalsNumber = decimalsNumber),
    )
}

fun FormatDoubleToString(value: Double, decimalsNumber: Int): String {
    if (value == 0.0) return "" // Retorna string vazia se for zero
    val factor = 10.0.pow(decimalsNumber.toDouble())
    val longValue = (factor * value).toLong()

    return longValue.toString()
}
