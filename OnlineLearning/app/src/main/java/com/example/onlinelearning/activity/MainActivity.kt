package com.example.onlinelearning.activity

import android.os.Bundle
import com.example.onlinelearning.base_class.BaseActivity
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.common.SharedPreference
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : BaseActivity() {

    private lateinit var tutorialSharedPreferences: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCenter.start(application, "6d5b1ad1-2ef4-48ab-bfa1-ce8610d2d52d", Analytics::class.java, Crashes::class.java)

        tutorialSharedPreferences = SharedPreference(getSharedPreferences(Constants.SHARED_PREFERENCE_KEY, MODE_PRIVATE))
        startNewActivity(if (tutorialSharedPreferences.isTutorialCompleted()) AuthenticationActivity() else TutorialActivity())
        finish()
    }
}