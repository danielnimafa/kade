package com.danielnimafa.klasemenliga.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.danielnimafa.klasemenliga.R

class DetailScheduleActivity : AppCompatActivity() {

    companion object {
        operator fun get(context: Context) = Intent(context, DetailScheduleActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_detail_schedule_layout)

        // TODO setup recyclerview
    }

}