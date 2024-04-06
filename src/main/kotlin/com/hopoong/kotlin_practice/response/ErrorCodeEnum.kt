package com.hopoong.kotlin_practice.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCodeEnum(
    val type: String,
    val code: String,
    val message: String,
) {

    // ************ T00 : Member Error
    ERROR_MEMBER_SEARCH("T00", "C01", "Member Search Error"),
    ERROR_MEMBER_SAVE("T00", "C02", "Member Save Error"),

    // ************ T01 :Post Error


    // ************ 4xxx : Server Error
    SERVER_ERROR("T40", "C01", "Server Error")
}