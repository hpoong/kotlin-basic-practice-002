package com.hopoong.kotlin_practice.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.session.HttpSessionEventPublisher

import org.springframework.security.core.session.SessionRegistryImpl

import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.crypto.factory.PasswordEncoderFactories

import org.springframework.security.crypto.password.PasswordEncoder
import java.security.AuthProvider











@Configuration
@EnableWebSecurity
class SecurityConfig(
    provider: SecurityAuthenticationProvider
) {

    private val provider: SecurityAuthenticationProvider = provider

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/user/**").hasRole("USER")
            .anyRequest().permitAll()
            .and().csrf().disable()
            .formLogin().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT Set

        return http.authenticationProvider(provider).build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

}