package com.hopoong.kotlin_practice.domain

import com.hopoong.kotlin_practice.util.TimeUtil
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class RedisRepositoryImpl(
    private val redisTemplate: RedisTemplate<String, Any>
): RedisRepository {

    private val log = LoggerFactory.getLogger(this::class.java)
    private val REFRESH_TOKEN_EXPIRE_TIME: Long = 1000 * 60 * 60 * 24 * 7

    override fun clear() {
        redisTemplate.execute { connection ->
            connection.flushDb()
            "OK" // return value for execute
        }
    }

    override fun remove(key: String): Any? {
        return redisTemplate.delete(key)
    }

    override fun findAll(): ArrayList<Any> {
        val keys = redisTemplate.keys("*")
        val list = ArrayList<Any>()

        for (key in keys) {
            val value = redisTemplate.opsForValue().get(key)
            if (value != null) {
                list.add(value)
            }
        }
        return list
    }

    override fun findByKey(key: String): Any {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElseThrow()
    }

    override fun save(key: String, value: Any) {
//        println(TimeUtil().formatTTL(REFRESH_TOKEN_EXPIRE_TIME))
        redisTemplate.opsForValue().set(key, value)
    }

    override fun findKeyExpireTime(key: String): String? {
        val ttlInSeconds = redisTemplate.getExpire(key)
        return TimeUtil().formatTTL(ttlInSeconds ?: -1)
    }

}