package com.lsp.kotlinnews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lsp.kotlinnews.fragment.NewsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment()
    }

    private fun setFragment(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, NewsFragment())
                .commit()
    }
}
