package com.app.weatherapplicationmvvm.network

import com.app.weatherapplicationmvvm.model.WeatherResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("onecall")
    fun getWeatherData(@QueryMap parameter : Map<String,String>) : Single<WeatherResponse>

}