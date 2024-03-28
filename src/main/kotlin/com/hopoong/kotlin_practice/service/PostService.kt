package com.hopoong.kotlin_practice.service

import com.hopoong.kotlin_practice.domain.post.Post
import com.hopoong.kotlin_practice.domain.post.PostDto
import com.hopoong.kotlin_practice.domain.post.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository
) {

    @Transactional(readOnly = true)
    fun findPosts(): MutableList<PostDto> {
        return postRepository.findAll()
            .map { Post.of(it) }.toMutableList()
    }

    fun loadPostInfo(id: Long): PostDto? {
        return postRepository.findById(id)
            .map { Post.of(it) }
            .orElseThrow { NoSuchElementException("해당 ID에 해당하는 글이 없습니다: $id") }
    }

    fun deletePostInfo(id: Long) {
        val postOptional = postRepository.findById(id)
        if (postOptional.isPresent) {
            postRepository.deleteById(id)
        } else {
            throw NoSuchElementException("해당 ID에 해당하는 글이 없습니다: $id")
        }
    }

    fun savePostInfo(memberDto: PostDto): Any? {
        return null
    }

    fun modifyPostInfo(memberUpdateDto: Any): Any? {
        return null
    }
}