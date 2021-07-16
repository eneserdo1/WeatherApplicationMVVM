package com.app.weatherapplicationmvvm.ui.MainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.weatherapplicationmvvm.data.Repository
import com.app.weatherapplicationmvvm.data.model.WeatherResponse
import com.app.weatherapplicationmvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository:Repository) : ViewModel() {

    private val weathers = MutableLiveData<Resource<WeatherResponse>>()

    init {
        fetchWeather()
    }

    private fun fetchWeather() {
        var query = HashMap<String,String>()
        query.put("lat","37.339856")
        query.put("lon","28.156679")
        query.put("exclude","daily")

        viewModelScope.launch {
            weathers.postValue(Resource.loading(null))
            repository.fetchWeatherData(query)
                .catch {
                    weathers.postValue(Resource.error(it.toString(),null))
                }
                .collect {
                    weathers.postValue(Resource.success(it))
                }

        }
    }

    fun getWeather(): LiveData<Resource<WeatherResponse>>{
        return weathers
    }
}