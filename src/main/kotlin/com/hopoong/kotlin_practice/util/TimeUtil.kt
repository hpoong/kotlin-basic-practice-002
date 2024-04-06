package com.hopoong.kotlin_practice.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimeUtil {

    fun getFormattedTimestamp(): String {
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return now.format(formatter)
    }
}