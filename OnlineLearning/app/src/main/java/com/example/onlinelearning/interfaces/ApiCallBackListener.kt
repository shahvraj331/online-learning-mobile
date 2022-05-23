package com.example.onlinelearning.interfaces

import com.example.onlinelearning.model.ErrorResponse

interface ApiCallBackListener {
    fun<T: Any> onSuccess(data: T)
    fun onFailure(error: ErrorResponse)
}