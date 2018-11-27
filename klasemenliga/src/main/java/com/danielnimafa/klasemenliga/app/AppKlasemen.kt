package com.danielnimafa.klasemenliga.app

import android.content.Context
import android.content.ContextWrapper
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import com.pixplicity.easyprefs.library.Prefs

class AppKlasemen : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)

        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()

    }

    override fun onTerminate() {
        super.onTerminate()
        Fresco.shutDown()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}