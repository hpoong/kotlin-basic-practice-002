package com.hopoong.kotlin_practice.domain.post

import com.hopoong.kotlin_practice.domain.AuditingEntity
import com.hopoong.kotlin_practice.domain.member.Member
import javax.persistence.*


@Entity
@Table(name = "acc_post")
class Post(

    title: String,
    content: String,
    member: Member
): AuditingEntity() {

    //    @Id
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //    var id: Long,

    @Column(name = "title", nullable = false)
    var title: String = title
        protected set

    @Column(name = "content", nullable = true)
    var content: String = content
        protected set


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member::class)
    var member: Member = member
        protected set

}