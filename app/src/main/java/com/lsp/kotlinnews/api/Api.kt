package com.lsp.kotlinnews.api

import io.reactivex.internal.operators.flowable.FlowableLimit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Kenney on 2017-11-16 11:32
 */
interface Api {
    //www.reddit.com/top.json?limit=10
    @GET("/top.json")
    fun getTop(@Query("after") after: String, @Query("limit") limit: Int): Call<NewsResponse>
}