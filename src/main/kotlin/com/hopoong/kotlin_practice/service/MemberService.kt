package com.hopoong.kotlin_practice.service

import com.hopoong.kotlin_practice.domain.member.Member
import com.hopoong.kotlin_practice.domain.member.MemberDto
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
     * pageable - 사용자 전체 조회
     */
    @Transactional(readOnly = true)
    fun findAll(pageable: Pageable): Page<Member> {
        return memberRepository.findMembers(pageable)
    }


    /*
     * 사용자 전체 조회
     */
    @Transactional(readOnly = true)
    fun findAlls(): MutableList<MemberDto> {
        return memberRepository.findAll()
            .map { Member.of(it) }.toMutableList()
    }


    /*
     * 특정 사용자 조회
     */
    @Transactional(readOnly = true)
    fun findMember(id: Long): MemberDto {
        return memberRepository.findById(id)
            .map { Member.of(it) }
            .orElseThrow { NoSuchElementException("해당 ID에 해당하는 회원이 없습니다: $id") }
    }

}