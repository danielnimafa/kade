package com.danielnimafa.klasemenliga.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.danielnimafa.klasemenliga.R
import com.danielnimafa.klasemenliga.fragments.LastMatchFragment
import com.danielnimafa.klasemenliga.fragments.NextMatchFragment
import com.danielnimafa.klasemenliga.views.adapter.SwipeAdapter
import kotlinx.android.synthetic.main.activity_club_activity.*
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

        // TODO setup view pager
        val vpAdapter = SwipeAdapter(supportFragmentManager)
        vpHome.apply {
            adapter = vpAdapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        }

        tabLayout.apply {
            setupWithViewPager(vpHome)
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabReselected(p0: TabLayout.Tab?) {
                    p0?.also { vpHome.currentItem = it.position }
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {

                }

                override fun onTabSelected(p0: TabLayout.Tab?) {

                }
            })
        }

        val firstTitle = "Last Match"
        val secondTitle = "Next Match"
        vpAdapter.apply {
            addFragment(LastMatchFragment.newInstance(), firstTitle)
            addFragment(NextMatchFragment.newInstance(), secondTitle)
        }
    }

    private fun loadScreen(fragment: Fragment, TAG: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment, TAG)
                .commit()
    }
}
