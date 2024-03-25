package com.hopoong.kotlin_practice.domain.post

import com.hopoong.kotlin_practice.domain.member.Member


data class PostDto(
    val title: String,
    val content: String,
    val member: Member
)



fun PostDto.toEntity(): Post {
    return Post(
        title = this.title,
        content = this.content,
        member = this.member
    )
}