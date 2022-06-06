package com.example.onlinelearning.activity

import android.os.Bundle
import com.example.onlinelearning.R
import com.example.onlinelearning.base_class.BaseActivity
import com.example.onlinelearning.common.verifyEmailAddress
import com.example.onlinelearning.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : BaseActivity() {

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
                if (etEmail.verifyEmailAddress()) {
                    startNewActivity(VerificationActivity())
                } else {
                    toastMessage(getString(R.string.email_error))
                }
            }
        }
    }
}