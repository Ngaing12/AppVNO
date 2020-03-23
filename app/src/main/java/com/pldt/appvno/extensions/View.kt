package com.pldt.appvno.extensions

import android.view.View

fun View.isVisible(value: Boolean){
    this.visibility = if (value) View.VISIBLE else View.GONE
}