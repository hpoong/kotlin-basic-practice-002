package com.hopoong.kotlin_practice.service

import com.hopoong.kotlin_practice.domain.member.*
import com.hopoong.kotlin_practice.exception.BusinessException
import com.hopoong.kotlin_practice.response.CommonCode
import com.hopoong.kotlin_practice.response.CommonResponse
import com.hopoong.kotlin_practice.response.ErrorResponse
import com.hopoong.kotlin_practice.response.SuccessResponses
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    /*
     * 사용자 조회
     */
    @Transactional(readOnly = true)
    fun loadMemberList(pageable: Pageable): CommonResponse {
        return SuccessResponses(CommonCode.MEMBER, memberRepository.findMembers(pageable))
    }

    /*
     * 특정 사용자 조회
     */
    @Transactional(readOnly = true)
    fun loadMemberInfo(id: Long): CommonResponse {
        return SuccessResponses(
            CommonCode.MEMBER,
            memberRepository.findById(id)
                .map { Member.of(it) }
                .orElseThrow {
                    log.error("Exception :::: 해당 ID에 해당하는 회원이 없습니다 ::::: $id")
                    throw BusinessException(CommonCode.MEMBER, "해당 ID에 해당하는 회원이 없습니다")
                }
        )
    }


    /*
     * 사용자 삭제
     */
    @Transactional
    fun deleteMemberInfo(id: Long): CommonResponse {
        val memberOptional = memberRepository.findById(id)
        if (memberOptional.isPresent) {
            memberRepository.deleteById(id)
            return SuccessResponses(CommonCode.MEMBER)
        } else {
            log.error("Exception :::: 해당 ID에 해당하는 회원이 없습니다 ::::: $id")
            return ErrorResponse(CommonCode.MEMBER, "해당 ID에 해당하는 회원이 없습니다")
        }
    }


    /*
     * 사용자 저장
     */
    @Transactional
    fun saveMemberInfo(memberDto: MemberDto): CommonResponse {
        try {
            return SuccessResponses(
                CommonCode.MEMBER, memberRepository.save(memberDto.toEntity())
            )
        } catch (ex: Exception) {
            log.error("Exception :::: ${ex.message}")
            return ErrorResponse(CommonCode.MEMBER, "사용자 저장 실패")
        }
    }


    /*
     * 사용자 수정
     */
    @Transactional
    fun modifyMemberInfo(memberUpdateDto: MemberUpdateDto): CommonResponse {
        val memberEntity = memberRepository.findById(memberUpdateDto.id!!)
            .orElseThrow {
                log.error("Exception :::: 해당 ID에 해당하는 회원이 없습니다 ::::: ${memberUpdateDto.id}")
                throw BusinessException(CommonCode.MEMBER, "해당 ID에 해당하는 회원이 없습니다")
            }


        memberEntity.apply {
            email = memberUpdateDto.email ?: ""
            password = memberUpdateDto.password ?: ""
            role = memberUpdateDto.role ?: Role.USER
        }

        return SuccessResponses(
            CommonCode.MEMBER, memberRepository.save(memberEntity)
        )
    }



}