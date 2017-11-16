@file:JvmName("ExtensionsUtils")//默认跟文件名一致
package com.lsp.kotlinnews.ext

import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.lsp.kotlinnews.R
import com.squareup.picasso.Picasso

/**
 * Created by Kenney on 2017-11-15 10:23
 */
//ViewGroup扩展函数,attachToRoot默认参数
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

//ImageView扩展函数
fun ImageView.loadImg(imageUrl: String){
    if(TextUtils.isEmpty(imageUrl)){
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    }else{
        Picasso.with(context).load(imageUrl).into(this)
    }
}

fun Fragment.showToast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, msg, length).show()
}

