package com.hopoong.kotlin_practice.domain.member.model

import com.hopoong.kotlin_practice.domain.member.Member
import com.hopoong.kotlin_practice.domain.member.MemberDto
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityMemberModel(
    member: Member
): UserDetails {

    var member: Member = member
        private set

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        var authorityArr :MutableCollection<GrantedAuthority> = ArrayList()
        authorityArr.add(GrantedAuthority { "ROLE_" + member.role })
        return authorityArr
    }

    override fun getPassword(): String {
        return member.password
    }

    override fun getUsername(): String {
        return member.email
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


    companion object {
        fun ofDto(member: Member): SecurityMemberModel {
            return SecurityMemberModel(member)
        }
    }


}