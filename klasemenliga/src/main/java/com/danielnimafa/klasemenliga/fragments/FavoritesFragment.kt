package com.danielnimafa.klasemenliga.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import com.danielnimafa.klasemenliga.model.Favorite
import com.danielnimafa.klasemenliga.model.MatchData
import com.danielnimafa.klasemenliga.utils.Sout
import com.danielnimafa.klasemenliga.utils.database
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritesFragment : BaseMatchFragment() {

    companion object {
        const val TAG = "FavoritesFragment"

        @JvmStatic
        fun newInstance(): Fragment = FavoritesFragment().apply {
            arguments = Bundle()
        }
    }

    override fun performDataRequest() {
        showProgress()
        context?.database?.use {
            val arrData = ArrayList<MatchData>()
            mAdapter?.apply {
                datasource.clear()
                notifyDataSetChanged()
            }

            val q = select(Favorite.TABLE_FAVORITE)
            val result = q.parseList(classParser<Favorite>())
            Observable.fromIterable(result)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext {
                        Gson().fromJson(it.matchDetail, MatchData::class.java)?.let { arrData.add(it) }
                    }
                    .doOnError { Sout.trace(it as Exception) }
                    .doOnComplete { mAdapter?.addCollectionData(arrData) }
                    .subscribe()
        }
    }
}