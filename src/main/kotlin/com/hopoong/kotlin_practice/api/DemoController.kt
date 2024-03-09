package com.hopoong.kotlin_practice.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController {

    @GetMapping
    fun demo(): String {
        return "demo-test"
    }
}