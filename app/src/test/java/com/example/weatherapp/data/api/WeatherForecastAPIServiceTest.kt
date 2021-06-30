package com.example.weatherapp.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherForecastAPIServiceTest {

    private lateinit var service: WeatherForecastAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherForecastAPIService::class.java)
    }

    private fun enqueueMockResponse(
        fileName:String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }

    @Test
    fun getWeatherForecastByCity_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("weatherforecastresponse.json")
            val responseBody = service.getWeatherForecastByCity("Raleigh").body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2.0/forecast/daily?city=Raleigh&key=a6d01938deef4234bfd20cc096d145a9")
        }
    }

    @Test
    fun getWeatherForecastForReleigh_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("weatherforecastresponse.json")
            val responseBody = service.getWeatherForecastByCity("Raleigh").body()
            val cityName = responseBody!!.cityName
            assertThat(cityName).isEqualTo("Raleigh")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }


}