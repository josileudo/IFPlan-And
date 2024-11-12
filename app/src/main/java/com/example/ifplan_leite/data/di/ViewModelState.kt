package com.example.ifplan_leite.data.di

import com.example.ifplan_leite.data.repository.AnimalRepository
import com.example.ifplan_leite.data.repository.AreaRepository
import com.example.ifplan_leite.data.repository.EconomyRepository
import com.example.ifplan_leite.data.repository.WeatherAndSoilRepository
import com.example.ifplan_leite.data.state.AnimalState
import com.example.ifplan_leite.data.state.AreaState
import com.example.ifplan_leite.data.state.EconomyState
import com.example.ifplan_leite.data.state.WeatherAndSoilState
import com.example.ifplan_leite.view_model.AnimalViewModel
import com.example.ifplan_leite.view_model.AreaViewModel
import com.example.ifplan_leite.view_model.EconomyViewModel
import com.example.ifplan_leite.view_model.WeatherAndSoilViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.StateFlow


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelState {

    @Provides
    @ViewModelScoped
    fun provideAreaStateFlow(areaRepository: AreaRepository): StateFlow<AreaState> {
        return areaRepository.areaState
    }
    @Provides
    @ViewModelScoped
    fun provideAnimalState(animalRepository: AnimalRepository): StateFlow<AnimalState> {
        return animalRepository.animalState
    }

    @Provides
    @ViewModelScoped
    fun provideEconomyState(economyRepository: EconomyRepository): StateFlow<EconomyState> {
        return economyRepository.economyState
    }

    @Provides
    @ViewModelScoped
    fun provideWeatherAndSoilState(weatherAndSoilRepository: WeatherAndSoilRepository): StateFlow<WeatherAndSoilState> {
        return weatherAndSoilRepository.weatherAndSoilState
    }
}