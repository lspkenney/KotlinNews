package com.lsp.kotlinnews.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Kenney on 2017-11-15 12:18
 * 定义ViewType委托适配器
 */
interface ViewTypeDelegateAdapter{
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}