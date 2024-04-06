package com.hopoong.kotlin_practice.response

import com.hopoong.kotlin_practice.util.TimeUtil

class ErrorResponse(
    val success: Boolean = false,
    var code: String,
    var message: String,
    private var _timestamp: String? = null
) {
    val timestamp: String
        get() {
            if (_timestamp == null) {
                _timestamp = TimeUtil().getFormattedTimestamp()
            }
            return _timestamp!!
        }

    constructor(errorCodeEnum: ErrorCodeEnum) : this(
        code = errorCodeEnum.code,
        message = errorCodeEnum.message
    )
}