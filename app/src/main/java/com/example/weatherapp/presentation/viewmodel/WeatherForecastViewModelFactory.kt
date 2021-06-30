package com.example.weatherapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.domain.usecase.GetWeatherForecastByCityUseCase

class WeatherForecastViewModelFactory(
    private val app: Application,
    private val getWeatherForecastByCityUseCase: GetWeatherForecastByCityUseCase
   ): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherForecastViewModel(
            app,
            getWeatherForecastByCityUseCase
        ) as T
    }
}

