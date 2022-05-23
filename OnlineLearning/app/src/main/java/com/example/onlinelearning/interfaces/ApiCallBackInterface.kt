package com.example.onlinelearning.interfaces

import com.example.onlinelearning.model.ApiErrorResponse

interface ApiCallBackInterface {
    fun<T: Any> onSuccess(data: T)
    fun onFailure(error: ApiErrorResponse)
}