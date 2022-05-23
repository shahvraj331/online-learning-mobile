package com.example.onlinelearning.common

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import android.widget.EditText

fun getSpannable(text: String, startIndex: Int, endIndex: Int, color: Int, spanClickCallback: () -> Unit): Spannable {
    val spannable = SpannableString(text)
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(p0: View) {
            spanClickCallback()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
            ds.color = color
        }
    }
    spannable.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannable
}

fun verifyNonEmpty(vararg input: EditText): Boolean {
    for (editText in input) {
        if (editText.trimmedText().isEmpty()) return false
    }
    return true
}

/**
 * Edittext extension
 */
fun EditText.updateTransformationMethod(image: View) {
    this.transformationMethod = if (image.isSelected) PasswordTransformationMethod() else null
    image.isSelected = !image.isSelected
    this.text?.let { text ->
        this.setSelection(text.length)
    }
}

fun EditText.trimmedText() = this.text.toString().trim()

fun EditText.verifyEmailAddress(): Boolean {
    val text = this.trimmedText()
    return text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(text).matches()
}