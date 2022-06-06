package com.example.onlinelearning.view_model

import androidx.lifecycle.MutableLiveData
import com.example.onlinelearning.base_class.BaseActivity
import com.example.onlinelearning.base_class.BaseViewModel
import com.example.onlinelearning.interfaces.ApiCallBackListener
import com.example.onlinelearning.interfaces.ApiInterface
import com.example.onlinelearning.model.ErrorResponse
import com.example.onlinelearning.model.SignUpResponse
import com.example.onlinelearning.model.UserModel

class SignUpViewModel: BaseViewModel() {

    val signUpResponse: MutableLiveData<SignUpResponse> = MutableLiveData()
    val errorResponse: MutableLiveData<String> = MutableLiveData()
    val signUpSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun createUser(email: String, password: String, activity: BaseActivity) {
        val retrofit = ApiInterface.getRetrofitObject(activity).create(ApiInterface::class.java).signUpUser(UserModel(email, password))
        call(retrofit, object : ApiCallBackListener {
            override fun <T : Any> onSuccess(data: T) {
                signUpResponse.value = data as SignUpResponse
                signUpSuccess.value = true
            }

            override fun onFailure(error: ErrorResponse) {
                errorResponse.value = error.error
                signUpSuccess.value = false
            }
        })
    }
}