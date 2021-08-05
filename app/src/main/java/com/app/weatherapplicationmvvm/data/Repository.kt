package com.app.weatherapplicationmvvm.data

import com.app.weatherapplicationmvvm.model.WeatherResponse
import com.app.weatherapplicationmvvm.network.ApiService
import com.app.weatherapplicationmvvm.model.Resource
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val api:ApiService) {

    fun fetchWeatherData(queryMap: HashMap<String, String>) = Observable.create<Resource<WeatherResponse>> { emitter ->
        emitter.onNext(Resource.loading(null))
        api.getWeatherData(queryMap).subscribeOn(Schedulers.io()).subscribe(
            { response ->
                emitter.onNext(Resource.success(response))
                emitter.onComplete()
            },
            { error ->
                println("Repo error-- ${error.message.toString()}")
                emitter.onNext(Resource.error(error.message.toString(), null))
                emitter.onComplete()
            }
        )
    }


}