package com.app.weatherapplicationmvvm.data.model

import com.google.gson.annotations.SerializedName

data class Minutely (

	@SerializedName("dt") val dt : Long,
	@SerializedName("precipitation") val precipitation : Int
)