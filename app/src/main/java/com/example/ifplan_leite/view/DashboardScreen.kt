package com.example.ifplan_leite.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplan_leite.model.BottomSheetViewModel
import com.example.ifplan_leite.model.TitleAndValue
import com.example.ifplan_leite.view.components.BottomSheetView
import com.example.ifplan_leite.view.components.TitleValueComponent
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: BottomSheetViewModel = BottomSheetViewModel()
) {
//    val showBottomSheet by viewModel.showBottomSheet.collectAsState()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    val coroutineScope = rememberCoroutineScope()
    var showBottomSheet by remember {  mutableStateOf(false) }

    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        // Animal
        Card(
            modifier.fillMaxWidth(),
        ) {
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
    }

    // MARK: Bottom Sheet
    if(showBottomSheet) {
        BottomSheetView(
            sheetTitle = "Editar Animal",
            sheetState = sheetState,
            onCloseRequest = { showBottomSheet = false },
            onSaveClick = { showBottomSheet = false }
        ) {
            // MARK: Here will have a forms
            Text("Content example")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true, )
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}