package com.danielnimafa.klasemenliga.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import com.danielnimafa.klasemenliga.model.MatchData
import com.danielnimafa.klasemenliga.utils.RequestData
import com.danielnimafa.klasemenliga.utils.Sout

class LastMatchFragment : BaseMatchFragment() {

    companion object {
        const val TAG = "LastMatchFragment"

        @JvmStatic
        fun newInstance(): Fragment = LastMatchFragment().apply {
            arguments = Bundle()
        }
    }

    override fun performDataRequest() {
        showProgress()
        compositeDisposable.add(RequestData.lastMatchRequest(4328,
                { events ->
                    hideProgress()
                    hideWarningMessage()
                    events?.let {
                        mAdapter?.addCollectionData(it.events as ArrayList<MatchData>)
                        Sout.log("lastMatchRequest", "data added")
                    }
                    Sout.log("lastMatchRequest", "done")
                },
                { message ->
                    hideProgress()
                    showWarningMessage(message)
                }))
    }

}