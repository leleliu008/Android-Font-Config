package com.fpliu.newton.font.config.ui.entity

class ListResponseEntity<T> : ResponseEntity<MutableList<T>>() {

    val list: MutableList<T>?
        get() = super.data
}
