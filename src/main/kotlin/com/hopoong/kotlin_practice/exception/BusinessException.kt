package com.hopoong.kotlin_practice.exception

import com.hopoong.kotlin_practice.response.ErrorCodeEnum

class BusinessException: Exception {

    val errorCodeEnum: ErrorCodeEnum

    constructor(errorCodeEnum: ErrorCodeEnum) : super() {
        this.errorCodeEnum = errorCodeEnum
    }
}