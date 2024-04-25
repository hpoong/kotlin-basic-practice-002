package com.hopoong.kotlin_practice.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {


    @GetMapping("/test")
    fun demo(): String {
        return "user-test"
    }
}