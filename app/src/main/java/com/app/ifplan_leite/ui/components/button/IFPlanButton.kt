package com.app.ifplan_leite.ui.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.compose.Gray100
import com.app.compose.GreenBase
import com.app.ui.theme.Typography

@Composable
fun IFPlanButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    isLoading: Boolean = false,
    iconRes: ImageVector? = null,
    size: String = "md"
) {
    val height = when (size) {
        "xm" -> 16.dp
        "sm" -> 22.dp
        "md" -> 48.dp
        else -> 66.dp
    }
    val textStyle = when (size) {
        "xm" -> Typography.labelLarge.copy(fontSize = 8.sp)
        "sm" -> Typography.labelLarge.copy(fontSize = 10.sp)
        "md" -> Typography.labelLarge
        else -> Typography.labelLarge.copy(fontSize = 24.sp)
    }
    val iconSize = when (size) {
        "xm" -> 8.dp
        "sm" -> 12.dp
        "md" -> 20.dp
        else -> 32.dp
    }

    Button(
        modifier = modifier.heightIn(height),
        onClick = { onClick() },
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenBase
        ),
        contentPadding = if (text != null && iconRes != null) PaddingValues(0.dp) else ButtonDefaults.ContentPadding
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(iconSize)
                        .aspectRatio(1f),
                    color = Gray100,
                )
            } else {
                iconRes?.let {
                    Icon(imageVector = iconRes, contentDescription = "button icon")
                }

                text?.let {
                    Text(text = text.uppercase(), style = textStyle)
                }
            }
        }
    }
}

@Preview
@Composable
fun IFPlanButtonPreview() {
    IFPlanButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Salvar",
        iconRes = Icons.Rounded.Check
    )
}

@Preview
@Composable
fun IFPlanButtonBackPreview() {
    // Icon button
    IFPlanButton(
        iconRes = Icons.AutoMirrored.Rounded.ArrowBack
    )
}

@Preview
@Composable
fun IFPlanButtonTextPreview() {
    // Text button
    IFPlanButton(
        text = "Entrar"
    )
}

@Preview
@Composable
fun IFPlanButtonLoadingPreview() {
    // Loading button
    IFPlanButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Entrar",
        isLoading = true
    )
}