package com.hopoong.kotlin_practice.response

import com.fasterxml.jackson.annotation.JsonFormat


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class SuccessCodeEnum(
    val code: String,
    val message: String
) {

    // ************ 01xx : Member
    MEMBER_API_SAVE("S-0101", "Member Save Success"),
    MEMBER_API_UPDATE("S-0102", "Member Update Success"),
}