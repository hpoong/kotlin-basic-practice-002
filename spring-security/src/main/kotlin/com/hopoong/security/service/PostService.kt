package com.hopoong.security.service

import com.hopoong.security.domain.member.MemberRepository
import com.hopoong.security.domain.post.*
import net.okihouse.autocomplete.repository.AutocompleteRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository,
    private val autocompleteRepository: AutocompleteRepository
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

        return Post.of(postRepository.save(Post(postSaveDto.title, postSaveDto.content, memberEntity, postSaveDto.postType)))
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


    /*
     * 자동완성 게시글 제목 조회
     */
    @Cacheable(cacheNames = ["posts"])
    @Transactional(readOnly = true)
    fun autocompletePostTitle(word: String): List<String> {

        // 게시글 조회
        val postTitles = findPosts().map { it.title }
        postTitles.forEach { autocompleteRepository.add(it) }

        // redis 데이터 조회
        val completions = autocompleteRepository.complete(word)
        return completions.map { it.value }.sortedWith(compareByDescending { it })
    }
}