package com.danielnimafa.klasemenliga.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.danielnimafa.klasemenliga.R
import com.danielnimafa.klasemenliga.model.Favorite
import com.danielnimafa.klasemenliga.model.MatchData
import com.danielnimafa.klasemenliga.utils.RequestData
import com.danielnimafa.klasemenliga.utils.Sout
import com.danielnimafa.klasemenliga.utils.database
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.content_detail_layout.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast
import java.lang.Exception

class DetailScheduleActivity : AppCompatActivity() {

    companion object {
        operator fun get(context: Context) = Intent(context, DetailScheduleActivity::class.java)
    }

    private var data: MatchData? = null
    private var matchDetailStr = ""
    private val subs = CompositeDisposable()
    private var menuItem: Menu? = null
    private var addFavorMenu: MenuItem? = null
    private var removeFavorMenu: MenuItem? = null
    private var isFavorite: Boolean = false

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

    override fun onResume() {
        super.onResume()
        // TODO query current match ID from database, handle option menu visibility
    }

    override fun onDestroy() {
        super.onDestroy()
        subs.clear()
    }

    //region :: Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        menu?.run {
            addFavorMenu = findItem(R.id.add_to_favorite)
            removeFavorMenu = findItem(R.id.remove_from_favorite)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                addToFavorite()
                true
            }

            R.id.remove_from_favorite -> {
                removeFromFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    //endregion

    //region :: DB Operation
    private fun removeFromFavorite() {
        try {
            // TODO delete operation
        } catch (e: Exception) {
            Sout.trace(e)
            toast("Failed to remove from favorite. Error: ${e.localizedMessage}")
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.MATCH_ID to data!!.idEvent,
                        Favorite.MATCH_NAME to data!!.strEvent,
                        Favorite.MATCH_DETAIL to matchDetailStr)
            }
            toast("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            Sout.trace(e)
            toast("Failed to add favorite${e.localizedMessage}")
        }
    }
    //endregion

    private fun showAddMenu(flag: Boolean) {
        addFavorMenu?.isVisible = flag
        removeFavorMenu?.isVisible = !flag
    }

    private fun attachData(t: MatchData) {

        matchDetailStr = Gson().toJson(t)
        Sout.log("Match Detail", matchDetailStr)

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