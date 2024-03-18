package com.hopoong.kotlin_practice.service

import com.hopoong.kotlin_practice.domain.member.Member
import com.hopoong.kotlin_practice.domain.member.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    @Transactional(readOnly = true)
    fun findAll(): MutableList<Member> = memberRepository.findAll()
}