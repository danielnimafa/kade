package com.danielnimafa.klasemenliga.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielnimafa.klasemenliga.R
import com.danielnimafa.klasemenliga.views.adapter.ListMatchAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.match_layout.*

abstract class BaseMatchFragment : Fragment() {

    val compositeDisposable = CompositeDisposable()

    //region -> Lifecycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.match_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.run {
            list_view.apply {
                layoutManager = LinearLayoutManager(this@run, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = ListMatchAdapter(arrayListOf())
            }
        }

        performDataRequest()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
    //endregion

    fun showProgress() {

    }

    fun hideProgress() {

    }

    abstract fun performDataRequest()

}