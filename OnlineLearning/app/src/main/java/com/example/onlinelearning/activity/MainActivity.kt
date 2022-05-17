package com.example.onlinelearning.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.common.SharedPreference

class MainActivity : AppCompatActivity() {

    private lateinit var tutorialSharedPreferences: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tutorialSharedPreferences = SharedPreference(getSharedPreferences(Constants.SHARED_PREFERENCE_KEY, MODE_PRIVATE))
        startActivity(
            Intent(
                this@MainActivity,
                if (tutorialSharedPreferences.isTutorialCompleted()) AuthenticationActivity::class.java else TutorialActivity::class.java
            )
        )
        finish()
    }
}