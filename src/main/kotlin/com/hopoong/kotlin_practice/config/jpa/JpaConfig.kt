package com.hopoong.kotlin_practice.config.jpa

import com.p6spy.engine.spy.P6SpyOptions
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import javax.annotation.PostConstruct


@Configuration
@EnableJpaAuditing
class JpaConfig {

}