package com.hopoong.kotlin_practice.service

import com.hopoong.kotlin_practice.domain.member.Member
import com.hopoong.kotlin_practice.domain.member.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.data.domain.Pageable

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    /*
     * 사용자 조회
     */
    @Transactional(readOnly = true)
    fun findAll(pageable: Pageable): Page<Member> {
        return memberRepository.findMembers(pageable)
    }
}