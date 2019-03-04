package com.imagedemo.models

import com.google.gson.annotations.SerializedName


data class Count(

	@field:SerializedName("_content")
	val content: Int? = null
)