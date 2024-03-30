package com.hopoong.kotlin_practice.domain.member

import javax.validation.constraints.NotBlank

open class MemberDto(

    @field:NotBlank(message = "required email")
    val email: String?,

    @field:NotBlank(message = "required password")
    val password: String?,

    val role: Role?
)

fun MemberDto.toEntity(): Member {
    return Member(
        email = this.email ?: "",
        password = this.password ?: "",
        role = this.role ?: Role.USERM
    )
}

class MemberUpdateDto(
    val id: Long,
    email: String?,
    password: String?,
    role: Role?
) : MemberDto(email, password, role)