package com.imagedemo.models

import com.google.gson.annotations.SerializedName


data class SearchResponse(

	@field:SerializedName("stat")
	val stat: String? = null,

	@field:SerializedName("photos")
	val photos: Photos? = null
)