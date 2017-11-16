package com.lsp.kotlinnews.fragment

import com.lsp.kotlinnews.api.RestApi
import com.lsp.kotlinnews.bean.AllNewsBean
import com.lsp.kotlinnews.bean.NewsBean
import io.reactivex.Observable

/**
 * Created by Kenney on 2017-11-16 09:23
 */
class NewsManager(private val api: RestApi = RestApi()) {
    fun getNews(after: String, limit: Int) : Observable<AllNewsBean> {
        return Observable.create { subscriber ->
            val callResponse = api.getNews(after, limit)
            val execute = callResponse.execute()
            var response = execute.body()!!.data

            if(execute.isSuccessful){
                val items = response.children.map {
                    var item = it.data
                    NewsBean(item.author, item.title, item.num_comments,
                            item.created, item.thumbnail, item.url)
                }
                var news = AllNewsBean(response.after ?: "", response.before ?: "", items)
                subscriber.onNext(news)
                subscriber.onComplete()
            }else{
                subscriber.onError(Throwable(execute.message()))
            }
        }
    }
}