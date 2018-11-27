package com.danielnimafa.klasemenliga.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import com.danielnimafa.klasemenliga.utils.RequestData

class NextMatchFragment : BaseMatchFragment() {

    companion object {
        const val TAG = "NextMatchFragment"

        @JvmStatic
        fun newInstance(): Fragment = NextMatchFragment().apply {
            arguments = Bundle()
        }
    }

    override fun performDataRequest() {
        compositeDisposable.add(RequestData.nextMatchRequest(4328,
                {

                },
                { message ->

                }))
    }
}