package com.hopoong.security.config.cache

import com.github.benmanes.caffeine.cache.Caffeine
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.stream.Collectors

import java.util.concurrent.TimeUnit

import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCache
import java.util.*


@Configuration
@EnableCaching
class CacheConfig() {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun cacheManager(): CacheManager? {
        val cacheManager = SimpleCacheManager()
        val caches = Arrays.stream(CacheType.values())
            .map { cache ->
                CaffeineCache(
                    cache.cacheName, Caffeine.newBuilder().recordStats()
                        .expireAfterWrite(cache.expiredAfterWrite, TimeUnit.SECONDS)
                        .maximumSize(cache.maximumSize)
                        .removalListener { key: Any?, value: Any?, cause ->
                            log.info("removalListener ::: key ::: $key value ::: $value")
                        }.build()
                )
            }.collect(Collectors.toList())
        cacheManager.setCaches(caches)
        return cacheManager
    }

}