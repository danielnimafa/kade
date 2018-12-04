package com.danielnimafa.footballclub.presenter

import com.danielnimafa.footballclub.model.Team
import com.danielnimafa.footballclub.model.TeamResponse
import com.danielnimafa.footballclub.view.TeamFragmentView
import com.danielnimafa.klasemenliga.utils.ApiRepository
import com.danielnimafa.klasemenliga.utils.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TeamFragmentPresenterTest {

    @Mock
    private lateinit var view: TeamFragmentView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamFragmentPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamFragmentPresenter(view, apiRepository, gson)
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premiere League"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeams(league)).await(),
                    TeamResponse::class.java)).thenReturn(response)

            presenter.getTeamList(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}