package com.example.onlinelearning.model

import com.google.gson.annotations.SerializedName

data class SignUpDataResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("token")
    val token: String
)