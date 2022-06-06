package com.example.onlinelearning.interfaces

import com.example.onlinelearning.base_class.BaseActivity
import com.example.onlinelearning.base_class.ErrorHandler
import com.example.onlinelearning.model.LoginResponse
import com.example.onlinelearning.model.SignUpResponse
import com.example.onlinelearning.model.UserModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("api/login")
    fun logInUser(@Body userModel: UserModel): Call<LoginResponse>

    @POST("api/register")
    fun signUpUser(@Body userModel: UserModel): Call<SignUpResponse>

    companion object {
        fun getRetrofitObject(activity: BaseActivity): Retrofit {
            val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(ErrorHandler(activity))
            val okHttpClient = okHttpClientBuilder.build()

            return Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}