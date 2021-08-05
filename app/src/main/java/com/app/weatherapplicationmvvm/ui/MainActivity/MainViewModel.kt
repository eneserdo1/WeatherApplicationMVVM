package com.app.weatherapplicationmvvm.ui.MainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.weatherapplicationmvvm.data.Repository
import com.app.weatherapplicationmvvm.model.WeatherResponse
import com.app.weatherapplicationmvvm.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository:Repository) : ViewModel() {

    private val _weathers = MutableLiveData<Resource<WeatherResponse>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchWeather()
    }

    fun fetchWeather() {
        var query = HashMap<String,String>()
        query.put("lat","37.339856")
        query.put("lon","28.156679")
        query.put("exclude","daily")
        query.put("appid","393a58535012ef9a818cf62884e2317a")
        compositeDisposable.add(
            repository.fetchWeatherData(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{response->
                    _weathers.postValue(response)
                }

        )

        /*
        viewModelScope.launch {
            weathers.postValue(Resource.loading(null))
            repository.fetchWeatherData(query)
                .catch {
                    weathers.postValue(Resource.error(it.toString(),null))
                }
                .collect {
                    weathers.postValue(Resource.success(it))
                }

        }*/
    }

    fun getWeather(): LiveData<Resource<WeatherResponse>>{
        return _weathers
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}