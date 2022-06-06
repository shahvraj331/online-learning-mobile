package com.example.onlinelearning.activity

import android.os.Bundle
import com.example.onlinelearning.base_class.BaseActivity
import com.example.onlinelearning.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : BaseActivity() {

    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnSignIn.setOnClickListener {
                startNewActivity(LoginActivity())
            }

            btnSignUp.setOnClickListener {
                startNewActivity(SignUpActivity())
            }
        }
    }
}