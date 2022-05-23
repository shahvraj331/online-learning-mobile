package com.example.onlinelearning.model

import com.google.gson.annotations.SerializedName

data class ApiErrorResponse(
    @SerializedName("error")
    val error: String
)
