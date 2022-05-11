package com.example.onlinelearning.common

import android.text.Editable
import android.text.TextWatcher

class OTPTextWatcher(val focusChangeListener: (Int) -> Unit) : TextWatcher {

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        p0?.let {
            val currentText = it.toString()
            if (currentText.isEmpty()) focusChangeListener(0) else focusChangeListener(1)
        }
    }

    override fun afterTextChanged(p0: Editable?) {  }
}