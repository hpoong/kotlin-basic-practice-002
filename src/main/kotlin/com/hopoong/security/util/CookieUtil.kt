package com.hopoong.security.util

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie


class CookieUtil {


    /*
     * Headers Cookie 생성
     */
    fun createCookieHeaders(cookie: ResponseCookie): HttpHeaders {
        val headers = HttpHeaders()
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString())
        return headers
    }

}