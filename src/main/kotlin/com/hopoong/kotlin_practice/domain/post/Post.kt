package com.hopoong.kotlin_practice.domain.post

import com.hopoong.kotlin_practice.domain.AuditingEntity
import javax.persistence.*


@Entity
@Table(name = "acc_post")
class Post(

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    var id: Long,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = true)
    var content: String

): AuditingEntity() {




}