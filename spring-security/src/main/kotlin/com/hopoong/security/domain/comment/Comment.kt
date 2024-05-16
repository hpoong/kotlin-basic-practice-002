package com.hopoong.security.domain.comment

import com.hopoong.security.domain.AuditingEntity
import com.hopoong.security.domain.post.Post
import javax.persistence.*


@Entity
@Table(name = "acc_comment")
class Comment(
    title: String,
    content: String,
    post: Post
):AuditingEntity()  {

    @Column(name = "title", nullable = false)
    var title: String = title
        protected set

    @Column(name = "content", nullable = false)
    var content: String = content
        protected set


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Post::class)
    var post: Post = post
        protected set

}