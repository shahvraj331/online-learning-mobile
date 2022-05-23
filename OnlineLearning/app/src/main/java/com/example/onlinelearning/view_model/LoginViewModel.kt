package com.example.onlinelearning.view_model

import androidx.lifecycle.MutableLiveData
import com.example.onlinelearning.base_class.BaseViewModel
import com.example.onlinelearning.interfaces.ApiCallBackInterface
import com.example.onlinelearning.model.ApiErrorResponse
import com.example.onlinelearning.model.LoginResponse
import org.json.JSONObject
import java.net.HttpURLConnection

class LoginViewModel: BaseViewModel() {

    val loginDataResponse: MutableLiveData<LoginResponse> = MutableLiveData()
    val errorResponse: MutableLiveData<ApiErrorResponse> = MutableLiveData()
    val loginSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun loginUser(url: String, body: JSONObject) {
        call(url, "POST", body, HttpURLConnection.HTTP_OK, LoginResponse::class.java, object :
            ApiCallBackInterface {
            override fun <T : Any> onSuccess(data: T) {
                loginDataResponse.postValue(data as LoginResponse)
                loginSuccess.postValue(true)
            }

            override fun onFailure(error: ApiErrorResponse) {
                errorResponse.postValue(error)
                loginSuccess.postValue(false)
            }
        })
    }
}