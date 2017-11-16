package com.lsp.kotlinnews.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.lsp.kotlinnews.bean.NewsBean

/**
 * Created by Kenney on 2017-11-15 14:10
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType{
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)

    }

    fun addNews(news: List<NewsBean>) {
        var beforeSize = items.size - 1

        //移除加载更多布局
        items.removeAt(beforeSize)
        notifyItemRemoved(beforeSize)

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(beforeSize, news.size + 1)
    }

    fun getNews(): List<NewsBean> {
        //条件过滤
        return items.filter {
            it.getViewType() == AdapterConstants.NEWS
        }.map {
            it as NewsBean
        }
    }

    fun clearAndAddNews(news: List<NewsBean>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemViewType(position: Int) = items.get(position).getViewType()

}