package com.fpliu.newton.font.config.ui.entity

import android.os.Parcel
import android.os.Parcelable

data class Font(
    //名称，用于显示
    val name: String = "",
    //assets或file
    var type: String = "",
    //assets或file的路径
    var path: String = "",
    //文件大小
    val size: String = "",
    //下载地址
    val url: String = ""
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(type)
        writeString(path)
        writeString(size)
        writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Font> = object : Parcelable.Creator<Font> {
            override fun createFromParcel(source: Parcel): Font = Font(source)
            override fun newArray(size: Int): Array<Font?> = arrayOfNulls(size)
        }
    }
}