package com.example.ifplan_leite.view
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ifplan_leite.R
import com.example.ifplan_leite.Routes
import com.example.ifplan_leite.ui.theme.IFPlanLeiteTheme

@Composable
fun HomeScreen(
    navController: NavController ?= null,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier)

        Card(
            modifier,
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = stringResource(R.string.welcome_ifplan_leite),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(32.dp),
                textAlign = TextAlign.Center,
                lineHeight = 32.sp
            )
        }

        Spacer(modifier)

        Button(onClick = {
            navController?.navigate(Routes.dashboard)
        }) {
            Text(
                text = stringResource(R.string.start),
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.lapis_logo),
            contentDescription = stringResource(R.string.logo_lapis_foundation),
            modifier
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Default-system"
)
@Composable
fun HomeScreenPreview() {
    IFPlanLeiteTheme {
        HomeScreen()
    }
}