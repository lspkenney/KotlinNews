package com.lsp.kotlinnews.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Kenney on 2017-11-16 11:36
 */
class RestApi {
    private val api: Api
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        api = retrofit.create(Api::class.java)
    }

    fun getNews(after: String, limit: Int): Call<NewsResponse>{
        return api.getTop(after, limit)
    }
}