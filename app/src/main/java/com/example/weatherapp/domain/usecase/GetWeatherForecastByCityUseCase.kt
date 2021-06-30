package com.example.weatherapp.domain.usecase

import com.anushka.newsapiclient.data.util.Resource
import com.example.weatherapp.data.model.APIResponse
import com.example.weatherapp.domain.repository.WeatherForecastRepository

class GetWeatherForecastByCityUseCase(private val weatherForecastRepository: WeatherForecastRepository) {

        suspend fun execute(city: String): Resource<APIResponse> {
            return weatherForecastRepository.getWeatherForecastByCity(city)
        }

}