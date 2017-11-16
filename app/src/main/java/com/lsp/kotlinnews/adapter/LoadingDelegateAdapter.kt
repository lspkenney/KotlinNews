package com.lsp.kotlinnews.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.lsp.kotlinnews.R
import com.lsp.kotlinnews.ext.inflate

/**
 * Created by Kenney on 2017-11-15 12:20
 * 加载更多item委托适配器
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter{
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {

    }


    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_loading_item))

}
