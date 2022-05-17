package com.example.onlinelearning.common

import android.content.SharedPreferences

class SharedPreference(private val sharedPreferences: SharedPreferences) {
    private val editor = sharedPreferences.edit()

    fun isTutorialCompleted(): Boolean {
        return sharedPreferences.getBoolean(Constants.TUTORIAL_COMPLETION_KEY, false)
    }

    fun setTutorialCompleted() {
        editor.putBoolean(Constants.TUTORIAL_COMPLETION_KEY, true)
        editor.apply()
    }
}