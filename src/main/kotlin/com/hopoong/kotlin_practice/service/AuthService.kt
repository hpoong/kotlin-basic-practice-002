package com.hopoong.kotlin_practice.service

import com.hopoong.kotlin_practice.domain.login.LoginDto
import com.hopoong.kotlin_practice.domain.member.MemberRepository
import com.hopoong.kotlin_practice.exception.BusinessException
import com.hopoong.kotlin_practice.jwt.JwtTokenModel
import com.hopoong.kotlin_practice.jwt.JwtTokenProvider
import com.hopoong.kotlin_practice.response.ErrorCodeEnum
import io.jsonwebtoken.Claims
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {

    /*
     * 사용자 정보 조회
     */
    fun loadUserByUsername(params: LoginDto): JwtTokenModel {
        val member = memberRepository.findUserInfo(params.username)

        member?.let { it }
            ?: throw BusinessException(ErrorCodeEnum.ERROR_MEMBER_SAVE)

        if(!params.password.equals(member.password))
            throw BusinessException(ErrorCodeEnum.SERVER_ERROR)

        return jwtTokenProvider.generateCreateToken(member)
    }

    /*
     * access token 재발행
     */
    fun reissueAccessToken(token: String): String? {
        var claims: Claims = jwtTokenProvider.validateAndReleaseToken(token)
        return jwtTokenProvider.accessCreateToken(claims)
    }

    /*
     * refresh token 발행
     */
    fun createRefreshToken(token: String): HttpHeaders {
        var claims: Claims = jwtTokenProvider.validateAndReleaseToken(token)
        var cookie = jwtTokenProvider.generateRefreshTokenCookie(claims)

        val headers = HttpHeaders()
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString())
        return headers
    }

    /*
     * logout
     */
    fun logout(): HttpHeaders {
        var cookie = jwtTokenProvider.refreshTokenCookiExpired()

        val headers = HttpHeaders()
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString())
        return headers
    }

}