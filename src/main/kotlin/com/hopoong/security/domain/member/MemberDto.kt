package com.hopoong.security.domain.member

import javax.validation.constraints.NotBlank

open class MemberDto(

    @field:NotBlank(message = "required email")
    val email: String,

    @field:NotBlank(message = "required password")
    var password: String,

    val role: Role?
) {

    fun toEntity(): Member {
        return Member(
            email = this.email,
            password = this.password,
            role = this.role ?: Role.USER
        )
    }
}


class MemberUpdateDto(
    val id: Long,
    email: String,
    password: String,
    role: Role?
) : MemberDto(email, password, role)