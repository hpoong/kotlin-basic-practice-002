package com.hopoong.kotlin_practice.domain.post

import com.hopoong.kotlin_practice.domain.AuditingEntity
import com.hopoong.kotlin_practice.domain.member.Member
import com.hopoong.kotlin_practice.domain.member.MemberDto
import javax.persistence.*


@Entity
@Table(name = "acc_post")
class Post(
    title: String,
    content: String,
    member: Member
): AuditingEntity() {

    @Column(name = "title", nullable = false)
    var title: String = title
//        protected set

    @Column(name = "content", nullable = true)
    var content: String = content
//        protected set


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member::class)
    var member: Member = member
//        protected set

    companion object {
        fun of(post: Post): PostDto {
            return PostDto(
                title = post.title,
                content = post.content,
                member = Member.of(post.member)
            )
        }
    }

}