package com.app.weatherapplicationmvvm.utils

import com.app.weatherapplicationmvvm.model.WeatherResponse
import com.app.weatherapplicationmvvm.network.ApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Constants {

    companion object{

        const val API_KEY = "393a58535012ef9a818cf62884e2317a"

        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        const val IMAGE_URL = "https://openweathermap.org/img/wn/"

    }
}
