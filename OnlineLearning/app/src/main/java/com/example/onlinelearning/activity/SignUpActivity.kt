package com.example.onlinelearning.activity

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.onlinelearning.R
import com.example.onlinelearning.base_class.BaseActivity
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.common.getSpannable
import com.example.onlinelearning.common.trimmedText
import com.example.onlinelearning.common.updateTransformationMethod
import com.example.onlinelearning.common.verifyEmailAddress
import com.example.onlinelearning.common.verifyNonEmpty
import com.example.onlinelearning.databinding.ActivitySignUpBinding
import com.example.onlinelearning.view_model.SignUpViewModel

class SignUpActivity : BaseActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

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

            val signInText = tvSignIn.text.toString()
            val spanColor = ContextCompat.getColor(this@SignUpActivity, R.color.appBaseColor)
            val customSpannable = getSpannable(signInText, signInText.length - Constants.SEVEN, signInText.length, spanColor) {
                startNewActivity(LoginActivity())
                finish()
            }
            tvSignIn.text = customSpannable
            tvSignIn.movementMethod = LinkMovementMethod.getInstance()

            btnSignUp.setOnClickListener {
                if (isValidCredentials()) {
                    progressBar.visibility = View.VISIBLE
                    viewModel.createUser(etEmail.trimmedText(), etPassword.trimmedText(), this@SignUpActivity)
                }
            }

            viewModel.signUpSuccess.observe(this@SignUpActivity) {
                progressBar.visibility = View.GONE
                if (it) startNewActivity(HomeActivity())
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
            !binding.etEmail.verifyEmailAddress() -> {
                toastMessage(getString(R.string.email_error))
                false
            }
            else -> true
        }
    }
}