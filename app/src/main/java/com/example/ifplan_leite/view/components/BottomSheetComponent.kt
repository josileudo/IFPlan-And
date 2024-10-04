package com.example.ifplan_leite.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetView(
    modifier: Modifier = Modifier,
    sheetTitle: String = "",
    sheetState: SheetState,
    onCloseRequest: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()

    Box {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { onCloseRequest() },
            modifier = modifier
                .fillMaxHeight()
                .padding(8.dp)
        ){
            // Sheet content
            Column() {
                // Title
                BottomSheetTitle(modifier, title = sheetTitle)

                // MARK: Here will have a forms
                content()

                // MARK: Button to save forms
                Button(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onSaveClick()
                        }
                    }
                }) {
                    Text("Salvar")
                }
            }

        }

    }
}

@Composable
private fun BottomSheetTitle(modifier: Modifier, title: String) {
    Box(
        modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = modifier.padding(bottom = 8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomSheetComponentPreview() {
    BottomSheetView(
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false),
        sheetTitle = "Test sheet")
    {
        Text("Content example")
    }
}