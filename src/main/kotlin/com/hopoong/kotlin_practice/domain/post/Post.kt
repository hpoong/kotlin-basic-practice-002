package com.hopoong.kotlin_practice.domain.post

import com.hopoong.kotlin_practice.domain.AuditingEntity
import javax.persistence.*


@Entity
@Table(name = "acc_post")
class Post(

    title: String,
    content: String

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
}