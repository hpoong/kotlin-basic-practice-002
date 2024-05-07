package com.hopoong.kotlin_practice.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimeUtil {

    fun getFormattedTimestamp(): String {
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return now.format(formatter)
    }

    fun formatTTL(ttlInSeconds: Long): String? {
        if (ttlInSeconds < 0) {
            return "Key does not exist or has no TTL"
        }
        val expirationDateTime = LocalDateTime.now().plusSeconds(ttlInSeconds)
        return expirationDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }

}