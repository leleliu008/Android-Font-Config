package com.fpliu.newton.font.config.ui.entity

import java.lang.RuntimeException

class ResponseException(
    val code: Int,
    message: String? = null,
    val data: Any? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)