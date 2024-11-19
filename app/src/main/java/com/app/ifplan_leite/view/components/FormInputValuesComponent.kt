package com.app.ifplan_leite.view.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.compose.IFPlanLeiteTheme
import com.app.ifplan_leite.R
import com.app.ifplan_leite.view_model.rememberImeState

@Composable
fun FormInputValuesComponent(
    modifier: Modifier = Modifier,
    formTitle: String = "",
    onSaveClick: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(imeState.value) {
        // TODO: State created to add scroll by keyboard.
//        if (imeState.value) {
//            scrollState.animateScrollTo(scrollState.viewportSize)
//        }
    }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarConfig(formTitle: String, onNavigateBack: () -> Unit) {
    TopAppBar(
        title = { BottomSheetTitle(title = formTitle) },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Voltar"
                )
            }
        }
    )
}

@Composable
private fun BottomSheetTitle(modifier: Modifier = Modifier, title: String) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = title,
            color = MaterialTheme.typography.headlineLarge.color,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
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