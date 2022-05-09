package com.example.onlinelearning.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.onlinelearning.R
import com.example.onlinelearning.common.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            delay(Constants.THREE_THOUSAND.toLong())
            startActivity(Intent(this@MainActivity, TutorialActivity::class.java))
            finish()
        }
    }
}