package com.danielnimafa.klasemenliga.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import com.danielnimafa.klasemenliga.utils.RequestData

class LastMatchFragment : BaseMatchFragment() {

    //region Properties
    companion object {
        const val TAG = "LastMatchFragment"

        @JvmStatic
        fun newInstance(): Fragment = LastMatchFragment().apply {
            arguments = Bundle()
        }
    }
    //endregion

    override fun performDataRequest() {
        compositeDisposable.add(RequestData.lastMatchRequest(4328,
                {

                },
                { message ->

                }))
    }
}