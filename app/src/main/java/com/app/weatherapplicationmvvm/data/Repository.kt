package com.app.weatherapplicationmvvm.data

import android.content.res.Resources
import com.app.weatherapplicationmvvm.data.model.WeatherResponse
import com.app.weatherapplicationmvvm.data.remote.ApiService
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(val apiService: ApiService) {

    suspend fun fetchWeatherData(queryMap:HashMap<String,String>) = flow {
        emit(apiService.getWeatherData(queryMap))
    }

    
}