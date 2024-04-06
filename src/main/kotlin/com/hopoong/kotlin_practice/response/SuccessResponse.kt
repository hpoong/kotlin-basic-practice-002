package com.hopoong.kotlin_practice.response

import com.hopoong.kotlin_practice.util.TimeUtil

class SuccessResponses(
    val success: Boolean = true,
    var code: String,
    var message: String,
    private var _timestamp: String? = null,
    var data: Any?
) {
    val timestamp: String
        get() {
            if (_timestamp == null) {
                _timestamp = TimeUtil().getFormattedTimestamp()
            }
            return _timestamp!!
        }

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

