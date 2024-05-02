package com.hopoong.kotlin_practice.api

import com.hopoong.kotlin_practice.domain.login.LoginDto
import com.hopoong.kotlin_practice.domain.member.MemberDto
import com.hopoong.kotlin_practice.response.CommonResponse
import com.hopoong.kotlin_practice.response.ErrorResponse
import com.hopoong.kotlin_practice.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class AuthController(
    private val authService: AuthService,
) {

    /*
     * 로그인
     */
    @PostMapping("/login")
    fun saveMemberInfo(@RequestBody params: LoginDto) : ResponseEntity<Any> {
        println(params.toString())
        var token = authService.loadUserByUsername(params)

        return ResponseEntity.status(HttpStatus.OK)
            .headers(authService.createRefreshToken(token.accessToken)).body(token)
    }


    /*
     * 회원가입
     */
    @PostMapping("/signup")
    fun signup(@RequestBody params: MemberDto) : ResponseEntity<CommonResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(authService.signup(params))
    }

    /*
     * refresh token 재발행
     */
    @GetMapping("/refresh-token")
    fun reissueAccessToken(@CookieValue(value = "refresh-token", required = true) refreshToken: String) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(authService.reissueAccessToken(refreshToken))
    }

    /*
     * logout
     */
    @GetMapping("/logout")
    fun logout() : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK)
            .headers(authService.logout()).body("successfully")
    }
}