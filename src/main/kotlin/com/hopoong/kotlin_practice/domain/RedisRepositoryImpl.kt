package com.hopoong.kotlin_practice.domain

import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.RedisTemplate
import java.util.Optional

class RedisRepositoryImpl(
    private val redisTemplate: RedisTemplate<String, Any>
): RedisRepository {

    private val log = LoggerFactory.getLogger(this::class.java)


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
        redisTemplate.opsForValue().set(key, value)
    }


}