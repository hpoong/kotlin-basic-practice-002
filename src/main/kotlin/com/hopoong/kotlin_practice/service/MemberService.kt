package com.hopoong.kotlin_practice.service

import com.hopoong.kotlin_practice.domain.member.*
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
    fun loadMemberList(pageable: Pageable): Page<Member> {
        return memberRepository.findMembers(pageable)
    }

    /*
     * 특정 사용자 조회
     */
    @Transactional(readOnly = true)
    fun loadMemberInfo(id: Long): MemberDto {
        return memberRepository.findById(id)
            .map { Member.of(it) }
            .orElseThrow { NoSuchElementException("해당 ID에 해당하는 회원이 없습니다: $id") }
    }

    /*
     * 사용자 삭제
     */
    @Transactional
    fun deleteMemberInfo(id: Long) {
        val memberOptional = memberRepository.findById(id)
        if (memberOptional.isPresent) {
            memberRepository.deleteById(id)
        } else {
            throw NoSuchElementException("해당 ID에 해당하는 회원이 없습니다: $id")
        }
    }

    /*
     * 사용자 저장
     */
    @Transactional
    fun saveMemberInfo(memberDto: MemberDto): Member {
        return memberRepository.save(memberDto.toEntity())
    }

    /*
     * 사용자 수정
     */
    @Transactional
    fun modifyMemberInfo(memberUpdateDto: MemberUpdateDto): Member {
        val memberEntity = memberRepository.findById(memberUpdateDto.id).orElseThrow { NoSuchElementException("해당 ID에 해당하는 회원이 없습니다: ${memberUpdateDto.id}") }

        memberEntity.apply {
            email = memberUpdateDto.email
            password = memberUpdateDto.password
            role = memberUpdateDto.role
        }

        return memberRepository.save(memberEntity)
    }



}