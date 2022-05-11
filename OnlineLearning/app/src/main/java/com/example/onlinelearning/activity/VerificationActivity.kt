package com.example.onlinelearning.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.onlinelearning.R
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.common.OTPTextWatcher
import com.example.onlinelearning.common.getSpannable
import com.example.onlinelearning.common.hideKeyboard
import com.example.onlinelearning.databinding.ActivityVerificationBinding

class VerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        with(binding) {
            baseScreen.setOnClickListener {
                hideKeyboard()
            }

            customToolbar.apply {
                toolbarTitle.text = getString(R.string.verification_title)
                btnBack.setOnClickListener {
                    finish()
                }
            }

            val resendCodeText = binding.tvResendCode.text.toString().trim()
            val spanColor = ContextCompat.getColor(this@VerificationActivity, R.color.appBaseColor)
            val customSpannable = getSpannable(resendCodeText,resendCodeText.length - Constants.ELEVEN, resendCodeText.length, spanColor) {}
            binding.tvResendCode.text = customSpannable

            etFirstNumber.addTextChangedListener(setUpTextWatcher(etFirstNumber))
            etSecondNumber.addTextChangedListener(setUpTextWatcher(etSecondNumber))
            etThirdNumber.addTextChangedListener(setUpTextWatcher(etThirdNumber))
            etFourthNumber.addTextChangedListener(setUpTextWatcher(etFourthNumber))
            etFifthNumber.addTextChangedListener(setUpTextWatcher(etFifthNumber))

            btnConfirm.setOnClickListener {
                if (validateOTP()) {
                    startActivity(Intent(this@VerificationActivity, ResetPasswordActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun validateOTP(): Boolean {
        with(binding) {
            return when {
                etFirstNumber.text.toString().isEmpty() -> false
                etSecondNumber.text.toString().isEmpty() -> false
                etThirdNumber.text.toString().isEmpty() -> false
                etFourthNumber.text.toString().isEmpty() -> false
                etFifthNumber.text.toString().isEmpty() -> false
                else -> true
            }
        }
    }

    private fun setUpTextWatcher(currentEditText: EditText): OTPTextWatcher {
        with(binding) {
            return OTPTextWatcher { textFlag ->
                when (currentEditText) {
                    etFirstNumber -> {
                        when (textFlag) {
                            1 -> {
                                etSecondNumber.requestFocus()
                            }
                        }
                    }
                    etSecondNumber -> {
                        when (textFlag) {
                            0 -> {
                                etFirstNumber.requestFocus()
                            }
                            1 -> {
                                etThirdNumber.requestFocus()
                            }
                        }
                    }
                    etThirdNumber -> {
                        when (textFlag) {
                            0 -> {
                                etSecondNumber.requestFocus()
                            }
                            1 -> {
                                etFourthNumber.requestFocus()
                            }
                        }
                    }
                    etFourthNumber -> {
                        when (textFlag) {
                            0 -> {
                                etThirdNumber.requestFocus()
                            }
                            1 -> {
                                etFifthNumber.requestFocus()
                            }
                        }
                    }
                    etFifthNumber -> {
                        when (textFlag) {
                            0 -> {
                                etFourthNumber.requestFocus()
                            }
                            1 -> {
                                hideKeyboard()
                            }
                        }
                    }
                }
            }
        }
    }
}