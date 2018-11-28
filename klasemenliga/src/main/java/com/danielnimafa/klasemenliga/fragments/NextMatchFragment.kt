package com.danielnimafa.klasemenliga.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielnimafa.klasemenliga.R
import com.danielnimafa.klasemenliga.model.MatchData
import com.danielnimafa.klasemenliga.utils.RequestData
import com.danielnimafa.klasemenliga.utils.Sout

class NextMatchFragment : BaseMatchFragment() {

    companion object {
        const val TAG = "NextMatchFragment"

        @JvmStatic
        fun newInstance(): Fragment = NextMatchFragment().apply {
            arguments = Bundle()
        }
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.next_match_layout, container, false)
    }

    override fun performDataRequest() {
        showProgress()
        compositeDisposable.add(RequestData.nextMatchRequest(4328,
                { events ->
                    hideProgress()
                    hideWarningMessage()
                    events?.let {
                        mAdapter?.addCollectionData(it.events as ArrayList<MatchData>)
                        Sout.log("nextMatchRequest", "data added")
                    }
                    Sout.log("nextMatchRequest", "done")
                },
                { message ->
                    hideProgress()
                    showWarningMessage(message)
                }))
    }

}