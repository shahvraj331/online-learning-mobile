package com.example.onlinelearning.activity

import android.os.Bundle
import com.example.onlinelearning.R
import com.example.onlinelearning.base_class.BaseActivity
import com.example.onlinelearning.common.updateTransformationMethod
import com.example.onlinelearning.common.verifyNonEmpty
import com.example.onlinelearning.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : BaseActivity() {

    private lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            baseScreen.setOnClickListener {
                hideKeyboard()
            }

            customToolbar.apply {
                toolbarTitle.text = getString(R.string.reset_password_title)
                btnBack.setOnClickListener {
                    finish()
                }
            }

            imgPasswordToggle.setOnClickListener {
                etPassword.updateTransformationMethod(it)
            }

            imgConfirmPasswordToggle.setOnClickListener {
                etConfirmPassword.updateTransformationMethod(it)
            }

            btnConfirm.setOnClickListener {
                if (verifyNonEmpty(etPassword, etConfirmPassword))
                    toastMessage(getString(R.string.reset_password_success))
            }
        }
    }
}