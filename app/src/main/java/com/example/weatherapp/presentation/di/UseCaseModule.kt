package com.example.weatherapp.presentation.di

import com.example.weatherapp.domain.repository.WeatherForecastRepository
import com.example.weatherapp.domain.usecase.GetWeatherForecastByCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
   @Singleton
   @Provides
   fun provideGetWeatherForecastByCityUseCase(
       weatherForecastRepository: WeatherForecastRepository
   ): GetWeatherForecastByCityUseCase {
      return provideGetWeatherForecastByCityUseCase(weatherForecastRepository)
   }

}


















