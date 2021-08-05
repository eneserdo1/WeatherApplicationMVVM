package com.app.weatherapplicationmvvm.model

import com.google.gson.annotations.SerializedName


data class WeatherResponse (

	@SerializedName("lat") val lat : Double?=null,
	@SerializedName("lon") val lon : Double?=null,
	@SerializedName("timezone") val timezone : String?=null,
	@SerializedName("timezone_offset") val timezone_offset : Int?=null,
	@SerializedName("current") val current : Current?=null,
	@SerializedName("minutely") val minutely : List<Minutely>?=null,
	@SerializedName("hourly") val hourly : List<Hourly>?=null
)