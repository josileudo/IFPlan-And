package com.example.ifplan_leite.data.di

import android.app.Application
import android.content.Context
import com.example.ifplan_leite.data.dao.AnimalDao
import com.example.ifplan_leite.data.database.IFPlanDatabase
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
}