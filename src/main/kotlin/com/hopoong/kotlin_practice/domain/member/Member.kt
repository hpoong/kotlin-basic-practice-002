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

    //    @Id
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //    var id: Long,

    @Column(name = "email", nullable = false)
    var email: String = email
        protected set

    @Column(name = "password", nullable = false)
    var password: String = password
        protected set

    @Enumerated(EnumType.STRING)
    var role: Role = role
        protected set
}

enum class Role{
    USERM, ADMIN
}

