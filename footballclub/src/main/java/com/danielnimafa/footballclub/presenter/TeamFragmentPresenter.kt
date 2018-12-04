package com.danielnimafa.footballclub.presenter

import com.danielnimafa.footballclub.model.TeamResponse
import com.danielnimafa.footballclub.view.TeamFragmentView
import com.danielnimafa.klasemenliga.utils.ApiRepository
import com.danielnimafa.klasemenliga.utils.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamFragmentPresenter(private val view: TeamFragmentView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {

    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }

}