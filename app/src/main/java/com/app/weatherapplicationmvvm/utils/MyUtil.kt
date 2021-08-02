package com.app.weatherapplicationmvvm.utils

import java.text.SimpleDateFormat
import java.util.*



fun calculatCelcius(temp: Double): CharSequence? {
    val celcius = temp - 273
    return String.format("%.1f", celcius)
}


 fun getDateTime(s: Long, format: String): String? {
    try {
        val updatedate = Date(s * 1000)
        val fmt = SimpleDateFormat(format)
        return fmt.format(updatedate)
    } catch (e: Exception) {
        return e.toString()
    }
}
