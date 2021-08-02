package com.app.weatherapplicationmvvm.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.*
import javax.inject.Inject
import android.preference.PreferenceManager.getDefaultSharedPreferences

class PreferencesHelper @Inject constructor(context: Context) {

    private val mPreferences : SharedPreferences = getDefaultSharedPreferences(context)
    private val mEditör : SharedPreferences.Editor = mPreferences.edit()

    companion object{
        private var instance : PreferencesHelper?=null

        const val VALUE_CITY_LAT : String = "val.city.lat"
        const val VALUE_CITY_LON : String = "val.city.lon"
        const val VALUE_CITY_NAME : String = "val.city.name"
        const val VALUE_EXCLUDE : String = "val.exclude"

        fun getInstane(context: Context):PreferencesHelper{
            if (instance == null)
                instance = PreferencesHelper(context)

            return instance as PreferencesHelper
        }
    }

    var cityName : String
        get() = mPreferences.getString(VALUE_CITY_NAME,"")!!
        set(value){
            mEditör.putString(VALUE_CITY_NAME,value)
            mEditör.commit()
        }

    var cityLatitude : String
        get() = mPreferences.getString(VALUE_CITY_LAT,"")!!
        set(value){
            mEditör.putString(VALUE_CITY_LAT,value)
            mEditör.commit()
        }

    var cityLongitude : String
        get() = mPreferences.getString(VALUE_CITY_LON,"")!!
        set(value){
            mEditör.putString(VALUE_CITY_LON,value)
            mEditör.commit()
        }

    var selectedExclude : String
        get() = mPreferences.getString(VALUE_EXCLUDE,"")!!
        set(value){
            mEditör.putString(VALUE_EXCLUDE,value)
            mEditör.commit()
        }


    fun clearSharedPreferenes(){
        mEditör.clear()
        mEditör.commit()
    }
}