package com.eric.jlp_android.util

import android.view.View
import androidx.viewbinding.ViewBinding

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun ViewBinding.hide() {
    root.visibility = View.GONE
}

fun ViewBinding.show() {
    root.visibility = View.VISIBLE
}
