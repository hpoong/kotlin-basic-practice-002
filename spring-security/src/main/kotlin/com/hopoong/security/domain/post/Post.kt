package com.hopoong.security.domain.post

import com.hopoong.security.domain.AuditingEntity
import com.hopoong.security.domain.PostTypeConverter
import com.hopoong.security.domain.member.Member
import javax.persistence.*


@Entity
@Table(name = "acc_post")
class Post(
    title: String,
    content: String,
    member: Member,
    postType: PostTypeEnum
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

    @Convert(converter = PostTypeConverter::class)
    @Column(name = "post_type", nullable = true)
    var postType: PostTypeEnum = postType

    companion object {
        fun of(post: Post): PostDto {
            return PostDto(
                title = post.title,
                content = post.content,
                member = Member.of(post.member),
                postType = post.postType.info,
            )
        }
    }

}