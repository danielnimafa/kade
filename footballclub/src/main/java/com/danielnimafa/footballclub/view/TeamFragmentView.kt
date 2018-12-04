package com.danielnimafa.footballclub.view

import com.danielnimafa.footballclub.model.Team

interface TeamFragmentView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}