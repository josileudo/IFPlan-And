package com.app.ifplan_leite.core.data.di

import android.content.Context
import com.app.ifplan_leite.core.data.dao.AnimalDao
import com.app.ifplan_leite.core.data.dao.AreaDao
import com.app.ifplan_leite.core.data.dao.EconomyDao
import com.app.ifplan_leite.core.data.dao.WeatherAndSoilDao
import com.app.ifplan_leite.core.data.database.IFPlanDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): IFPlanDatabase {
        return IFPlanDatabase.getDatabase(context)
    }

    @Provides
    fun provideAnimalDao(database: IFPlanDatabase): AnimalDao {
        return database.animalDao()
    }

    @Provides
    fun provideAreaDao(database: IFPlanDatabase): AreaDao {
        return database.areaDao()
    }

    @Provides
    fun provideEconomyDao(database: IFPlanDatabase): EconomyDao {
        return database.economyDao()
    }

    @Provides
    fun provideWeatherAndSoil(database: IFPlanDatabase): WeatherAndSoilDao {
        return database.weatherAndSoilDao()
    }
}