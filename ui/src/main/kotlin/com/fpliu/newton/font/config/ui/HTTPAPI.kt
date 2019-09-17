package com.fpliu.newton.font.config.ui

import com.fpliu.newton.font.config.ui.entity.Font
import com.fpliu.newton.font.config.ui.entity.ListResponseEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface HTTPAPI {

    @GET("/api/font/list/{page}")
    fun requestFontList(@Path("page") page: Int) : Observable<ListResponseEntity<Font>>
}