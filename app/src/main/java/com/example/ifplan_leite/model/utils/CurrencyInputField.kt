import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.ViewModel
import com.ban.currencyamountinput.CurrencyAmountInputVisualTransformation

class CurrencyInputViewModel : ViewModel() {
    private val _amount = mutableStateOf(0.0)
    val amount: State<Double> = _amount

    fun updateAmount(newAmount: Double) {
        _amount.value = newAmount
    }
}

@Composable
fun CurrencyInputField(
    viewModel: CurrencyInputViewModel,
    onValueChange: (Double) -> Unit
) {
    var textFieldValue by remember { mutableStateOf("") }

    TextField(
        value = textFieldValue,
        onValueChange = { newValue ->
            val cleanValue = newValue.filter { it.isDigit() }
            textFieldValue = cleanValue
            val doubleValue = cleanValue.toDoubleOrNull()?.div(100) ?: 0.0
            viewModel.updateAmount(doubleValue)
            onValueChange(doubleValue)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = CurrencyAmountInputVisualTransformation()
    )
}
//
//class CurrencyVisualTransformation : VisualTransformation {
//    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).apply {
//        maximumFractionDigits = 2
//        minimumFractionDigits = 2
//    }
//
//    override fun filter(text: AnnotatedString): TransformedText {
//        val cleanText = text.text.filter { it.isDigit() }
//        val doubleValue = cleanText.toDoubleOrNull()?.div(100) ?: 0.0
//        val formatted = numberFormat.format(doubleValue)
//
//        val newText = AnnotatedString(formatted)
//
//        val offsetMapping = object : OffsetMapping {
//            override fun originalToTransformed(offset: Int): Int = formatted.length
//            override fun transformedToOriginal(offset: Int): Int = cleanText.length
//        }
//
//        return TransformedText(newText, offsetMapping)
//    }
//}

// Usage in your Compose UI
@Composable
fun YourScreen(viewModel: CurrencyInputViewModel) {
    CurrencyInputField(
        viewModel = viewModel,
        onValueChange = { newValue ->
            // Handle the new value (e.g., update ViewModel or database)
            println("New value: $newValue")
        }
    )
}