package com.danielnimafa.footballclub.presenter

import com.danielnimafa.footballclub.model.TeamResponse
import com.danielnimafa.footballclub.view.TeamDetailView
import com.danielnimafa.klasemenliga.utils.ApiRepository
import com.danielnimafa.klasemenliga.utils.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                    TeamResponse::class.java)

            view.hideLoading()
            view.showTeamDetail(data.teams)
        }
    }
}