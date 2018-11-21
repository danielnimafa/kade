package com.danielnimafa.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.danielnimafa.footballclub.adapter.FCAdapter
import com.danielnimafa.footballclub.model.FootballClub
import com.danielnimafa.footballclub.model.FootballClubParcel
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class FootballClubActivity : AppCompatActivity() {

    lateinit var listRview: RecyclerView
    lateinit var mAdapter: FCAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContentView()
        populateFootballData()
    }

    private fun populateFootballData() {
        val arrData = ArrayList<FootballClub>()
        arrData.apply {
            add(FootballClub("Inter Milan", R.drawable.inter, getString(R.string.inter_desc)))
        }
    }

    private fun setupContentView() {
        mAdapter = FCAdapter {
            val parcel = FootballClubParcel().apply {
                label = it.label
                desc = it.desc
                img = it.img
            }
            startActivity<DetailClubActivity>("fc" to parcel)
        }

        verticalLayout {
            lparams(matchParent, matchParent)
            listRview = recyclerView {
                lparams(matchParent, matchParent)
                layoutManager = LinearLayoutManager(this@FootballClubActivity, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(DividerItemDecoration(this@FootballClubActivity, LinearLayoutManager.VERTICAL))
                setHasFixedSize(true)
                adapter = mAdapter
            }
        }
    }
}