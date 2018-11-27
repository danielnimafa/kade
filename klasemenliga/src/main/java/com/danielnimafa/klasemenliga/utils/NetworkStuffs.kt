package com.danielnimafa.klasemenliga.utils

import com.danielnimafa.klasemenliga.model.EventMatch
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object RequestData {

    fun lastMatchRequest(leagueId: Int, completion: (EventMatch?) -> Unit, error: (String) -> Unit): DisposableObserver<Response<EventMatch>> {
        var events: EventMatch? = null
        return ServiceGenerator.createService().lastMatch(leagueId)
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

}

interface api {

    @GET("eventspastleague.php")
    fun lastMatch(@Query("id") id: Int): Observable<Response<EventMatch>>

    @GET("eventsnextleague.php")
    fun nextMatch(@Query("id") id: Int): Observable<Response<EventMatch>>

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