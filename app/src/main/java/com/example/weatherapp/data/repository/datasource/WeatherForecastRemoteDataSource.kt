package com.example.weatherapp.data.repository.datasource

import com.example.weatherapp.data.model.APIResponse
import retrofit2.Response

interface WeatherForecastRemoteDataSource {

    suspend fun getWeatherForecastByCity(city : String): Response<APIResponse>
}