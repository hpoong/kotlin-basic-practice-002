package com.hopoong.kotlin_practice.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val objectMapper: ObjectMapper,
    private val jwtTokenProvider: JwtTokenProvider,
): OncePerRequestFilter() {


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        try {

            val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

            // 로그인이 되지 않는 상태
            if (authorizationHeader == null) {
                filterChain.doFilter(request, response)
                return
            }

            jwtTokenProvider.validateToken(authorizationHeader)

            val authentication: Authentication = jwtTokenProvider.getAuthentication(authorizationHeader)
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response)
        } catch (ex: Exception) {
            ex.printStackTrace()
            response.sendJsonResponse(HttpServletResponse.SC_BAD_REQUEST, ex.message)
        }

    }
}

private fun HttpServletResponse.sendJsonResponse(statusCode: Int, result: String?) {
    this.status = statusCode
    this.contentType = "application/json"
    this.characterEncoding = "utf-8"
    this.writer.write(result)
}
