package com.hopoong.kotlin_practice.config

import com.hopoong.kotlin_practice.domain.member.Member
import com.hopoong.kotlin_practice.domain.member.MemberRepository
import com.hopoong.kotlin_practice.domain.member.Role
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener


@Configuration
class InitDataConfig(
    private val memberRepository: MemberRepository
) {

    @EventListener
    fun onApplicationEvent(event: ApplicationStartedEvent) {
        val member = Member(email = "test_user@gamil.com", password = "123", role = Role.USERM)
        memberRepository.save(member)
    }

}