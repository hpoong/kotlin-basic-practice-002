package com.hopoong.kotlin_practice.domain.member

import com.hopoong.kotlin_practice.domain.AuditingEntity
import javax.persistence.*

@Entity
@Table(name = "acc_member")
class Member(
    email: String,
    password: String,
    role: Role
): AuditingEntity() {

    @Column(name = "email", nullable = false)
    var email: String = email
//        protected set

    @Column(name = "password", nullable = false)
    var password: String = password
//        protected set

    @Enumerated(EnumType.STRING)
    var role: Role = role
//        protected set


    companion object {
        fun of(member: Member): MemberDto {
            return MemberDto(
                email = member.email,
                password = member.password,
                role = member.role
            )
        }
    }


    // protected set 제거 시
//    fun updateMemberEntity(memberUpdateDto: MemberUpdateDto): Member {
//        email = memberUpdateDto.email
//        password = memberUpdateDto.email
//        role = memberUpdateDto.role
//        return this
//    }
}

enum class Role{
    USER, ADMIN
}

