package com.fpliu.newton.font.config.api

import android.content.Context
import java.util.concurrent.atomic.AtomicBoolean

object FontConfigFactory {

    private val isInited = AtomicBoolean(false)

    private var fontConfig: IFontConfig? = null

    fun setInstance(fontConfig: IFontConfig) {
        this.fontConfig = fontConfig
    }

    fun getInstance(appContext: Context, key: String = "appFontUri"): IFontConfig {
        if (isInited.compareAndSet(false, true)) {
            if (fontConfig == null) {
                fontConfig = FontConfigViaSp(appContext, key)
            }
        }
        return fontConfig!!
    }
}