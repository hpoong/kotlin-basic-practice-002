package com.hopoong.security.config.redis

import net.okihouse.autocomplete.implement.AutocompleteServiceImpl

import net.okihouse.autocomplete.repository.AutocompleteRepository

import net.okihouse.autocomplete.repository.AutocompleteKeyRepository

import net.okihouse.autocomplete.key.AutocompleteKeyServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.StringRedisTemplate


@Configuration
class AutocompleteConfig(
    private val stringRedisTemplate: StringRedisTemplate
) {

    @Bean(name = ["autocompleteKeyRepository", "keyRepository"])
    fun keyRepository(): AutocompleteKeyRepository? {
        return AutocompleteKeyServiceImpl(stringRedisTemplate)
    }

    @Bean(name = ["autocompleteRepository"])
    fun autocompleteRepository(autocompleteKeyRepository: AutocompleteKeyRepository?): AutocompleteRepository? {
        return AutocompleteServiceImpl(stringRedisTemplate, autocompleteKeyRepository)
    }
}