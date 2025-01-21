package com.app.ifplan_leite.ui.components.form

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.compose.IFPlanLeiteTheme
import com.app.ifplan_leite.R
import com.app.ifplan_leite.ui.components.navigation.TopBarConfig

@Composable
fun IfPlanFormContainer(
    modifier: Modifier = Modifier,
    formTitle: String = "",
    onSaveClick: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { TopBarConfig(formTitle, onNavigateBack) }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .imePadding()
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            // Conteúdo do formulário (campos dinâmicos)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                content()
            }

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
}


@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomSheetComponentPreview() {
    IFPlanLeiteTheme {
        IfPlanFormContainer(
            formTitle = "Test sheet"
        )
        {
            Text("Content example")
        }
    }
}