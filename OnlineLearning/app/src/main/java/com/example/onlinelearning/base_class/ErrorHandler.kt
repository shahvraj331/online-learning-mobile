package com.example.onlinelearning.base_class

import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject

class ErrorHandler(val activity: BaseActivity): Interceptor, BaseActivity() {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
            .newBuilder()
            .header("Content-Type", "application/json")
            .build()
        runOnUiThread {
            when (response.code) {
                400 -> {
                    try {
                        response.body?.let { it ->
                            val error = it.byteStream().bufferedReader().use { response ->
                                response.readLine()
                            }
                            val jsonObject = JSONObject(error)
                            activity.showDialog(jsonObject.getString("error"))
                        }
                    } catch (exception: Exception) {
                        activity.showDialog(exception.toString())
                    }
                }
            }
        }
        return response
    }
}