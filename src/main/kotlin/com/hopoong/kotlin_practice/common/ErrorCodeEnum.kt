package com.hopoong.kotlin_practice.common

import com.fasterxml.jackson.annotation.JsonFormat


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCodeEnum(
    val code: String,
    val message: String
) {

    // ************ 01xx : Member Error
    MEMBER_API_SAVE("0101", "Member Save Error"),
    MEMBER_API_UPDATE("0102", "Member Update Error"),

    // ************ 4xxx : Server Error
    SERVER_ERROR("4000", "Server Error"),
}