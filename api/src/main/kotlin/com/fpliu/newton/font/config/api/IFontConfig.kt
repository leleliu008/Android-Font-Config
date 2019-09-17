package com.fpliu.newton.font.config.api

interface IFontConfig {

    //获得当前的字体的
    fun getCurrentFontUri(): String

    fun changeFont(uri: String)
}