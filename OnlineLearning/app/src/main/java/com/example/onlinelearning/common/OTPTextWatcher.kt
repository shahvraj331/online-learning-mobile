package com.example.onlinelearning.common

import android.text.Editable
import android.text.TextWatcher

class OTPTextWatcher(val focusChangeListener: (Boolean) -> Unit) : TextWatcher {

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        p0?.let {
            val currentText = it.toString()
            if (currentText.isEmpty()) focusChangeListener(false) else focusChangeListener(true)
        }
    }

    override fun afterTextChanged(p0: Editable?) {  }
}