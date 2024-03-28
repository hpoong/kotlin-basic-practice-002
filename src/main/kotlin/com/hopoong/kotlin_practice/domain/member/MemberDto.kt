package com.hopoong.kotlin_practice.domain.member

open class MemberDto(
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

class MemberUpdateDto(
    val id: Long,
    email: String,
    password: String,
    role: Role
) : MemberDto(email, password, role)