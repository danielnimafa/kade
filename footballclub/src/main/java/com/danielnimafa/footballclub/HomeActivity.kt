package com.danielnimafa.footballclub

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home_layout.*

class HomeActivity : AppCompatActivity() {

    companion object {
        operator fun get(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_layout)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.teams -> {
                    loadScreen(TeamsFragment(), TeamsFragment::class.java.simpleName)
                }
                R.id.favorites -> {
                    loadScreen(FavoriteTeamsFragment(), FavoriteTeamsFragment::class.java.simpleName)
                }
            }
            true
        }

        bottom_navigation.selectedItemId = R.id.teams

    }

    private fun loadScreen(fragment: Fragment, TAG: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment, TAG)
                .commit()
    }

}