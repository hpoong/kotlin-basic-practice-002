package com.hopoong.kotlin_practice.domain.member

data class MemberDto(
    val email: String,
    val password: String,
    val role: Role
)

fun MemberDto.toEntity(): Member {
    return Member(
        email = this.email,
        password = this.password,
        role = this.role
    )
}