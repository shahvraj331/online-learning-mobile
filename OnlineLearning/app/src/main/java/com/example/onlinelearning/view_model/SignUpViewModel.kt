package com.example.onlinelearning.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.onlinelearning.base_class.BaseViewModel
import com.example.onlinelearning.interfaces.ApiCallBackInterface
import com.example.onlinelearning.model.ApiErrorResponse
import com.example.onlinelearning.model.SignUpDataResponse
import org.json.JSONObject
import java.net.HttpURLConnection

class SignUpViewModel: BaseViewModel() {

    val signUpDataResponse: MutableLiveData<SignUpDataResponse> = MutableLiveData()
    val errorResponse: MutableLiveData<ApiErrorResponse> = MutableLiveData()
    val signUpSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun createUser(url: String, body: JSONObject) {
        call(url, "POST", body, HttpURLConnection.HTTP_OK, SignUpDataResponse::class.java, object : ApiCallBackInterface{
            override fun <T : Any> onSuccess(data: T) {
                signUpDataResponse.postValue(data as SignUpDataResponse)
                Log.d("response", data.toString())
                signUpSuccess.postValue(true)
            }

            override fun onFailure(error: ApiErrorResponse) {
                Log.d("response", error.toString())
                errorResponse.postValue(error)
                signUpSuccess.postValue(false)
            }
        })
    }
}