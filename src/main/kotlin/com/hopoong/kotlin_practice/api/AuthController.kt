package com.hopoong.kotlin_practice.api

import com.hopoong.kotlin_practice.domain.login.LoginDto
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
        var token = authService.loadUserByUsername(params)

        return ResponseEntity.status(HttpStatus.OK)
            .headers(authService.createRefreshToken(token.accessToken)).body(token)
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