package com.hopoong.kotlin_practice.api

import com.hopoong.kotlin_practice.domain.login.LoginDto
import com.hopoong.kotlin_practice.domain.member.MemberDto
import com.hopoong.kotlin_practice.exception.BusinessException
import com.hopoong.kotlin_practice.response.ErrorCodeEnum
import com.hopoong.kotlin_practice.service.LoginService
import com.hopoong.kotlin_practice.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
class LoginController(
    private val loginService: LoginService,

) {

    /*
     * 로그인
     */
    @PostMapping("/login")
    fun saveMemberInfo(@RequestBody params: LoginDto) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.loadUserByUsername(params))
    }
}