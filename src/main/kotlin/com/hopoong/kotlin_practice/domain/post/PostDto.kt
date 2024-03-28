package com.hopoong.kotlin_practice.domain.post

import com.hopoong.kotlin_practice.domain.member.MemberDto
import com.hopoong.kotlin_practice.domain.member.Role
import com.hopoong.kotlin_practice.domain.member.toEntity


open class PostDto(
    val title: String,
    val content: String,
    val member: MemberDto
)


fun PostDto.toEntity(): Post {
    return Post(
        title = this.title,
        content = this.content,
        member = this.member.toEntity()
    )
}


class PostUpdateDto(
    val id: Long,
    title: String,
    content: String,
    member: MemberDto
) : PostDto(title, content, member)