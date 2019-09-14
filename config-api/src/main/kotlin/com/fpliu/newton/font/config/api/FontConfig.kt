package com.fpliu.newton.font.config.api

import android.app.Application
import android.content.Context
import android.graphics.Typeface

//全局替换字体，必须明确声明你使用的字体是monospace
//<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
//    ....
//    <item name="android:typeface">monospace</item>
//</style>
@Throws(Exception::class)
fun Application.applyFontFromConfig(defaultFontUri: String): Boolean {
    return applyFontFromConfig("appFontUri", defaultFontUri)
}

//全局替换字体，必须明确声明你使用的字体是monospace
//<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
//    ....
//    <item name="android:typeface">monospace</item>
//</style>
@Throws(Exception::class)
fun Application.applyFontFromConfig(key: String = "appFontUri", defaultFontUri: String): Boolean {
    var uri = FontConfigFactory.getInstance(this, key).getCurrentFontUri()
    if (uri == "") uri = defaultFontUri
    if (uri == "") return false
    val array = uri.split(":")
    if (array.size != 2) return false
    applyFont(array[0], array[1])
    return true
}

//全局替换字体，必须明确声明你使用的字体是monospace
//<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
//    ....
//    <item name="android:typeface">monospace</item>
//</style>
@Throws(Exception::class)
fun Application.applyFont(fontUri: String): Boolean {
    val array = fontUri.split(":")
    if (array.size != 2) return false
    applyFont(array[0], array[1])
    return true
}

//全局替换字体，必须明确声明你使用的字体是monospace
//<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
//    ....
//    <item name="android:typeface">monospace</item>
//</style>
@Throws(Exception::class)
fun Application.applyFont(type: String, path: String) {
    Typeface::class.java.getDeclaredField("MONOSPACE").run {
        isAccessible = true
        set(null, createTypeface(type, path))
    }
}

fun Context.createTypeface(type: String, path: String): Typeface = when (type) {
    "assets" -> Typeface.createFromAsset(assets, path)
    else -> Typeface.createFromFile(path)
}