package com.danielnimafa.klasemenliga.utils

import android.content.Context
import android.os.Handler
import android.util.DisplayMetrics

fun postDelayed(delayMillis: Long, todo: () -> Unit) {
    Handler().postDelayed({ todo.invoke() }, delayMillis)
}

fun convertPixelsToDp(px: Float, context: Context): Float {
    return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun convertDpToPixel(dp: Float, context: Context): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}