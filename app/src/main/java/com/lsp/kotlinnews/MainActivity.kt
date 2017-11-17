package com.lsp.kotlinnews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lsp.kotlinnews.fragment.NewsFragment

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment()
    }

    private fun setFragment(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, NewsFragment.newInstance(), NewsFragment::class.java.simpleName)
                .commit()
        Log.i(TAG, "NewsFragment replaced")
    }
}
