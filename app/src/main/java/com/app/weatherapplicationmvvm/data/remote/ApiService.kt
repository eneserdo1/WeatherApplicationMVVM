package com.app.weatherapplicationmvvm.data.remote

import com.app.weatherapplicationmvvm.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("onecall")
    suspend fun getWeatherData(@QueryMap parameter : Map<String,String>) : WeatherResponse

}