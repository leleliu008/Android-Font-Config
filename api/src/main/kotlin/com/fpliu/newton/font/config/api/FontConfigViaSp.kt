package com.fpliu.newton.font.config.api

import android.content.Context

class FontConfigViaSp(
    appContext: Context,
    private val key: String = "appFontUri"
) : IFontConfig {

    private val sp by lazy { appContext.getSharedPreferences("appFontUri", Context.MODE_PRIVATE) }

    override fun getCurrentFontUri(): String = sp.getString(key, "") ?: ""

    override fun changeFont(uri: String) {
        sp.edit().putString(key, uri).apply()
    }
}