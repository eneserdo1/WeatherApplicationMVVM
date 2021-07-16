package com.app.weatherapplicationmvvm.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.weatherapplicationmvvm.data.Repository
import com.app.weatherapplicationmvvm.ui.MainActivity.MainViewModel

class ViewModelFactory(private val repository:Repository):ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        /*if (modelClass.isAssignableFrom(SeriesNetworkCallsViewModel::class.java)) {
            return SeriesNetworkCallsViewModel(apiHelper, dbHelper) as T
        }*/

        throw IllegalArgumentException("Unknown class name")
    }
}