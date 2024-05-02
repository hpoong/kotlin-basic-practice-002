package com.hopoong.kotlin_practice.service

import com.hopoong.kotlin_practice.domain.login.LoginDto
import com.hopoong.kotlin_practice.domain.member.MemberDto
import com.hopoong.kotlin_practice.domain.member.MemberRepository
import com.hopoong.kotlin_practice.domain.member.toEntity
import com.hopoong.kotlin_practice.exception.BusinessException
import com.hopoong.kotlin_practice.jwt.JwtTokenModel
import com.hopoong.kotlin_practice.jwt.JwtTokenProvider
import com.hopoong.kotlin_practice.response.*
import com.hopoong.kotlin_practice.util.CookieUtil
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    /*
     * 사용자 정보 조회
     */
    fun loadUserByUsername(params: LoginDto): JwtTokenModel {
        val member = memberRepository.findUserInfo(params.username)

        member?.let { it }
            ?: throw BusinessException(CommonCode.AUTH, "사용자가 존재하지 않습니다.")

        if(!passwordEncoder.matches(params.password, member.password))
            throw BusinessException(CommonCode.AUTH, "비밀번호를 확인하세요")

        return jwtTokenProvider.generateCreateToken(member)
    }

    /*
     * access token 재발행
     */
    fun reissueAccessToken(token: String): String? {
        var claims = jwtTokenProvider.validateAndReleaseToken(token)
        return jwtTokenProvider.accessCreateToken(claims)
    }

    /*
     * refresh token 발행
     */
    fun createRefreshToken(token: String): HttpHeaders {
        var claims = jwtTokenProvider.validateAndReleaseToken(token)
        var cookie = jwtTokenProvider.generateRefreshTokenCookie(claims)
        return CookieUtil().createCookieHeaders(cookie)
    }

    /*
     * logout
     */
    fun logout(): HttpHeaders {
        var cookie = jwtTokenProvider.refreshTokenCookiExpired()
        return CookieUtil().createCookieHeaders(cookie)
    }

    /*
     * 회원가입
     */
    @Transactional
    fun signup(params: MemberDto): CommonResponse {
        try {
            params.password = passwordEncoder.encode(params.password)
            return SuccessResponses(
                CommonCode.AUTH, memberRepository.save(params.toEntity())
            )
        } catch (ex: Exception) {
            log.error("Exception :::: ${ex.message}")
            return ErrorResponse(CommonCode.AUTH, "회원가입 실패")
        }
    }

}