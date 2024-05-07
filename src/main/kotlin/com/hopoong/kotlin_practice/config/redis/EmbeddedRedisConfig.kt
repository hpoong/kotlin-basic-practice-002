package com.hopoong.kotlin_practice.config.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import org.springframework.data.redis.serializer.StringRedisSerializer

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory

import org.springframework.data.redis.connection.RedisConnectionFactory








@Configuration
class EmbeddedRedisConfig(
    @Value("\${spring.redis.port}") private var redisPort: Int
) {

//    lateinit var redisServer: RedisServer
//
//    @PostConstruct
//    private fun startRedis() {
//        println("::::: start reids")
//        redisServer = RedisServer(redisPort)
//        redisServer.start()
//    }
//
//    @PreDestroy
//    private fun stopRedis(){
//        println("::::: stop reids")
//        redisServer.stop()
//    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory("127.0.0.1", redisPort)
    }


    @Bean
    fun redisTemplate(): RedisTemplate<String, String> {
        val redisTemplate = RedisTemplate<String, String>()
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }

}