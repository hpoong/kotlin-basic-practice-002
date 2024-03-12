package com.hopoong.kotlin_practice.domain.member

import javax.persistence.*

@Entity
@Table(name = "acc_member")
class Member(

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    var id: Long,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Enumerated(EnumType.STRING)
    var role: Role
) {

}

enum class Role{
    USERM, ADMIN
}

