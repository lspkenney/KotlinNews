package com.lsp.kotlinnews.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.lsp.kotlinnews.R
import com.lsp.kotlinnews.bean.NewsBean
import com.lsp.kotlinnews.ext.getFriendlyTime
import com.lsp.kotlinnews.ext.inflate
import com.lsp.kotlinnews.ext.loadImg
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by Kenney on 2017-11-15 14:58
 */
class NewsDelegateAdapter : ViewTypeDelegateAdapter{
    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as NewsBean)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item)) {
        private val img_thumbnail = itemView.img_thumbnail
        private val description = itemView.description
        private val author = itemView.author
        private val comments = itemView.comments
        private val time = itemView.time

        fun bind(item: NewsBean){
            description.text = item.title
            author.text = item.author
            img_thumbnail.loadImg(item.thumbnail)
            //字符串模版
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }
}