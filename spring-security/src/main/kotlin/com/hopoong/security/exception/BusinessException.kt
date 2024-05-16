package com.hopoong.security.exception

import com.hopoong.security.response.CommonCode

class BusinessException: Exception {

    val code: CommonCode
    override val message: String


    constructor(code: CommonCode, message: String) : super() {
        this.code = code
        this.message = message
    }
}