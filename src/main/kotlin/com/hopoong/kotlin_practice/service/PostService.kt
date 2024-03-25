package com.hopoong.kotlin_practice.service

import com.hopoong.kotlin_practice.domain.post.Post
import com.hopoong.kotlin_practice.domain.post.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository
) {

    @Transactional(readOnly = true)
    fun findPosts(): MutableList<Post> {
        return this.postRepository.findAll()
    }
}