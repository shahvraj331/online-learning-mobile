package com.example.onlinelearning.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinelearning.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnSignIn.setOnClickListener {
                startActivity(Intent(this@AuthenticationActivity, LoginActivity::class.java))
            }

            btnSignUp.setOnClickListener {
                startActivity(Intent(this@AuthenticationActivity, SignUpActivity::class.java))
            }
        }
    }
}