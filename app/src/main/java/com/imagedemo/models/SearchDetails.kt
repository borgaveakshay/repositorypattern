package com.imagedemo.models

import com.google.gson.annotations.SerializedName


data class SearchDetails(

	@field:SerializedName("stat")
	val stat: String? = null,

	@field:SerializedName("count")
	val count: Count? = null,

	@field:SerializedName("nextphoto")
	val nextphoto: Nextphoto? = null,

	@field:SerializedName("prevphoto")
	val prevphoto: Prevphoto? = null
)