package com.example.vinod.doggeneratorapp.model

import com.google.gson.annotations.SerializedName

data class DataResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	var isError: Boolean? = false,
	var isApiSuccessful: Boolean? = false
)