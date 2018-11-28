package com.danielnimafa.klasemenliga.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.danielnimafa.klasemenliga.R
import com.danielnimafa.klasemenliga.model.MatchData
import com.danielnimafa.klasemenliga.utils.RequestData
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.content_detail_layout.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailScheduleActivity : AppCompatActivity() {

    companion object {
        operator fun get(context: Context) = Intent(context, DetailScheduleActivity::class.java)
    }

    var data: MatchData? = null
    val subs = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_detail_schedule_layout)

        setSupportActionBar(my_toolbar)
        supportActionBar?.apply {
            title = "Match Detail"
            setDisplayHomeAsUpEnabled(true)
        }

        my_toolbar.setNavigationOnClickListener { onBackPressed() }

        intent?.extras?.run { data = getParcelable("data") }

        data?.let { attachData(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        subs.clear()
    }

    private fun attachData(t: MatchData) {

        fetchHomeImageTeam(t.idHomeTeam!!) { imgPath ->
            imgPath?.let { imgHomeTeam.setImageURI(it) }
        }

        fetchAwayImageTeam(t.idAwayTeam!!) { imgPath ->
            imgPath?.let { imgAwayTeam.setImageURI(it) }
        }

        dateTx.text = t.strDate
        homeTeamTx.text = t.strHomeTeam
        awayTeamTx.text = t.strAwayTeam
        homeScoreTx.text = t.intHomeScore
        awayScoreTx.text = t.intAwayScore
        homeGoalsTx.text = t.strHomeGoalDetails
        awayGoalsTx.text = t.strAwayGoalDetails
        homeShotsTx.text = t.intHomeShots
        awayShotsTx.text = t.intAwayShots
        homeGKTx.text = t.strHomeLineupGoalkeeper
        awayGKTx.text = t.strAwayLineupGoalkeeper
        homeDFTx.text = t.strHomeLineupDefense
        awayDFTx.text = t.strAwayLineupDefense
        homeMDTx.text = t.strHomeLineupMidfield
        awayMDTx.text = t.strAwayLineupMidfield
        homeFWTx.text = t.strHomeLineupForward
        awayFWTx.text = t.strAwayLineupForward
        homeSubTx.text = t.strHomeLineupSubstitutes
        awaySubTx.text = t.strAwayLineupSubstitutes
    }

    private fun fetchHomeImageTeam(idTeam: String, completion: (String?) -> Unit) {
        subs.add(RequestData.teamDetailRequest(idTeam) { completion(it) })
    }

    private fun fetchAwayImageTeam(idTeam: String, completion: (String?) -> Unit) {
        subs.add(RequestData.teamDetailRequest(idTeam) { completion(it) })
    }

}