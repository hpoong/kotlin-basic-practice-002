package com.hopoong.kotlin_practice.response

class ErrorResponse(
    val status: String = "Failure",
    var code: String,
    var message: String,
) {
    constructor(successCodeEnum: SuccessCodeEnum) : this(
        code = successCodeEnum.code,
        message = successCodeEnum.message,
    )
}