package com.example.ifplan_leite.view.screens.animal.components

import CurrencyInputField
import CurrencyInputViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ban.currencyamountinput.CurrencyAmountInputVisualTransformation
import com.example.ifplan_leite.view.components.TextInputView
import com.example.ifplan_leite.view_model.AnimalViewModel
import rememberCurrencyVisualTransformation
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import kotlin.Byte.Companion.MAX_VALUE

@Composable
fun AnimalFormView(modifier: Modifier = Modifier, animalViewModel: AnimalViewModel, currencyInputViewModel: CurrencyInputViewModel) {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // PESO CORPORAL
        TextInputView(
            label = "Peso corporal (kg)",
            value = animalViewModel.formatCurrency(animalViewModel.pesoCorporal),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            visualTransformation = CurrencyAmountInputVisualTransformation(
            ),
            onValueChange = {
                animalViewModel.updatePesoCorporal(it )
            },
        )

        // PRODUÇÃO DE LEITE
        TextInputView(
            modifier = modifier.focusRequester(focusRequester),
            label = "Produção de leite (L/vaca/dia)",
            value = animalViewModel.formatCurrency(animalViewModel.milkProduction),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            visualTransformation = CurrencyAmountInputVisualTransformation(
            ),
            onValueChange = {
                animalViewModel.updateMilkProduction(it)
            },
        )

        // TEOR DE GORDURA
        TextInputView(
            label = "Teor de gordura no leite (%)",
            value = animalViewModel.formatCurrency(animalViewModel.milkFatContent),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            visualTransformation = CurrencyAmountInputVisualTransformation(
            ),
            onValueChange = {
                animalViewModel.updateMilkFatContent(it)
            },
        )

        // TEOR DE PB NO LEITE
        TextInputView(
            label = "Teor de PB no leite (%)",
            value = animalViewModel.formatCurrency(animalViewModel.pbFatMilk),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            visualTransformation = CurrencyAmountInputVisualTransformation(

            ),
            onValueChange = {
                animalViewModel.updatePbFatMilk(it)
            },
        )

        // DESLOCAMENTO HORIZONTAL
        TextInputView(
            label = "Deslocamento horizontal (m)",
            value = animalViewModel.formatCurrency(animalViewModel.horizontalShift),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            visualTransformation = CurrencyAmountInputVisualTransformation(

            ),
            onValueChange = {
                animalViewModel.updateHorizontalShift(it)
            },
        )

        // DESLOCAMENTO VERTICAL
        TextInputView(
            label = "Deslocamento vertical (m)",
            value = animalViewModel.formatCurrency(animalViewModel.verticalShift),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            visualTransformation = CurrencyAmountInputVisualTransformation(),
            onValueChange = {
                animalViewModel.updateVerticalShift(it)
            },
        )

        // VACAS EM LACTCAO
//        TextInputView(
//            label = "Vacas em lactação (%)",
//            value = animalViewModel.cleanDoubleInput(animalViewModel.lactatingCows),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
//            visualTransformation = CurrencyAmountInputVisualTransformation(),
//            onValueChange = {
//                animalViewModel.updateLactatingCows((it))
////                animalViewModel.updateLactatingCows(formatCurrency(it))
////                animalViewModel.currencyFormat(it)
//                println("*** ${it}")
////                println("*** animalViewModel.currencyFormat(it) ${animalViewModel.currencyFormat(it)}")
//                println("*** ${animalViewModel.lactatingCows}")
//
//            },
//        )

        CurrencyInputField(
            viewModel = currencyInputViewModel,
            onValueChange = { newValue ->
                animalViewModel.updateLactatingCows(newValue)
                println("*** $newValue")
                println("*** ${animalViewModel.lactatingCows}")
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AnimalFormPreview() {
    // Mock do AnimalRepository
//    val mockAnimalRepository = AnimalRepository(object : AnimalDao {
//        override suspend fun insertAnimal(animal: AnimalData) {
//            // Mock sem funcionalidade
//        }
//
//        override suspend fun getAllAnimals(): List<AnimalData> {
//            return emptyList() // Retorna uma lista vazia ou mockada
//        }
//    })

    // Cria o AnimalViewModel com o mock do repositório
//    val mockAnimalViewModel = AnimalViewModel(animalRepository = mockAnimalRepository).apply {
//        updatePesoCorporal("10.0")
//        updateMilkProduction("20.0")
//        updateMilkFatContent("3.5")
//        updateLactatingCows("40.0")
//    }

//    AnimalFormView(animalViewModel = mockAnimalViewModel)
}