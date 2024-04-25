package com.hopoong.kotlin_practice.jwt

import com.hopoong.kotlin_practice.domain.member.Member
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import java.util.stream.Collectors
import javax.annotation.PostConstruct


@Component
class JwtTokenProvider {

    // claim key
    private val AUTHROLE_KEY = "auth"
    private val AUTHUSER_KEY = "user"

    // 유효시간
    private val ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60


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
            .setIssuedAt(Date(System.currentTimeMillis()))                                                      // 생성
            .setExpiration(Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))                    // 만료
            .compact()

        return JwtTokenModel(accessToken = accessToken)
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
    fun validateToken(token: String) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
        } catch (e: SecurityException) {
            throw Exception("잘못된 JWT 서명입니다.")
        } catch (e: MalformedJwtException) {
            throw Exception("잘못된 JWT 서명입니다.")
        } catch (e: ExpiredJwtException) {
            throw Exception("만료된 JWT 서명입니다.")
        } catch (e: UnsupportedJwtException) {
            throw Exception("지원되지 않는 JWT 서명입니다.")
        } catch (e: IllegalArgumentException) {
            throw Exception("JWT 토큰이 잘못되었습니다.")
        } catch (e: Exception) {
            throw Exception("JWT 오류")
        }
    }

}

