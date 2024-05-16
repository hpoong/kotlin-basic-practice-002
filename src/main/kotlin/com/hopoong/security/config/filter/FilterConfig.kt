package com.hopoong.security.config.filter

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Configuration
import javax.servlet.Filter

@Configuration
class FilterConfig {

////    @Bean
//    fun MDCLoggingFilterBean(): FilterRegistrationBean<Filter>? {
//        val bean: FilterRegistrationBean<Filter> = FilterRegistrationBean<Filter>()
//        bean.setFilter(MDCLoggingFilter())
//        bean.setOrder(1)
//        return bean
//    }

//    @Bean
    fun filterRegistrationBean(): FilterRegistrationBean<Filter>? {
        val bean: FilterRegistrationBean<Filter> = FilterRegistrationBean<Filter>()
        bean.setFilter(SomeFilter())
        bean.setOrder(2)
        return bean
    }

}