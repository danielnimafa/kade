package com.danielnimafa.klasemenliga.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.danielnimafa.klasemenliga.R
import com.danielnimafa.klasemenliga.fragments.FavoritesFragment
import com.danielnimafa.klasemenliga.fragments.LastMatchFragment
import com.danielnimafa.klasemenliga.fragments.NextMatchFragment
import com.danielnimafa.klasemenliga.views.adapter.SwipeAdapter
import kotlinx.android.synthetic.main.activity_home_layout.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity() {

    companion object {
        operator fun get(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_club_activity)

        setSupportActionBar(my_toolbar)
        supportActionBar?.apply {
            title = "Football Match Schedule"
        }

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.pref_match -> {
                    loadScreen(LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                }
                R.id.next_match -> {
                    loadScreen(NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                }
                R.id.favorites -> {
                    loadScreen(FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                }
            }
            true
        }

        bottom_navigation.selectedItemId = R.id.pref_match
    }

    private fun loadScreen(fragment: Fragment, TAG: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment, TAG)
                .commit()
    }
}
