package com.danielnimafa.klasemenliga.utils

import android.os.Handler
import android.view.View

fun postDelayed(delayMillis: Long, todo: () -> Unit) {
    Handler().postDelayed({ todo.invoke() }, delayMillis)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}