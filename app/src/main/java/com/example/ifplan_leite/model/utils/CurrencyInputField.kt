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
) {

    var textFieldValue by remember { mutableStateOf(
        formatDoubleToString(value)
    ) }

    TextInputView(
        value = textFieldValue,
        onValueChange = { newValue ->
            val cleanValue = newValue.filter { it.isDigit() }
            textFieldValue = cleanValue
            val doubleValue = cleanValue.toDoubleOrNull()?.div(100) ?: 0.0
            viewModel.updateAmount(doubleValue)

            onValueChange(doubleValue)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = label,
        visualTransformation = CurrencyAmountInputVisualTransformation(),
    )
}

private fun formatDoubleToString(value: Double): String {
    if (value == 0.0) return "" // Retorna string vazia se for zero
    val longValue = (value * 100).toLong()
    return longValue.toString()
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CurrencyInputFieldPreview(viewModel: CurrencyInputViewModel) {
//    CurrencyInputField(
//        viewModel = viewModel,
//        onValueChange = { newValue ->
//            println("New value: $newValue")
//        }
//    )
//}