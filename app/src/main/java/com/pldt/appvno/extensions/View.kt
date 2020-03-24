package com.pldt.appvno.extensions

import android.view.View
import android.widget.EditText

fun View.isVisible(value: Boolean){
    this.visibility = if (value) View.VISIBLE else View.GONE
}


fun EditText?.Text() : String{
    return this?.text?.toString() ?: ""
}