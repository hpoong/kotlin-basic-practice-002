package com.hopoong.kotlin_practice.config

import com.hopoong.kotlin_practice.domain.member.*
import com.hopoong.kotlin_practice.domain.post.Post
import com.hopoong.kotlin_practice.domain.post.PostDto
import com.hopoong.kotlin_practice.domain.post.PostRepository
import com.hopoong.kotlin_practice.domain.post.toEntity
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener


@Configuration
class InitDataConfig(
    private val memberRepository: MemberRepository,
    private val postRepository : PostRepository
) {

    @EventListener
    fun onApplicationEvent(event: ApplicationStartedEvent) {
//        val postArr = mutableListOf<Post>()
//        for(i in 1..50) {
//            var member = memberRepository.save(Member(email = "email-${i}", role = Role.USER, password = "123"));
//            memberRepository.save(member);
//
//            postArr.add(
//                Post(title = "title - ${i}", content = "content - ${i}", member = member)
//            )
//        }
//        postRepository.saveAll(postArr)
    }

}