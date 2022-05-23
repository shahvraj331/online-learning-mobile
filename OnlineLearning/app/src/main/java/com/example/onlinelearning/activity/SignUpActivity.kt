package com.example.onlinelearning.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.onlinelearning.R
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.common.getSpannable
import com.example.onlinelearning.common.hideKeyboard
import com.example.onlinelearning.common.updateTransformationMethod
import com.example.onlinelearning.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            baseScreen.setOnClickListener {
                hideKeyboard()
            }

            customToolbar.apply {
                toolbarTitle.text = getString(R.string.sign_up_title)
                btnBack.setOnClickListener {
                    finish()
                }
            }

            imgPasswordToggle.setOnClickListener {
                etPassword.updateTransformationMethod(it)
            }

            val signUpText = tvSignUp.text.toString()
            val spanColor = ContextCompat.getColor(this@SignUpActivity, R.color.appBaseColor)
            val customSpannable = getSpannable(signUpText, signUpText.length - Constants.SEVEN, signUpText.length, spanColor) {
                startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                finish()
            }
            tvSignUp.text = customSpannable
            tvSignUp.movementMethod = LinkMovementMethod.getInstance()

            btnSignUp.setOnClickListener {
                val name = etName.text.toString().trim()
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()
                if (isValidCredentials(name, email, password)) {
                    startActivity(Intent(this@SignUpActivity, HomeActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun isValidCredentials(name: String, email: String, password: String): Boolean {
        return if (name.isEmpty()) {
            Toast.makeText(this@SignUpActivity, getString(R.string.name_error), Toast.LENGTH_SHORT).show()
            false
        } else if (password.isEmpty() || password.length < 4) {
            Toast.makeText(this@SignUpActivity, getString(R.string.password_error), Toast.LENGTH_SHORT).show()
            false
        } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this@SignUpActivity, getString(R.string.email_error), Toast.LENGTH_SHORT).show()
            false
        } else true
    }
}