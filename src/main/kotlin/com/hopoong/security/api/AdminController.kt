package com.hopoong.security.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController {

    @GetMapping("/test")
    fun demo(): String {
        return "admin-test"
    }
}