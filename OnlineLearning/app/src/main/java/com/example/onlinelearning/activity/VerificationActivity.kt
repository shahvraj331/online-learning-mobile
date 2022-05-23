package com.example.onlinelearning.activity

import android.os.Bundle
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.onlinelearning.R
import com.example.onlinelearning.base_class.BaseActivity
import com.example.onlinelearning.common.Constants
import com.example.onlinelearning.common.OTPTextWatcher
import com.example.onlinelearning.common.getSpannable
import com.example.onlinelearning.common.verifyNonEmpty
import com.example.onlinelearning.databinding.ActivityVerificationBinding

class VerificationActivity : BaseActivity() {

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
                    startNewActivity(ResetPasswordActivity())
                    finish()
                }
            }
        }
    }

    private fun validateOTP(): Boolean {
        with(binding) {
            return when {
                !verifyNonEmpty(etFirstNumber, etSecondNumber, etThirdNumber, etFourthNumber, etFifthNumber) -> false
                else -> true
            }
        }
    }

    private fun setUpTextWatcher(currentEditText: EditText): OTPTextWatcher {
        with(binding) {
            return OTPTextWatcher { textFlag ->
                when (currentEditText) {
                    etFirstNumber -> if (textFlag) etSecondNumber.requestFocus()
                    etSecondNumber -> if (textFlag) etThirdNumber.requestFocus() else etFirstNumber.requestFocus()
                    etThirdNumber -> if (textFlag) etFourthNumber.requestFocus() else etSecondNumber.requestFocus()
                    etFourthNumber -> if (textFlag) etFifthNumber.requestFocus() else etThirdNumber.requestFocus()
                    etFifthNumber -> if (textFlag) hideKeyboard() else etFourthNumber.requestFocus()
                }
            }
        }
    }
}