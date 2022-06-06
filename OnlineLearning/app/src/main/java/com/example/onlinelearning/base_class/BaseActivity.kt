package com.example.onlinelearning.base_class

import android.app.Activity
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinelearning.R
import com.example.onlinelearning.common.Constants

open class BaseActivity: AppCompatActivity() {

    fun hideKeyboard() {
        currentFocus?.let {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, Constants.ZERO)
        }
    }

    fun startNewActivity(activity: Activity) = this.startActivity(Intent(this, activity::class.java))

    fun toastMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    fun showDialog(error: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.dialogTitle)
        builder.setMessage(error)
        builder.setIcon(R.drawable.ic_warning)
        builder.setPositiveButton(getString(R.string.ok)) { _ , _ -> }
        val alertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
}