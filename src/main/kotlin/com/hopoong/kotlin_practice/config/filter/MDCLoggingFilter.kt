package com.hopoong.kotlin_practice.config.filter

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class MDCLoggingFilter: Filter {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        // 추후 Nginx X-Request-Id 추가하기
        val req: HttpServletRequest = request as HttpServletRequest
        val requestId = req.getHeader("X-RequestID") ?: UUID.randomUUID().toString().replace("-", "")
        MDC.put("request_id", requestId);
        chain.doFilter(request, response);
        MDC.clear();
    }
}