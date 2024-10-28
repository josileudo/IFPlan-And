package com.example.ifplan_leite.view.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifplan_leite.R
import com.example.ifplan_leite.ui.theme.IFPlanLeiteTheme

@Composable
fun FormInputValuesComponent(
    modifier: Modifier = Modifier,
    formTitle: String = "",
    onSaveClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Título do formulário
        BottomSheetTitle(title = formTitle)

        // Conteúdo do formulário (campos dinâmicos)
        content()

        // Botão de salvar no final do formulário
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onSaveClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(stringResource(id = R.string.save))
        }
    }
}

@Composable
private fun BottomSheetTitle(modifier: Modifier = Modifier, title: String) {
    Box(
        modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = MaterialTheme.typography.headlineLarge.color,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = modifier.padding(bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomSheetComponentPreview() {
    IFPlanLeiteTheme {
        FormInputValuesComponent(
            formTitle = "Test sheet")
        {
            Text("Content example")
        }
    }
}