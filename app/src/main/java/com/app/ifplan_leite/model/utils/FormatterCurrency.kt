package com.app.ifplan_leite.model.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

fun formatterCurrency(value: Double, decimalsNumber: Int = 3): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR")) as DecimalFormat
    val symbols: DecimalFormatSymbols = formatter.decimalFormatSymbols
    symbols.setCurrencySymbol("")
    formatter.decimalFormatSymbols = symbols
    formatter.maximumFractionDigits = decimalsNumber
    return formatter.format(value)
}