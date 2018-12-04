package com.danielnimafa.footballclub.presenter

import com.danielnimafa.footballclub.model.TeamResponse
import com.danielnimafa.footballclub.view.TeamDetailView
import com.danielnimafa.klasemenliga.utils.ApiRepository
import com.danielnimafa.klasemenliga.utils.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamDetail(data.teams)
            }
        }
    }
}