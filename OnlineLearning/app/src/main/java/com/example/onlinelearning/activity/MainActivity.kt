package com.example.onlinelearning.activity

import android.os.Bundle
import com.example.onlinelearning.base_class.BaseActivity
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.common.SharedPreference

class MainActivity : BaseActivity() {

    private lateinit var tutorialSharedPreferences: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tutorialSharedPreferences = SharedPreference(getSharedPreferences(Constants.SHARED_PREFERENCE_KEY, MODE_PRIVATE))
        startNewActivity(if (tutorialSharedPreferences.isTutorialCompleted()) AuthenticationActivity() else TutorialActivity())
        finish()
    }
}