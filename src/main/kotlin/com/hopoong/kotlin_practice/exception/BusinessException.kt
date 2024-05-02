package com.hopoong.kotlin_practice.exception

import com.hopoong.kotlin_practice.response.CommonCode

class BusinessException: Exception {

    val code: CommonCode
    override val message: String


    constructor(code: CommonCode, message: String) : super() {
        this.code = code
        this.message = message
    }
}