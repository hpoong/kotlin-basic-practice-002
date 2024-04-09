package com.hopoong.kotlin_practice.service

import com.hopoong.kotlin_practice.domain.member.*
import com.hopoong.kotlin_practice.domain.member.model.SecurityMemberModel
import com.hopoong.kotlin_practice.exception.BusinessException
import com.hopoong.kotlin_practice.response.ErrorCodeEnum
import com.hopoong.kotlin_practice.response.ErrorResponse
import com.hopoong.kotlin_practice.response.SuccessCodeEnum
import com.hopoong.kotlin_practice.response.SuccessResponses
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
): UserDetailsService {

    private val log = LoggerFactory.getLogger(this::class.java)

    /*
     * 사용자 조회
     */
    @Transactional(readOnly = true)
    fun loadMemberList(pageable: Pageable): SuccessResponses {
        return SuccessResponses(SuccessCodeEnum.SUCCESS_MEMBER_SEARCH, memberRepository.findMembers(pageable))
    }

    /*
     * 특정 사용자 조회
     */
    @Transactional(readOnly = true)
    fun loadMemberInfo(id: Long): SuccessResponses {
        return SuccessResponses(
            SuccessCodeEnum.SUCCESS_MEMBER_SEARCH,
            memberRepository.findById(id)
                .map { Member.of(it) }
                .orElseThrow {
                    log.error("Exception :::: 해당 ID에 해당하는 회원이 없습니다 ::::: $id")
                    throw BusinessException(ErrorCodeEnum.ERROR_MEMBER_SEARCH)
                }
        )
    }



    /*
     * 사용자 삭제
     */
    @Transactional
    fun deleteMemberInfo(id: Long): SuccessResponses {
        val memberOptional = memberRepository.findById(id)
        if (memberOptional.isPresent) {
            memberRepository.deleteById(id)
            return SuccessResponses(SuccessCodeEnum.SUCCESS_MEMBER_SAVE)
        } else {
            log.error("Exception :::: 해당 ID에 해당하는 회원이 없습니다 ::::: $id")
            throw BusinessException(ErrorCodeEnum.ERROR_MEMBER_SEARCH)
        }
    }


    /*
     * 사용자 저장
     */
    @Transactional
    fun saveMemberInfo(memberDto: MemberDto): Any {
        try {
            return SuccessResponses(
                SuccessCodeEnum.SUCCESS_MEMBER_SAVE, memberRepository.save(memberDto.toEntity())
            )
        } catch (ex: Exception) {
            log.error("Exception :::: ${ex.message}")
            return ErrorResponse(ErrorCodeEnum.ERROR_MEMBER_SAVE)
        }
    }



    /*
     * 사용자 수정
     */
    @Transactional
    fun modifyMemberInfo(memberUpdateDto: MemberUpdateDto): SuccessResponses {
        val memberEntity = memberRepository.findById(memberUpdateDto.id!!)
            .orElseThrow {
                log.error("Exception :::: 해당 ID에 해당하는 회원이 없습니다 ::::: ${memberUpdateDto.id}")
                throw BusinessException(ErrorCodeEnum.ERROR_MEMBER_SEARCH)
            }


        memberEntity.apply {
            email = memberUpdateDto.email ?: ""
            password = memberUpdateDto.password ?: ""
            role = memberUpdateDto.role ?: Role.USER
        }

        return SuccessResponses(
            SuccessCodeEnum.SUCCESS_MEMBER_UPDATE, memberRepository.save(memberEntity)
        )
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val member = memberRepository.findMembers1(username)
        return member?.let { SecurityMemberModel(it) }
            ?: throw BusinessException(ErrorCodeEnum.ERROR_MEMBER_SEARCH)
    }
}