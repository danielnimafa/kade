package com.danielnimafa.klasemenliga.utils

import android.net.Uri
import com.danielnimafa.footballclub.BuildConfig
import com.danielnimafa.footballclub.model.EventMatch
import com.danielnimafa.footballclub.model.TeamList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.net.URL
import java.util.concurrent.TimeUnit

object RequestData {

    fun lastMatchRequest(leagueId: Int, completion: (EventMatch?) -> Unit, error: (String) -> Unit): DisposableObserver<Response<EventMatch>> {
        var events: EventMatch? = null
        return ServiceGenerator.createService().lastMatch(leagueId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Response<EventMatch>>() {
                    override fun onComplete() {
                        completion(events)
                    }

                    override fun onNext(t: Response<EventMatch>) {
                        if (t.isSuccessful) events = t.body()
                    }

                    override fun onError(e: Throwable) {
                        Sout.trace(e as Exception)
                        error("Failed to fetch events. Error: ${e.localizedMessage}")
                    }
                })
    }

    fun nextMatchRequest(leagueId: Int, completion: (EventMatch?) -> Unit, error: (String) -> Unit): DisposableObserver<Response<EventMatch>> {
        var events: EventMatch? = null
        return ServiceGenerator.createService().nextMatch(leagueId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Response<EventMatch>>() {
                    override fun onComplete() {
                        completion(events)
                    }

                    override fun onNext(t: Response<EventMatch>) {
                        if (t.isSuccessful) events = t.body()
                    }

                    override fun onError(e: Throwable) {
                        Sout.trace(e as Exception)
                        error("Failed to fetch events. Error: ${e.localizedMessage}")
                    }
                })
    }

    fun teamDetailRequest(teamId: String, completion: (String?) -> Unit): DisposableObserver<Response<TeamList>> {
        var events: String? = null
        return ServiceGenerator.createService().teamDetail(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Response<TeamList>>() {
                    override fun onComplete() {
                        completion(events)
                    }

                    override fun onNext(t: Response<TeamList>) {
                        if (t.isSuccessful) events = t.body()?.teams!![0].strTeamLogo
                    }

                    override fun onError(e: Throwable) {
                        Sout.trace(e as Exception)
                        //error("Failed to fetch events. Error: ${e.localizedMessage}")
                    }
                })
    }

}

interface api {

    @GET("eventspastleague.php")
    fun lastMatch(@Query("id") id: Int): Observable<Response<EventMatch>>

    @GET("eventsnextleague.php")
    fun nextMatch(@Query("id") id: Int): Observable<Response<EventMatch>>

    @GET("lookupteam.php")
    fun teamDetail(@Query("id") id: String): Observable<Response<TeamList>>

}

object ServiceGenerator {
    val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .cache(null)

    val builder = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

    fun createService(): api {
        addLoggingInterceptor(httpClient)
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(api::class.java)
    }

    private fun addLoggingInterceptor(httpClient: OkHttpClient.Builder) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
    }
}

class ApiRepository {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}

object TheSportDBApi {
    /*fun getTeams(league: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}\" + \"/search_all_teams.php?l=\" + league"
    }*/

    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("search_all_teams.php")
                .appendQueryParameter("l", league)
                .build()
                .toString()
    }

    fun getTeamDetail(teamId: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", teamId)
                .build()
                .toString()
    }
}