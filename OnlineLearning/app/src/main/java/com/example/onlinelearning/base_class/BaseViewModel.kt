package com.example.onlinelearning.base_class

import androidx.lifecycle.ViewModel
import com.example.onlinelearning.interfaces.ApiCallBackListener
import com.example.onlinelearning.model.ErrorResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseViewModel: ViewModel() {

    fun<T: Any> call(retrofitCall: Call<T>, apiCallBackListener: ApiCallBackListener) {
        retrofitCall.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        apiCallBackListener.onSuccess(it)
                    }
                } else {
                    apiCallBackListener.onFailure(ErrorResponse(response.code().toString()))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                apiCallBackListener.onFailure(ErrorResponse(t.toString()))
            }
        })
    }
}

