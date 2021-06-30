package com.example.weatherapp.presentation.di

import android.app.Application
import com.example.weatherapp.domain.usecase.GetWeatherForecastByCityUseCase
import com.example.weatherapp.presentation.viewmodel.WeatherForecastViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
  fun provideWeatherForecastViewModelFactory(
        application: Application,
        getWeatherForecastByCityUseCase: GetWeatherForecastByCityUseCase
  ): WeatherForecastViewModelFactory {
      return WeatherForecastViewModelFactory(
          application,
          getWeatherForecastByCityUseCase
      )
  }

}








