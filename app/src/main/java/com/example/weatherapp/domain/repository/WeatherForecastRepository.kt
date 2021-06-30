package com.example.weatherapp.domain.repository

import com.anushka.newsapiclient.data.util.Resource
import com.example.weatherapp.data.model.APIResponse

interface WeatherForecastRepository {

    suspend fun getWeatherForecastByCity(city: String): Resource<APIResponse>

}