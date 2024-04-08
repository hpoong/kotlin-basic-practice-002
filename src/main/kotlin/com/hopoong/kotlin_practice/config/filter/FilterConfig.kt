package com.hopoong.kotlin_practice.config.filter

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.servlet.Filter

@Configuration
class FilterConfig {

//    @Bean
    fun filterRegistrationBean(): FilterRegistrationBean<Filter>? {
        val bean: FilterRegistrationBean<Filter> = FilterRegistrationBean<Filter>()
        bean.setFilter(SomeFilter())
        bean.setOrder(1)
        return bean
    }

}