package com.lsp.kotlinnews.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lsp.kotlinnews.R
import com.lsp.kotlinnews.adapter.NewsAdapter
import com.lsp.kotlinnews.bean.AllNewsBean
import com.lsp.kotlinnews.ext.inflate
import com.lsp.kotlinnews.ext.showToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
//导入扩展包 替换所有的findViewById
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * Created by Kenney on 2017-11-15 09:46
 */
class NewsFragment : Fragment(){

    private var allNews: AllNewsBean? = null

    companion object {//类似java的static
        private var KEY_ALL_NEWS = "allNews"
    }


    //委托属性: lazy 属性:它的值只在第一次访问时计算
    private val newsManager by lazy { NewsManager() }

    //管理所有订阅者
    private var subscriptions = CompositeDisposable()


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_news)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //该方法内确保布局已经加载完毕

        //设置adapter
        //var adapter = NewsAdapter()
        //newsRecyclerView.adapter = adapter
        //apply是扩展函数省去方法名前面部分newsList.方法名
        newsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({refreshData()}, layoutManager as LinearLayoutManager))
            adapter = NewsAdapter()
        }

        if(savedInstanceState != null && savedInstanceState.containsKey(KEY_ALL_NEWS)){
            var allNews = savedInstanceState.get(KEY_ALL_NEWS) as AllNewsBean
            var news = allNews!!.news
            (newsList.adapter as NewsAdapter).clearAndAddNews(news)
        }else {
            refreshData()
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        var news = (newsList.adapter as NewsAdapter).getNews()
        if(allNews != null && news.isNotEmpty()){
            outState?.putParcelable(KEY_ALL_NEWS, allNews?.copy(news = news))
        }
    }


    private fun refreshData(){
        val subscription = newsManager.getNews(allNews?.after ?:"", 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ retrievedNews ->
                    //var appNews = retrievedNews
                    (newsList.adapter as NewsAdapter).addNews(retrievedNews.news)
                },{ error ->
                    //Toast.makeText(context, error.message?: "", Toast.LENGTH_SHORT).show()
                    showToast(error.message?: "")
                })
        subscriptions.add(subscription)
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }


    //委托属性: lazy 属性:它的值只在第一次访问时计算
    /*private val newsRecyclerView by lazy {
        newsList.setHasFixedSize(true)
        newsList.layoutManager = LinearLayoutManager(context)
        *//*newsList.addOnScrollListener {
            InfiniteScrollListener({ refreshData() }, newsList.layoutManager as LinearLayoutManager)
        }*//*
        newsList
    }*/

}