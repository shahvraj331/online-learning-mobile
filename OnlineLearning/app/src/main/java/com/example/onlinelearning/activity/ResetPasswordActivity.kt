package com.example.onlinelearning.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlinelearning.R
import com.example.onlinelearning.common.*
import com.example.onlinelearning.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {

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