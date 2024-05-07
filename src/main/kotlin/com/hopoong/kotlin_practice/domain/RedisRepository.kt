package com.hopoong.kotlin_practice.domain

interface RedisRepository {
    fun clear()
    fun remove(key: String): Any?
    fun findAll(): ArrayList<Any>
    fun findByKey(key: String): Any
    fun save(key: String, value: Any)
    fun findKeyExpireTime(key: String): String?
}