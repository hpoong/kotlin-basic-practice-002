package com.hopoong.security.domain.post

import com.hopoong.security.domain.member.MemberDto


open class PostDto(
    val title: String,
    val content: String,
    val member: MemberDto,
    val postType: String
) {
}



class PostRequestDto {

    class PostUpdateDto(
        val id: Long,
        val title: String,
        val content: String,
        val memberId: Long,
    )


    class PostSaveDto(
        val memberId: Long,
        val title: String,
        val content: String,
        val postType: PostTypeEnum,
    )
}
