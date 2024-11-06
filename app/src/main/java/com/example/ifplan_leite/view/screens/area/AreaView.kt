package com.example.ifplan_leite.view.screens.area

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ifplan_leite.model.TitleAndValue
import com.example.ifplan_leite.view.components.TitleValueComponent
import com.example.ifplan_leite.view_model.AreaViewModel

@Composable
fun AreaView(
    modifier: Modifier = Modifier,
    areaViewModel: AreaViewModel = hiltViewModel(),
    navController: NavController?= null
){
    val areaState = areaViewModel.areaState.collectAsState().value

    Card(modifier.fillMaxWidth()) {
        // TODO: After create a component for it
        Column(modifier.padding(16.dp)) {
            Text(
                text = "Area",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                modifier = modifier.padding(bottom = 8.dp)
            )

            val mockValues = listOf(
                TitleAndValue("Area (ha)", areaState.area.toString()),
                TitleAndValue("Números de piquetes (unid)", areaState.picketsNumber.toString()),
            )

            for (value in mockValues) {
                TitleValueComponent(value.title, value.value)
            }

            Box(
                modifier.fillMaxWidth().padding(top = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    navController?.navigate("area_input_screen")
                }, ) {
                    Text(text = "Editar", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AreaViewPreview() {
    AreaView( )
}


