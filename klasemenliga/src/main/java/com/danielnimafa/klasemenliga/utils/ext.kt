package com.danielnimafa.klasemenliga.utils

import android.os.Handler

fun postDelayed(delayMillis: Long, todo: () -> Unit) {
    Handler().postDelayed({ todo.invoke() }, delayMillis)
}