package com.danielnimafa.klasemenliga.utils

/**
 * Created by danielnimafa on 07/12/17.
 */

object Sout {
    fun log(key: String, any: Any?) {
        println(key + ": " + any)
    }

    fun trace(e: Exception) {
        e.printStackTrace()
    }

    fun thisContext(activityClass: Class<*>) {
        log("View Context", activityClass.simpleName)
    }
}