package com.example.weatherapp.presentation.di

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.api.WeatherForecastAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
         return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl(BuildConfig.BASE_URL)
             .build()
    }

    @Singleton
    @Provides
    fun provideweatherForecastAPIService(retrofit: Retrofit): WeatherForecastAPIService {
        return retrofit.create(WeatherForecastAPIService::class.java)
    }



}













