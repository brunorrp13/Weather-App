package com.example.weatherapp.data.repository.datasourceimpl

import com.example.weatherapp.data.api.WeatherForecastAPIService
import com.example.weatherapp.data.model.APIResponse
import com.example.weatherapp.data.repository.datasource.WeatherForecastRemoteDataSource
import retrofit2.Response

class WeatherForecastRemoteDataSourceImpl (
       private val weatherForecastAPIService: WeatherForecastAPIService
        ): WeatherForecastRemoteDataSource {
    override suspend fun getWeatherForecastByCity(city: String): Response<APIResponse> {
        return weatherForecastAPIService.getWeatherForecastByCity(city)
    }
}