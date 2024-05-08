package com.hopoong.kotlin_practice.jwt

import com.hopoong.kotlin_practice.domain.member.Member
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseCookie
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.annotation.PostConstruct


@Component
class JwtTokenProvider {

    // claim key
    private val AUTHROLE_KEY = "auth"
    private val AUTHUSER_KEY = "user"

    // 유효시간 = 1시간
    private val ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60

    // 유효시간 = 1분
//    private val ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60

    // 유효시간 = 7일
    private val REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7


    @Value("\${jwt.secret}")
    private lateinit var secretKey: String
    private lateinit var  key : Key

    @PostConstruct
    private fun init() {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    /*
     * 로그인 생성시 토큰 생성
     */
    fun generateCreateToken(param: Member): JwtTokenModel {

        // accessToken 토큰생성
        var accessToken = Jwts.builder()
            .claim(AUTHROLE_KEY, param.role)
            .claim(AUTHUSER_KEY, param.email)
            .signWith(key, SignatureAlgorithm.HS512)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
            .compact()

        return JwtTokenModel(accessToken = accessToken)
    }


    fun generateRefreshTokenCookie(param: Claims): ResponseCookie {

        // refreshToken 토큰생성
        var refreshToken = Jwts.builder()
            .claim(AUTHROLE_KEY, param.get(AUTHROLE_KEY))
            .claim(AUTHUSER_KEY, param.get(AUTHUSER_KEY))
            .signWith(key, SignatureAlgorithm.HS512)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
            .compact()

        return ResponseCookie.from("refresh-token", refreshToken)
            .httpOnly(true)
            .secure(false) // http
            .sameSite("None")
            .path("/")
            .build()
    }

    /*
     * accessToken 토큰생성
     */
    fun accessCreateToken(param: Claims): String? {

        return Jwts.builder()
            .claim(AUTHROLE_KEY, param.get(AUTHROLE_KEY))
            .claim(AUTHUSER_KEY, param.get(AUTHUSER_KEY))
            .signWith(key, SignatureAlgorithm.HS512)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
            .compact()
    }


    /*
     * 토큰을 받아서 Authentication 객체를 반환
     */
    fun getAuthentication(token: String): Authentication {
        val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
        val principal = JwtSecurityUser(claims)
        return UsernamePasswordAuthenticationToken(principal, token, principal.authorities)
    }

    /*
     * 토큰의 유효성 검사
     */
    fun validateAndReleaseToken(token: String): Claims {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
        } catch (e: SecurityException) {
            throw Exception("잘못된 JWT 서명입니다.")
        } catch (e: MalformedJwtException) {
            throw Exception("잘못된 JWT 서명입니다.")
        } catch (e: ExpiredJwtException) {
            throw e
        } catch (e: UnsupportedJwtException) {
            throw Exception("지원되지 않는 JWT 서명입니다.")
        } catch (e: IllegalArgumentException) {
            throw Exception("JWT 토큰이 잘못되었습니다.")
        } catch (e: Exception) {
            throw Exception("JWT 오류")
        }
    }

    /*
     * refreshToken 만료처리
     */
    fun refreshTokenCookiExpired(): ResponseCookie {
        return ResponseCookie.from("refresh-token", "").maxAge(1).build()
    }

}

