package com.app.ifplan_leite.ui.screen.weatherAndSoil

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.app.ifplan_leite.R
import com.app.ifplan_leite.core.data.model.TitleAndValue
import com.app.ifplan_leite.core.data.model.utils.formatterCurrency
import com.app.ifplan_leite.ui.components.card.IfPlanCardInfoResultContainer
import com.app.ifplan_leite.ui.screen.route.Routes

@Composable
fun WeatherAndSoilScreen(
    navController: NavController? = null,
    uiState: WeatherAndSoilUiState
) {
    val weatherAndSoilState = uiState.weatherAndSoilResult
    val mockValues = listOf(
        TitleAndValue(
            stringResource(R.string.precipitation),
            formatterCurrency( weatherAndSoilState?.precipitation ?: 0.0)
        ),
        TitleAndValue(
            stringResource(R.string.maxTemperature),
            formatterCurrency(weatherAndSoilState?.maxTemperature ?: 0.0)
        ),
        TitleAndValue(
            stringResource(R.string.minTemperature),
            formatterCurrency(weatherAndSoilState?.minTemperature?: 0.0)
        ),
        TitleAndValue(
            stringResource(R.string.relativeHumidity),
            formatterCurrency(weatherAndSoilState?.relativeHumidity ?: 0.0)
        ),
        TitleAndValue(
            stringResource(R.string.velocityVents),
            formatterCurrency(weatherAndSoilState?.velocityVents ?: 0.0)
        ),
        TitleAndValue(
            stringResource(R.string.nDosage),
            formatterCurrency(weatherAndSoilState?.nDosage ?: 0.0)
        ),
        TitleAndValue(
            stringResource(R.string.otherAndWater),
            formatterCurrency(            weatherAndSoilState?.otherAndWater?: 0.0)
        ),
        TitleAndValue(
            stringResource(R.string.agua_disp_p_irriga_o_m3_dia),
            formatterCurrency(weatherAndSoilState?.waterAvailableToIrrigation ?: 0.0)
        ),
    )

    IfPlanCardInfoResultContainer(
        title = stringResource(R.string.weatherAndSoil),
//        isLoading = weatherAndSoilState.isSaving,
//        error = weatherAndSoilState.error,
        listItems = mockValues,
        onClick = {
            navController?.navigate(Routes.weatherAndSoilInput)
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeatherAndSoilViewPreview() {
    WeatherAndSoilScreen(uiState = WeatherAndSoilUiState())
}


