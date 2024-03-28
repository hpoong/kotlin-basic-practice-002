package com.hopoong.kotlin_practice.api

import com.hopoong.kotlin_practice.domain.member.Member
import com.hopoong.kotlin_practice.domain.member.MemberDto
import com.hopoong.kotlin_practice.domain.member.MemberUpdateDto
import com.hopoong.kotlin_practice.service.MemberService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class MemberController(
    private val memberService: MemberService
) {

    /*
     * 사용자 전체 조회
     */
    @GetMapping("/members")
    fun loadMemberList(@PageableDefault(size = 10) pageable: Pageable) : ResponseEntity<Page<Member>> {
        return ResponseEntity.status(HttpStatus.OK).body(this.memberService.loadMemberList(pageable))
    }

    /*
     * 사용자 조회
     */
    @GetMapping("/members/{id}")
    fun loadMemberInfo(@PathVariable id: Long) : ResponseEntity<MemberDto> {
        return ResponseEntity.status(HttpStatus.OK).body(this.memberService.loadMemberInfo(id))
    }

    /*
     * 사용자 삭제
     */
    @DeleteMapping("/members/{id}")
    fun deleteMemberInfo(@PathVariable id: Long) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.deleteMemberInfo(id))
    }

    /*
     * 사용자 추가
     */
    @PostMapping("/members")
    fun saveMemberInfo(@RequestBody memberDto: MemberDto) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.saveMemberInfo(memberDto))
    }

    /*
     * 사용자 변경
     */
    @PutMapping("/members")
    fun modifyMemberInfo(@RequestBody memberUpdateDto: MemberUpdateDto) : ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.modifyMemberInfo(memberUpdateDto))
    }

}