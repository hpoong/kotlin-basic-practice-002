package com.hopoong.security.config

import com.hopoong.security.domain.member.MemberRepository
import com.hopoong.security.domain.post.PostRepository
import org.springframework.context.annotation.Configuration


@Configuration
class InitDataConfig(
    private val memberRepository: MemberRepository,
    private val postRepository : PostRepository
) {

//    @EventListener
//    fun onApplicationEvent(event: ApplicationStartedEvent) {
//        val postArr = mutableListOf<Post>()
//        for(i in 1..50) {
//            var member = memberRepository.save(Member(email = "email-${i}", role = Role.USER, password = "123"));
//            memberRepository.save(member);
//
//            postArr.add(
//                Post(title = "title - ${i}", content = "content - ${i}", member = member, PostTypeEnum.C_004)
//            )
//        }
//        postRepository.saveAll(postArr)
//    }

}