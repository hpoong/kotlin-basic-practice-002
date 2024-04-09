package com.hopoong.kotlin_practice.config.security

import com.hopoong.kotlin_practice.exception.BusinessException
import com.hopoong.kotlin_practice.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component


@Component
class SecurityAuthenticationProvider(
    private val memberService: MemberService
): AuthenticationProvider {

//    @Throws(Throwable::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials.toString()

        try {
            var securityMemberModel = memberService.loadUserByUsername(username)

            if(!securityMemberModel.password.equals(password)) {
                throw UsernameNotFoundException("")
            }

        } catch (ex : BusinessException) {
            ex.printStackTrace()
            throw UsernameNotFoundException("Id ERROR")
        } catch (ex : Exception) {
            ex.printStackTrace()
            throw UsernameNotFoundException("Pw ERROR")
        }

        return UsernamePasswordAuthenticationToken(username, password)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return true
    }
}