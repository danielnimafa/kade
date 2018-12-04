package com.danielnimafa.footballclub.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.danielnimafa.footballclub.R
import com.danielnimafa.footballclub.model.Team
import com.danielnimafa.footballclub.presenter.TeamFragmentPresenter
import com.danielnimafa.footballclub.view.TeamFragmentView
import com.danielnimafa.footballclub.view.activities.TeamDetailActivity
import com.danielnimafa.footballclub.view.adapter.TeamAdapter
import com.danielnimafa.klasemenliga.utils.ApiRepository
import com.danielnimafa.klasemenliga.utils.invisible
import com.danielnimafa.klasemenliga.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.team_fragment_layout.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

class TeamsFragment : Fragment(), TeamFragmentView {

    //region Properties
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamFragmentPresenter
    private lateinit var mAdapter: TeamAdapter
    private lateinit var leagueName: String
    //endregion

    //region Life Fragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.team_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = TeamAdapter(teams) {
            context?.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }

        listview.apply {
            layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mAdapter
        }

        val spinnerItems = resources.getStringArray(R.array.league)
        spinner.adapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipe.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamFragmentPresenter(this, request, gson)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    //endregion

    override fun showLoading() {
        progressBar.visible()
        listview.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        listview.visible()
        swipe.isRefreshing = false
    }

    override fun showTeamList(data: List<Team>) {
        swipe.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        mAdapter.notifyDataSetChanged()
    }
}