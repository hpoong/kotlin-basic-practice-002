package com.hopoong.kotlin_practice.response

class SuccessResponses(
    val success: Boolean = true,
    var code: String,
    var message: String,
    var data: Any?
) {


    constructor(successCodeEnum: SuccessCodeEnum) : this(
        code = successCodeEnum.code,
        message = successCodeEnum.message,
        data = null
    )

    constructor(successCodeEnum: SuccessCodeEnum, data: Any) : this(
        code = successCodeEnum.code,
        message = successCodeEnum.message,
        data = data
    )
}

