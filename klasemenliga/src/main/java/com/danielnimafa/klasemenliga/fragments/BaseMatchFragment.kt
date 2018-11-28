package com.danielnimafa.klasemenliga.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielnimafa.klasemenliga.R
import com.danielnimafa.klasemenliga.activities.DetailScheduleActivity
import com.danielnimafa.klasemenliga.model.MatchData
import com.danielnimafa.klasemenliga.utils.postDelayed
import com.danielnimafa.klasemenliga.views.adapter.ListMatchAdapter
import com.kaopiz.kprogresshud.KProgressHUD
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.match_layout.*

abstract class BaseMatchFragment : Fragment() {

    val compositeDisposable = CompositeDisposable()
    var mAdapter: ListMatchAdapter? = null

    //region -> Lifecycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideWarningMessage()
        activity?.run {
            mAdapter = ListMatchAdapter(arrayListOf()) { tapEvent(it) }

            list_view.apply {
                layoutManager = LinearLayoutManager(this@run, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = mAdapter
            }

            swipeLayout.setOnRefreshListener { performDataRequest() }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        performDataRequest()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
    //endregion

    fun showProgress() {
        hideProgress()
        hideWarningMessage()
        list_view.visibility = View.GONE
        swipeLayout.post { swipeLayout.isRefreshing = true }
    }

    fun hideProgress() {
        list_view.visibility = View.VISIBLE
        if (swipeLayout.isRefreshing) swipeLayout.isRefreshing = false
    }

    fun showWarningMessage(message: String) {
        hideProgress()
        message_text.text = message
        message_text.visibility = View.VISIBLE
        list_view.visibility = View.GONE
    }

    fun hideWarningMessage() {
        message_text.visibility = View.GONE
        list_view.visibility = View.VISIBLE
    }

    private fun tapEvent(data: MatchData) {
        activity?.run {
            postDelayed(300) {
                startActivity(DetailScheduleActivity[this].apply { putExtra("data", data) })
            }
        }
    }

    abstract fun performDataRequest()
    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
}