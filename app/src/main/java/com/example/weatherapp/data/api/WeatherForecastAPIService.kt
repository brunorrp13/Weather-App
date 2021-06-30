package com.example.weatherapp.data.api

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastAPIService {

    @GET("v2.0/forecast/daily")
    suspend fun getWeatherForecastByCity(
        @Query("city")
        city:String,
        @Query("key")
        apiKey:String = BuildConfig.API_KEY
    ): Response<APIResponse>

}