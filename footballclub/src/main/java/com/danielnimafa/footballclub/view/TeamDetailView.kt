package com.danielnimafa.footballclub.view

import com.danielnimafa.footballclub.model.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}