package com.lsp.kotlinnews.api

/**
 * Created by Kenney on 2017-11-16 11:25
 */

class NewsResponse(val data: DataResponse)

class DataResponse(
        val children: List<NewsChildrenResponse>,
        val after: String?,
        val before:String?
)

class NewsChildrenResponse(val data: NewsDataResponse)

class NewsDataResponse(
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
)