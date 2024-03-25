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

        val memberArr = mutableListOf<Member>();
        val postArr = mutableListOf<Post>();

        for(i in 1..50) {
//            memberArr.add(
//                MemberDto(email = "test", role = Role.USERM, password = "1111").toEntity()
//            )
//            memberRepository.saveAll(memberArr)

            var member = memberRepository.save(MemberDto(email = "email - ${i}", role = Role.USERM, password = "password - ${i}").toEntity());
            memberRepository.save(member);

            postArr.add(
                PostDto(title = "title - ${i}", content = "content - ${i}", member = member).toEntity()
            )
        }

        memberRepository.saveAll(memberArr)
        postRepository.saveAll(postArr)
    }

}