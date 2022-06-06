package com.example.onlinelearning.model

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("token")
    val token: String
)