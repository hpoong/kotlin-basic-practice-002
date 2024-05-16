package com.hopoong.security.jwt

import io.jsonwebtoken.Claims
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtSecurityUser(
    claims: Claims
): UserDetails {

    var claims: Claims = claims
        private set

    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_" + claims.get("auth").toString()))
    }

    override fun getPassword(): String {
        return ""
    }

    override fun getUsername(): String {
        return claims.get("user").toString()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}