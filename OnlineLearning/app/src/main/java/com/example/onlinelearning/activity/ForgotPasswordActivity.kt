package com.example.onlinelearning.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.onlinelearning.R
import com.example.onlinelearning.common.hideKeyboard
import com.example.onlinelearning.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            baseScreen.setOnClickListener {
                hideKeyboard()
            }

            customToolbar.apply {
                toolbarTitle.text = getString(R.string.forgot_password_title)
                btnBack.setOnClickListener {
                    finish()
                }
            }

            btnSendCode.setOnClickListener {
            val email = etEmail.text.toString().trim()
                if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    startActivity(Intent(this@ForgotPasswordActivity, VerificationActivity::class.java))
                } else {
                    Toast.makeText(this@ForgotPasswordActivity, getString(R.string.email_error), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}