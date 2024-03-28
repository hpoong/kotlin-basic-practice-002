package com.hopoong.kotlin_practice.api

import com.hopoong.kotlin_practice.domain.post.PostDto
import com.hopoong.kotlin_practice.domain.post.PostUpdateDto
import com.hopoong.kotlin_practice.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PostController(
    private val postService: PostService
) {

    @GetMapping("/posts")
    fun loadPostList(): ResponseEntity<MutableList<PostDto>> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findPosts());
    }


    /*
     * 사용자 조회
     */
    @GetMapping("/posts/{id}")
    fun loadPostInfo(@PathVariable id: Long) : ResponseEntity<PostDto> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.loadPostInfo(id))
    }

    /*
     * 사용자 삭제
     */
    @DeleteMapping("/posts/{id}")
    fun deletePostInfo(@PathVariable id: Long) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.deletePostInfo(id))
    }

    /*
     * 사용자 추가
     */
    @PostMapping("/posts")
    fun savePostInfo(@RequestBody memberDto: PostDto) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.savePostInfo(memberDto))
    }

    /*
     * 사용자 변경
     */
    @PutMapping("/posts")
    fun modifyPostInfo(@RequestBody memberUpdateDto: PostUpdateDto) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.modifyPostInfo(memberUpdateDto))
    }

}