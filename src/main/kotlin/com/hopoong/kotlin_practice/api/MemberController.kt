package com.hopoong.kotlin_practice.api

import com.hopoong.kotlin_practice.domain.member.Member
import com.hopoong.kotlin_practice.service.MemberService
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/members")
    fun findAll() :MutableList<Member> {
        return memberService.findAll()
    }

}