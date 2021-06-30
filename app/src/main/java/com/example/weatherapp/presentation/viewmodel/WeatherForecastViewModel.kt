package com.example.weatherapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anushka.newsapiclient.data.util.Resource
import com.example.weatherapp.data.model.APIResponse
import com.example.weatherapp.domain.usecase.GetWeatherForecastByCityUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class WeatherForecastViewModel(
    private val app: Application,
    private val getWeatherForecastByCityUseCase: GetWeatherForecastByCityUseCase
): AndroidViewModel(app) {

    val weatherForecast : MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getWeatherForecast(city: String) = viewModelScope.launch(Dispatchers.IO) {
        weatherForecast.postValue(Resource.Loading())
        try{
            if(isNetworkAvailable(app)) {

                val apiResult = getWeatherForecastByCityUseCase.execute(city)
                weatherForecast.postValue(apiResult)
            }else{
                weatherForecast.postValue(Resource.Error("Internet is not available"))
            }

        }catch (e: Exception){
            weatherForecast.postValue(Resource.Error(e.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

}