package com.hopoong.kotlin_practice.service

import com.hopoong.kotlin_practice.domain.login.LoginDto
import com.hopoong.kotlin_practice.domain.member.MemberRepository
import com.hopoong.kotlin_practice.exception.BusinessException
import com.hopoong.kotlin_practice.jwt.JwtTokenModel
import com.hopoong.kotlin_practice.jwt.JwtTokenProvider
import com.hopoong.kotlin_practice.response.ErrorCodeEnum
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {

    /*
     * 사용자 수정
     */
    fun loadUserByUsername(params: LoginDto): JwtTokenModel {
        val member = memberRepository.findUserInfo(params.username)

        member?.let { it }
            ?: throw BusinessException(ErrorCodeEnum.ERROR_MEMBER_SAVE)

        if(!params.password.equals(member.password))
            throw BusinessException(ErrorCodeEnum.SERVER_ERROR)

        return jwtTokenProvider.generateCreateToken(member)
    }
}