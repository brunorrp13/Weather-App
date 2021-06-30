package com.example.weatherapp.data.repository

import com.anushka.newsapiclient.data.util.Resource
import com.example.weatherapp.data.model.APIResponse
import com.example.weatherapp.data.repository.datasource.WeatherForecastRemoteDataSource
import com.example.weatherapp.domain.repository.WeatherForecastRepository
import retrofit2.Response

class WeatherForecastRepositoryImpl (
    private val weatherForecastRemoteDataSource: WeatherForecastRemoteDataSource
        ): WeatherForecastRepository {
    override suspend fun getWeatherForecastByCity(city: String): Resource<APIResponse> {
       return responseToResource(weatherForecastRemoteDataSource.getWeatherForecastByCity(city))
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let {result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

}