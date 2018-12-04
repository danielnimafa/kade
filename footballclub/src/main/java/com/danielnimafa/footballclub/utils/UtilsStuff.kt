package com.danielnimafa.footballclub.utils

import java.text.SimpleDateFormat
import java.util.*

object UtilsStuff {

    fun toSimpleDateStr(date: Date?): String? = with(date ?: Date()) {
        SimpleDateFormat("EEE, dd MMM yyy").format(this)
    }

}