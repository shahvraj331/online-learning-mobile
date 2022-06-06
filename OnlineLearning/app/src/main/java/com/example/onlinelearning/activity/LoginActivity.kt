package com.example.onlinelearning.activity

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.onlinelearning.R
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.common.getSpannable
import com.example.onlinelearning.common.hideKeyboard
import com.example.onlinelearning.common.startNewActivity
import com.example.onlinelearning.common.toastMessage
import com.example.onlinelearning.common.trimmedText
import com.example.onlinelearning.common.updateTransformationMethod
import com.example.onlinelearning.common.verifyNonEmpty
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
                startNewActivity(ForgotPasswordActivity())
            }

            val signUpText = tvSignUp.text.toString()
            val spanColor = ContextCompat.getColor(this@LoginActivity, R.color.appBaseColor)
            val customSpannable = getSpannable(signUpText, signUpText.length - Constants.SEVEN, signUpText.length, spanColor) {
                startNewActivity(SignUpActivity())
                finish()
            }
            tvSignUp.text = customSpannable
            tvSignUp.movementMethod = LinkMovementMethod.getInstance()

            btnSignIn.setOnClickListener {
                if (isValidCredentials()) {
                    //TODO: Start home screen
                }
            }
        }
    }

    private fun isValidCredentials(): Boolean {
        return when {
            !verifyNonEmpty(binding.etName, binding.etPassword) -> {
                toastMessage(getString(R.string.all_fields_required))
                false
            }
            binding.etPassword.trimmedText().length < Constants.FOUR -> {
                toastMessage(getString(R.string.password_error))
                false
            }
            else -> true
        }
    }
}