package com.example.ifplan_leite.view.screens.animal

import CurrencyInputViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ifplan_leite.model.TitleAndValue
import com.example.ifplan_leite.view.components.BottomSheetView
import com.example.ifplan_leite.view.components.TitleValueComponent
import com.example.ifplan_leite.view.screens.animal.components.AnimalFormView
import com.example.ifplan_leite.view_model.AnimalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalView(
    modifier: Modifier = Modifier,
    animalViewModel: AnimalViewModel = viewModel())
{
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    var showBottomSheet by remember {  mutableStateOf(false) }

    Card(modifier.fillMaxWidth()) {
        // TODO: After create a component for it
        Column(modifier.padding(16.dp)) {
            Text(
                text = "Animal",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                modifier = modifier.padding(bottom = 8.dp)
            )
            val mockValues = listOf(
                TitleAndValue("Peso corporal (kg)", "10,00"),
                TitleAndValue("Produção de leite (L/vaca/dia)", "110,00"),
                TitleAndValue("Teor de gordura no leite (%)", "110,00"),
                TitleAndValue("Teor de PB no leite (%)", "110,00"),
                TitleAndValue("Deslocamento horizontal (m)", "110,00"),
                TitleAndValue("Deslocamento Vertical (m)", "110,00"),
                TitleAndValue("Vacas em lactação (%)", "110,00"),
            )

            for (value in mockValues) {
                TitleValueComponent(value.title, value.value)
            }

            Box(
                modifier.fillMaxWidth().padding(top = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    showBottomSheet = true
                }, ) {
                    Text(text = "Editar", fontWeight = FontWeight.Bold)
                }
            }
        }
    }

    // MARK: Bottom Sheet
    BottomSheetView(
        sheetTitle = "Editar Animal",
        sheetState = sheetState,
        isVisible = showBottomSheet,
        onCloseRequest = { showBottomSheet = true },
        onSaveClick = {
//            showBottomSheet = false
            animalViewModel.saveAnimal()
        }
    ) {
        AnimalFormView(animalViewModel = animalViewModel, currencyInputViewModel = CurrencyInputViewModel())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimalViewPreview() {
    AnimalView( )
}


