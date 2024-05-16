package com.hopoong.security.response

import com.hopoong.security.util.TimeUtil

open class CommonResponse(
    val success: Boolean,
    private var _timestamp: String? = null
) {

    val timestamp: String
        get() {
            if (_timestamp == null) {
                _timestamp = TimeUtil().getFormattedTimestamp()
            }
            return _timestamp!!
        }

}