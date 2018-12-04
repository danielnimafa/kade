package com.danielnimafa.footballclub.presenter

import com.danielnimafa.footballclub.model.TeamResponse
import com.danielnimafa.footballclub.view.TeamFragmentView
import com.danielnimafa.klasemenliga.utils.ApiRepository
import com.danielnimafa.klasemenliga.utils.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamFragmentPresenter(private val view: TeamFragmentView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {

    fun getTeamList(league: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)).await(),
                    TeamResponse::class.java
            )

            view.hideLoading()
            view.showTeamList(data.teams)
        }
    }

}