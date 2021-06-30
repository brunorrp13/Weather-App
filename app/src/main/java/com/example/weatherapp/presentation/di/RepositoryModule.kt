package com.example.weatherapp.presentation.di

import com.example.weatherapp.data.repository.WeatherForecastRepositoryImpl
import com.example.weatherapp.data.repository.datasource.WeatherForecastRemoteDataSource
import com.example.weatherapp.domain.repository.WeatherForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        weatherForecastRemoteDataSource: WeatherForecastRemoteDataSource
    ):WeatherForecastRepository{
        return WeatherForecastRepositoryImpl(weatherForecastRemoteDataSource)
    }

}














