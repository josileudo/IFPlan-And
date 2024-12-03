package com.app.ifplan_leite.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ifplan_leite.data.state.AnimalState
import com.app.ifplan_leite.data.state.AreaState
import com.app.ifplan_leite.data.state.EconomyState
import com.app.ifplan_leite.data.state.SoilWaterPlantAnimalState
import com.app.ifplan_leite.data.state.SystemCostsResultEconomicState
import com.app.ifplan_leite.data.state.WeatherAndSoilState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class SimulateViewModel @Inject constructor(
    private val areaState: StateFlow<AreaState>,
    private val animalState: StateFlow<AnimalState>,
    private val economyState: StateFlow<EconomyState>,
    private val weatherAndSoilState: StateFlow<WeatherAndSoilState>
) : ViewModel() {
    private val _soilWaterPlantAnimalState = MutableStateFlow(SoilWaterPlantAnimalState())
    val soilWaterPlantAnimalState: StateFlow<SoilWaterPlantAnimalState> =
        _soilWaterPlantAnimalState.asStateFlow()
    private val _systemCostsResultEconomicState = MutableStateFlow(SystemCostsResultEconomicState())
    val systemCostsResultEconomicState: StateFlow<SystemCostsResultEconomicState> =
        _systemCostsResultEconomicState.asStateFlow()

    private fun updateSoilWaterPlantAnimalState(soilWaterPlantAnimalState: SoilWaterPlantAnimalState) {
        _soilWaterPlantAnimalState.update { soilWaterPlantAnimalState }
    }

    private fun updateSystemCostsResultEconomicState(systemCostsResultEconomicState: SystemCostsResultEconomicState) {
        _systemCostsResultEconomicState.update { systemCostsResultEconomicState }
    }

    fun simulate() {
        viewModelScope.launch {
            val temperaturaMaxima = weatherAndSoilState.value.maxTemperature
            val temperaturaMinima = weatherAndSoilState.value.minTemperature
            val velocidadeVento = weatherAndSoilState.value.velocityVents
            val precipitacao = weatherAndSoilState.value.precipitation
            val pesoCorporal = animalState.value.pesoCorporal
            val producaoLeite = animalState.value.milkProduction
            val teorPB = animalState.value.pbFatMilk
            val teorGordura = animalState.value.milkFatContent
            val desloVertical = animalState.value.verticalShift
            val desloHorizontal = animalState.value.horizontalShift
            val taxaDepreciacao = economyState.value.depreciationRate
            val umidadeRelativa = weatherAndSoilState.value.relativeHumidity
            val doseN = weatherAndSoilState.value.nDosage
            val area = areaState.value.area
            val investimento = economyState.value.investmentsPerLiters
            val rendaFamiliar = economyState.value.familyIncome
            val numeroPiquetes = areaState.value.picketsNumber
            val vacasLactacao = animalState.value.lactatingCows
            val aguaUsos = weatherAndSoilState.value.otherAndWater
            val aguaDisponivelPorIrrigacao = weatherAndSoilState.value.waterAvailableToIrrigation

            println("*** temperaturaMaxima $temperaturaMaxima")
            println("*** temperaturaMinima $temperaturaMinima")
            println("*** velocidadeVento $velocidadeVento")
            println("*** precipitacao $precipitacao")
            println("*** pesoCorporal $pesoCorporal")
            println("*** producaoLeite $producaoLeite")
            println("*** teorPB $teorPB")
            println("*** teorGordura $teorGordura")
            println("*** desloVertical $desloVertical")
            println("*** desloHorizontal $desloHorizontal")
            println("*** taxaDepreciacao $taxaDepreciacao")
            println("*** umidadeRelativa $umidadeRelativa")
            println("*** doseN $doseN")
            println("*** area $area")
            println("*** investimento $investimento")
            println("*** rendaFamiliar $rendaFamiliar")
            println("*** numeroPiquetes $numeroPiquetes")
            println("*** vacasLactacao $vacasLactacao")
            println("*** aguaUsos $aguaUsos")
            println("*** aguaDisponivelPorIrrigacao $aguaDisponivelPorIrrigacao")

            // ETo (mm)
            val ETo = (((24.211 * temperaturaMaxima - 635.46) / 30.4) +
                    ((53.984 * velocidadeVento + 10.898) / 30.4)) / 2

            // Irrigação (mm)
            val irrigacao = ETo - precipitacao

            // Água aplicada (mm/dia)
            val aguaAplicada = precipitacao + if (aguaDisponivelPorIrrigacao >= irrigacao) {
                irrigacao
            } else {
                aguaDisponivelPorIrrigacao
            }

            // Consumo (Kg MS/dia)
            val consumo =
                -4.69 + (0.0142 * pesoCorporal) + (0.356 * producaoLeite) + (1.72 * teorGordura)

            // Consumo de NDT
            val consumoNDT = ((((48.6 - (0.0183 * pesoCorporal)) + (0.435 * producaoLeite)
                    + (0.728 * teorGordura) + (3.46 * teorPB)) * 1.04) / 100) * consumo

            // NDT DV (Deslocamento Vertical)
            val NDTdv = if (desloVertical > 0) {
                (0.00669 * pesoCorporal * (desloVertical / 1000)) * 0.43
            } else {
                0.0
            }

            // NDT DH=
            val NDTdh = (0.00048 * pesoCorporal * (desloHorizontal / 1000)) * 0.43

            // NDT deslocamento
            val NDTdeslocamento = NDTdh + NDTdv

            // Consumo total (Kg MS/dia)
            val consTotal = consumo + ((NDTdeslocamento / consumoNDT) * consumo)

            // Tensão da água no solo (bar)
            val tenAguaSolo = 0.0368068 + (-1.06252 / aguaAplicada)

            // Depreciação (R$/L)
            val depreciacao = (investimento * (taxaDepreciacao / 365))

            // ITU
            val ITU = (0.8 * (temperaturaMaxima + temperaturaMinima)) / 2 +
                    (umidadeRelativa / 100) * ((temperaturaMaxima + temperaturaMinima) / 2 - 14.4) + 46.4

            // DPL
            val DPL = -1.075 - (1.736 * producaoLeite) + (0.02474 * producaoLeite * ITU)

            // Produção de forragem
            val prodForragem = (1.36722 + (-0.284546 * tenAguaSolo) +
                    (-2.13514 * tenAguaSolo.pow(2))) * doseN

            // Forragem Disponível
            val forrDisponivel = ((prodForragem * 10000) * (area / numeroPiquetes)) * 0.2

            // Suplementação (kg MS/dia)
            val suplementacao = producaoLeite / 2.5

            // Capacidade de suporte (animais)
            val capaSuporte = (forrDisponivel * 0.95) / (consTotal - suplementacao)

            // DPL anual
            val DPLAnual = (DPL * capaSuporte) * 365

            // Produção diária
            val prodDiaria = producaoLeite * (capaSuporte * (vacasLactacao / 100))

            // COE R$/L
            val COE = 4.52816 + (-0.000142 * prodDiaria) +
                    (0.00000000767199 * prodDiaria.pow(2)) +
                    (-0.24042 * producaoLeite) +
                    (0.004937 * producaoLeite.pow(2))

            // Produção de leite (L/ha/ano)
            val prodLeiteAno = (prodDiaria * 365) / area

            // Produção de leite (L/ha/dia)
            val prodLeiteDia = prodLeiteAno / 365

            // MDO familiar
            val mdoFamiliar = rendaFamiliar / (prodDiaria * 30.4)

            // Pegada hídrica
            val pegadaHidrica = (((aguaAplicada * 10000) * area) + (aguaUsos / 30.4)) / prodDiaria

            // Investimento total
            val investimentoTotal = investimento * prodDiaria

            // COT
            val COT = COE + mdoFamiliar + depreciacao

            // Participação da irrigação na água
            val partIrrAgua = (irrigacao / aguaAplicada) * 100

            // Preço do leite
            val precoLeite = (0.631922 * prodDiaria.pow(0.102383)) +
                    (-0.0132 * teorGordura.pow(2) + 0.1384 * teorGordura - 0.3089)

            // Receita total (R$/mês)
            val receitaTotalMes = (prodDiaria * precoLeite) * 30.4

            // ML (R$/L)
            val ML = precoLeite - COT

            // ML Anual
            val MLAnual = ML * prodDiaria * 365

            // Payback (Anos)
            val payback = investimentoTotal / MLAnual

            // Perda de receita com estresse
            val perdaReceitaEstresse = DPLAnual * precoLeite

            // Taxa de lotação
            val taxaLotacao = capaSuporte / area

            // TRCI
            val TRCI = (ML * 365) / investimento * 100

            // Receita por área
            val recArea = (receitaTotalMes * 12) / area

            // COE Total
            val COETotal = COE * prodDiaria * 365

            // Receita total (R$/ano)
            val receitaTotalAno = receitaTotalMes * 12

            // ML por área
            val MLArea = MLAnual / area

            // TODO: Change to method
            val newSoilWaterPlantAnimal = SoilWaterPlantAnimalState(
                tenAguaSolo = tenAguaSolo,
                prodForragem = prodForragem,
                capaSuporte = capaSuporte,
                taxaLotacao = taxaLotacao,
                itu = ITU,
                dpl = DPL,
                pegadaHidrica = pegadaHidrica
            )
            updateSoilWaterPlantAnimalState(newSoilWaterPlantAnimal)

            val newSystemCostsResultEconomic = SystemCostsResultEconomicState(
                prodDiaria = prodDiaria,
                prodLeiteDia = prodLeiteDia,
                prodLeiteAno = prodLeiteAno,
                perdReceitaEstresse = perdaReceitaEstresse,
                coe = COE,
                cot = COT,
                receitaTotalAno = receitaTotalAno,
                mlArea = MLArea,
                trci = TRCI,
                payback = payback
            )
            updateSystemCostsResultEconomicState(newSystemCostsResultEconomic)

            println("*** newSystemCostsResultEconomic $newSystemCostsResultEconomic")
            println("*** newSoilWaterPlantAnimal $newSoilWaterPlantAnimal")
        }
    }
}