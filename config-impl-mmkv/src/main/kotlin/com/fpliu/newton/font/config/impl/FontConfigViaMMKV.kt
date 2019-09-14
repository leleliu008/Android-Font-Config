package com.fpliu.newton.font.config.impl

import android.content.Context
import com.fpliu.newton.font.config.api.IFontConfig
import com.tencent.mmkv.MMKV

class FontSettingViaMMKV(
    appContext: Context,
    private val key: String = "appFontUri"
) : IFontConfig {

    private var mmkv: MMKV

    init {
        MMKV.initialize(appContext)
        mmkv = MMKV.defaultMMKV()
    }

    override fun getCurrentFontUri(): String = mmkv.getString(key, "") ?: ""

    override fun changeFont(uri: String) {
        mmkv.putString(key, uri)
    }
}
