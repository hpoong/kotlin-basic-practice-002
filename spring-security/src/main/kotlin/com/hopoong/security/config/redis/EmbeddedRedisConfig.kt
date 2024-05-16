package com.hopoong.security.config.redis

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy


@Configuration
class EmbeddedRedisConfig(
    @Value("\${spring.redis.port}") private var redisPort: Int,
    @Value("\${spring.redis.host}") private var redisHost: String
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    lateinit var redisServer: RedisServer

    @PostConstruct
    private fun startRedis() {
        log.info("Start Embedded Redis ::::::::")
        redisServer = RedisServer(redisPort)
        redisServer.start()
    }

    @PreDestroy
    private fun stopRedis(){
        log.info("Stop Embedded Redis ::::::::")
        redisServer.stop()
    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(redisHost, redisPort)
    }


    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }

}