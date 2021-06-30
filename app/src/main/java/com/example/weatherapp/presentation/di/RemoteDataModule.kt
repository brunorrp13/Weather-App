package com.example.weatherapp.presentation.di

import com.example.weatherapp.data.api.WeatherForecastAPIService
import com.example.weatherapp.data.repository.datasource.WeatherForecastRemoteDataSource
import com.example.weatherapp.data.repository.datasourceimpl.WeatherForecastRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        weatherForecastAPIService: WeatherForecastAPIService
    ):WeatherForecastRemoteDataSource{
       return WeatherForecastRemoteDataSourceImpl(weatherForecastAPIService)
    }

}












