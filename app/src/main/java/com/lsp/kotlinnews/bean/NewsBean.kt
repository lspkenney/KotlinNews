package com.lsp.kotlinnews.bean

import android.os.Parcel
import android.os.Parcelable
import com.lsp.kotlinnews.adapter.AdapterConstants
import com.lsp.kotlinnews.adapter.ViewType

/**
 * Created by Kenney on 2017-11-15 14:54
 */

data class AllNewsBean(
        val after: String,
        val before: String,
        val news: List<NewsBean>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(NewsBean)!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(after)
        parcel.writeString(before)
        parcel.writeTypedList(news)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AllNewsBean> {
        override fun createFromParcel(parcel: Parcel): AllNewsBean {
            return AllNewsBean(parcel)
        }

        override fun newArray(size: Int): Array<AllNewsBean?> {
            return arrayOfNulls(size)
        }
    }

}


data class NewsBean(var author: String,
                    var title: String,
                    var numComments: Int,
                    var created: Long,
                    var thumbnail: String,
                    var url: String?) : ViewType, Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString())

    override fun getViewType(): Int {
        return AdapterConstants.NEWS
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeInt(numComments)
        parcel.writeLong(created)
        parcel.writeString(thumbnail)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsBean> {
        override fun createFromParcel(parcel: Parcel): NewsBean {
            return NewsBean(parcel)
        }

        override fun newArray(size: Int): Array<NewsBean?> {
            return arrayOfNulls(size)
        }
    }
}