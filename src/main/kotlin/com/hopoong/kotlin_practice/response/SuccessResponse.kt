package com.hopoong.kotlin_practice.response

class SuccessResponses(
    var code: String,
    var message: String = "Success",
    var data: Any?
): CommonResponse(true) {

    constructor(code: CommonCode) : this(
        code = code.code,
        data = null
    )

    constructor(code: CommonCode, data: Any) : this(
        code = code.code,
        data = data
    )
}
