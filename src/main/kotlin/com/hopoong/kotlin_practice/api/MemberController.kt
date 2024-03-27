package com.hopoong.kotlin_practice.api

import com.hopoong.kotlin_practice.domain.member.Member
import com.hopoong.kotlin_practice.domain.member.MemberDto
import com.hopoong.kotlin_practice.service.MemberService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService
) {

    /*
     * 사용자 조회
     */
    @GetMapping("/members")
    fun findAll(@PageableDefault(size = 10) pageable: Pageable) : ResponseEntity<Page<Member>> {
        return ResponseEntity.status(HttpStatus.OK).body(this.memberService.findAll(pageable))
    }

    @GetMapping("/members/{id}")
    fun findMember(@PathVariable id: Long) : ResponseEntity<MemberDto> {
        return ResponseEntity.status(HttpStatus.OK).body(this.memberService.findMember(id))
    }

}