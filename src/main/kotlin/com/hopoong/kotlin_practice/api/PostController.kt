package com.hopoong.kotlin_practice.api

import com.hopoong.kotlin_practice.domain.post.PostDto
import com.hopoong.kotlin_practice.domain.post.PostRequestDto
import com.hopoong.kotlin_practice.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class PostController(
    private val postService: PostService
) {

    /*
     * 게시글 전체 조회
     */
    @GetMapping("/posts")
    fun loadPostList(): ResponseEntity<MutableList<PostDto>> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findPosts());
    }

    /*
     * 게시글 조회
     */
    @GetMapping("/posts/{id}")
    fun loadPostInfo(@PathVariable id: Long) : ResponseEntity<PostDto> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.loadPostInfo(id))
    }

    /*
     * 게시글 삭제
     */
    @DeleteMapping("/posts/{id}")
    fun deletePostInfo(@PathVariable id: Long) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.deletePostInfo(id))
    }

    /*
     * 게시글 추가
     */
    @PostMapping("/posts")
    fun savePostInfo(
//        @RequestPart(value = "saveParam", required = true) saveDto: PostRequestDto.PostSaveDto,
//        @RequestPart(value = "fileList", required = false) fileList: MutableList<MultipartFile>,
        @RequestBody saveDto: PostRequestDto.PostSaveDto) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.savePostInfo(saveDto))
    }

    /*
     * 게시글 변경
     */
    @PutMapping("/posts")
    fun modifyPostInfo(@RequestBody updateDto: PostRequestDto.PostUpdateDto) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(postService.modifyPostInfo(updateDto))
    }

}