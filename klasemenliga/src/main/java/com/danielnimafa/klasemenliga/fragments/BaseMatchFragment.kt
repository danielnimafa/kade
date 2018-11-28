package com.danielnimafa.klasemenliga.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.danielnimafa.klasemenliga.R
import com.danielnimafa.klasemenliga.activities.DetailScheduleActivity
import com.danielnimafa.klasemenliga.model.MatchData
import com.danielnimafa.klasemenliga.utils.postDelayed
import com.danielnimafa.klasemenliga.views.adapter.ListMatchAdapter
import io.reactivex.disposables.CompositeDisposable

abstract class BaseMatchFragment : Fragment() {

    val compositeDisposable = CompositeDisposable()
    var mAdapter: ListMatchAdapter? = null
    lateinit var listView: RecyclerView
    lateinit var textMessage: TextView
    lateinit var swipeLayout: SwipeRefreshLayout

    //region -> Lifecycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.match_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.run {
            listView = findViewById(R.id.list_view)
            textMessage = findViewById(R.id.message_text)
            swipeLayout = findViewById(R.id.swipeLayout)
        }

        hideWarningMessage()

        activity?.run {
            mAdapter = ListMatchAdapter(arrayListOf()) { tapEvent(it) }

            listView.apply {
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
        listView.visibility = View.GONE
        swipeLayout.post { swipeLayout.isRefreshing = true }
    }

    fun hideProgress() {
        listView.visibility = View.VISIBLE
        if (swipeLayout.isRefreshing) swipeLayout.isRefreshing = false
    }

    fun showWarningMessage(message: String) {
        hideProgress()
        textMessage.text = message
        textMessage.visibility = View.VISIBLE
        listView.visibility = View.GONE
    }

    fun hideWarningMessage() {
        textMessage.visibility = View.GONE
        listView.visibility = View.VISIBLE
    }

    private fun tapEvent(data: MatchData) {
        activity?.run {
            postDelayed(300) {
                startActivity(DetailScheduleActivity[this].apply { putExtra("data", data) })
            }
        }
    }

    abstract fun performDataRequest()
}