package com.hopoong.kotlin_practice.config.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy


@Configuration
class EmbeddedRedisConfig(
    @Value("\${spring.redis.port}") private var redisPort: Int
) {

    lateinit var redisServer: RedisServer

    @PostConstruct
    private fun startRedis() {
        redisServer = RedisServer(redisPort)
        redisServer.start()
    }

    @PreDestroy
    private fun stopRedis(){
        redisServer.stop()
    }

}