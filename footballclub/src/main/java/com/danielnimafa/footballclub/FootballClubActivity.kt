package com.danielnimafa.footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.danielnimafa.footballclub.adapter.FCAdapter
import com.danielnimafa.footballclub.model.FootballClubParcel
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.verticalLayout

class FootballClubActivity : AppCompatActivity() {

    lateinit var listRview: RecyclerView
    lateinit var mAdapter: FCAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContentView()
        populateFootballData()
    }

    private fun populateFootballData() {
        val arrData = ArrayList<FootballClubParcel>()
        arrData.apply {
            add(FootballClubParcel("Inter Milan", R.drawable.inter, getString(R.string.inter_desc)))
            add(FootballClubParcel("AC Milan", R.drawable.ac_milan, getString(R.string.ac_milan_desc)))
            add(FootballClubParcel("Juventus", R.drawable.juve, getString(R.string.juve_desc)))
            add(FootballClubParcel("SS Lazio", R.drawable.lazio, getString(R.string.lazio_desc)))
            add(FootballClubParcel("Chelsea FC", R.drawable.chelsea, getString(R.string.chelsea_desc)))
            add(FootballClubParcel("Manchester United", R.drawable.mu, getString(R.string.mu_desc)))
            add(FootballClubParcel("Liverpool FC", R.drawable.liverpool, getString(R.string.liverpool_desc)))
            add(FootballClubParcel("Arsenal FC", R.drawable.arsenal, getString(R.string.arsenal_desc)))
            add(FootballClubParcel("Real Madrid FC", R.drawable.realm, getString(R.string.realmadrid_desc)))
            add(FootballClubParcel("FC Barcelona", R.drawable.fcb, getString(R.string.fcb_desc)))
            add(FootballClubParcel("Paris Saint Germaint", R.drawable.psg, getString(R.string.psg_desc)))
            add(FootballClubParcel("AS Monaco", R.drawable.as_monaco, getString(R.string.monaco_desc)))
        }

        mAdapter.addCollectionData(arrData)
    }

    private fun setupContentView() {
        mAdapter = FCAdapter {
            startActivity<DetailClubActivity>("fc" to it)
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