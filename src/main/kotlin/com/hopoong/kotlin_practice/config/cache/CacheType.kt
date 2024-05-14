package com.hopoong.kotlin_practice.config.cache

enum class CacheType(
    val cacheName: String,
    val expiredAfterWrite: Long,
    val maximumSize: Long
) {
    POSTS("posts", 5 * 60, 10000)
}