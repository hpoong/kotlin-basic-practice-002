package com.hopoong.kotlin_practice.service

import com.hopoong.kotlin_practice.domain.member.MemberRepository
import com.hopoong.kotlin_practice.domain.post.Post
import com.hopoong.kotlin_practice.domain.post.PostDto
import com.hopoong.kotlin_practice.domain.post.PostRepository
import com.hopoong.kotlin_practice.domain.post.PostRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.util.*
import kotlin.NoSuchElementException

@Service
class PostService(
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository,
) {

    /*
     * 게시글 전체 조회
     */
    @Transactional(readOnly = true)
    fun findPosts(): MutableList<PostDto> {
        return postRepository.findAllPosts().map { Post.of(it) }.toMutableList()
    }

    /*
     * 게시글 조회
     */
    @Transactional(readOnly = true)
    fun loadPostInfo(id: Long): PostDto? {
        return postRepository.findById(id)
            .map { Post.of(it) }
            .orElseThrow { NoSuchElementException("해당 ID에 해당하는 게시글이 없습니다: $id") }
    }

    /*
     * 게시글 삭제
     */
    @Transactional
    fun deletePostInfo(id: Long) {
        val postOptional = postRepository.findById(id)
        if (postOptional.isPresent) {
            postRepository.deleteById(id)
        } else {
            throw NoSuchElementException("해당 ID에 해당하는 게시글이 없습니다: $id")
        }
    }

    /*
     * 게시글 추가
     */
    @Transactional
    fun savePostInfo(postSaveDto: PostRequestDto.PostSaveDto): PostDto {
        var memberEntity = memberRepository.findById(postSaveDto.memberId)
            .orElseThrow { NoSuchElementException("해당 ID에 해당하는 게시글이 없습니다: ${postSaveDto.memberId}") }

//        if (fileList != null) {
//            for (file in fileList) {
//                val uuid = UUID.randomUUID()
//                val originFileName = file.originalFilename
//                val saveFileName = uuid.toString() + originFileName
//                val filePath: Path = FileUtil.getPath()
//                val location = filePath.resolve(Objects.requireNonNull(saveFileName))
//                Files.copy(file.inputStream, location, StandardCopyOption.REPLACE_EXISTING)
//                savedFileList.add(location)
//
//                // save 로직
//
//            }
//        }


        return Post.of(postRepository.save(Post(postSaveDto.title, postSaveDto.content, memberEntity)))
    }

    /*
     * 게시글 변경
     */
    @Transactional
    fun modifyPostInfo(postUpdateDto: PostRequestDto.PostUpdateDto): PostDto {
        var postEntity = postRepository.findById(postUpdateDto.id)
            .orElseThrow { NoSuchElementException("해당 ID에 해당하는 게시글이 없습니다: ${postUpdateDto.id}") }

        if (postEntity.member.id != postUpdateDto.memberId) {
            val memberEntity = memberRepository.findById(postUpdateDto.memberId)
                .orElseThrow { NoSuchElementException("해당 ID에 해당하는 회원이 없습니다: ${postUpdateDto.memberId}") }
            postEntity.member = memberEntity
        }

        postEntity.apply {
            content =  postUpdateDto.content
            title = postUpdateDto.title
        }

        return Post.of(postRepository.save(postEntity))
    }
}