package com.imagedemo.models

import com.google.gson.annotations.SerializedName


data class Photos(

	@field:SerializedName("perpage")
	val perpage: Int? = null,

	@field:SerializedName("total")
	val total: String? = null,

	@field:SerializedName("pages")
	val pages: String? = null,

	@field:SerializedName("photo")
	val photo: List<PhotoItem>? = null,

	@field:SerializedName("page")
	val page: Int? = null
)