package com.lsp.kotlinnews.fragment

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by Kenney on 2017-11-16 14:14
 * 高阶函数 实现无限滑动
 * 一个高阶函数是一个函数接受一个函数作为参数或返回一个函数.
 */
class InfiniteScrollListener(val func: () -> Unit,
                             val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = 2
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if(dy > 0){
            visibleItemCount = recyclerView!!.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if(loading){
                if(totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }

            if(!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)){
                //到达底部，自动加载更多
                func()
                loading = true
            }
        }
    }
}