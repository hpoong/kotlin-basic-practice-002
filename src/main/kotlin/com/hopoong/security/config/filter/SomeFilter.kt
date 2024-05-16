package com.hopoong.security.config.filter

import org.slf4j.LoggerFactory
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class SomeFilter: Filter  {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain) {
        val req: HttpServletRequest = request as HttpServletRequest
        val requestURI: String = req.getRequestURI()
        log.info("[{}] SomeFilter doFilter Start", requestURI)
        chain.doFilter(request, response);
    }
}