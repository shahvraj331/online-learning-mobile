package com.example.onlinelearning.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.onlinelearning.R
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.common.getSpannable
import com.example.onlinelearning.common.hideKeyboard
import com.example.onlinelearning.common.updateTransformationMethod
import com.example.onlinelearning.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            baseScreen.setOnClickListener {
                hideKeyboard()
            }

            customToolbar.apply {
                toolbarTitle.text = getString(R.string.sign_in_title)
                btnBack.setOnClickListener {
                    finish()
                }
            }

            imgPasswordToggle.setOnClickListener {
                etPassword.updateTransformationMethod(it)
            }

            btnForgotPassword.setOnClickListener {
                startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
            }

            val signUpText = tvSignUp.text.toString()
            val spanColor = ContextCompat.getColor(this@LoginActivity, R.color.appBaseColor)
            val customSpannable = getSpannable(signUpText, signUpText.length - Constants.SEVEN, signUpText.length, spanColor) {
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
                finish()
            }
            tvSignUp.text = customSpannable
            tvSignUp.movementMethod = LinkMovementMethod.getInstance()

            btnSignIn.setOnClickListener {
                val name = etName.text.toString().trim()
                val password = etPassword.text.toString().trim()
                if (isValidCredentials(name, password)) {
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun isValidCredentials(name: String, password: String): Boolean {
        return if (name.isEmpty()) {
            Toast.makeText(this@LoginActivity, getString(R.string.name_error), Toast.LENGTH_SHORT).show()
            false
        } else if (password.isEmpty() || password.length < 4) {
            Toast.makeText(this@LoginActivity, getString(R.string.password_error), Toast.LENGTH_SHORT).show()
            false
        } else true
    }
}