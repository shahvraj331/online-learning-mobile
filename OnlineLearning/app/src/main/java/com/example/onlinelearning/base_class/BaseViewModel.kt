package com.example.onlinelearning.base_class

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinelearning.interfaces.ApiCallBackInterface
import com.example.onlinelearning.model.ApiErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

abstract class BaseViewModel: ViewModel() {

    fun <T : Any> call(
        url: String,
        method: String,
        requestBody: JSONObject? = null,
        requiredResponseCode: Int,
        responseDataClass: Class<T>,
        apiCallBackInterface: ApiCallBackInterface
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val requestUrl = URL(url)
            var responseData: String

            with(requestUrl.openConnection() as HttpURLConnection) {
                setRequestProperty("Content-type", "application/json")
                requestMethod = method
                requestBody?.let { body ->
                    outputStream.bufferedWriter().use {
                        it.write(body.toString())
                    }
                }

                when (responseCode) {
                    requiredResponseCode -> {
                        inputStream.bufferedReader().use {
                            responseData = it.readText()
                        }
                        apiCallBackInterface.onSuccess(Gson().fromJson(responseData, responseDataClass))
                    }
                    400 -> {
                        errorStream.bufferedReader().use {
                            responseData = it.readLine()
                        }
                        apiCallBackInterface.onFailure(Gson().fromJson(responseData, ApiErrorResponse::class.java))
                    }
                    else -> {
                        apiCallBackInterface.onFailure(ApiErrorResponse(responseCode.toString()))
                    }
                }
            }
        }
    }
}
