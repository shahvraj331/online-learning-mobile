package com.example.onlinelearning.view_model

import androidx.lifecycle.MutableLiveData
import com.example.onlinelearning.base_class.BaseActivity
import com.example.onlinelearning.base_class.BaseViewModel
import com.example.onlinelearning.interfaces.ApiCallBackListener
import com.example.onlinelearning.interfaces.ApiInterface
import com.example.onlinelearning.model.ErrorResponse
import com.example.onlinelearning.model.LoginResponse
import com.example.onlinelearning.model.UserModel

class LoginViewModel: BaseViewModel() {

    val loginDataResponse: MutableLiveData<LoginResponse> = MutableLiveData()
    val errorResponse: MutableLiveData<String> = MutableLiveData()
    val loginSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun loginUser(email: String, password: String, activity: BaseActivity) {
        val retrofit = ApiInterface.getRetrofitObject(activity).create(ApiInterface::class.java).logInUser(UserModel(email, password))
        call(retrofit, object : ApiCallBackListener {
            override fun <T : Any> onSuccess(data: T) {
                loginDataResponse.value = data as LoginResponse
                loginSuccess.value = true
            }

            override fun onFailure(error: ErrorResponse) {
                errorResponse.value = error.error
                loginSuccess.value = false
            }
        })
    }
}