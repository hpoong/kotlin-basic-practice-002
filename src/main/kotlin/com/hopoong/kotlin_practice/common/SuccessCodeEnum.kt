package com.hopoong.kotlin_practice.common

import com.fasterxml.jackson.annotation.JsonFormat


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class SuccessCodeEnum(
    val code: String,
    val message: String
) {

    // ************ 01xx : Member
    MEMBER_API_SAVE("0101", "Member Save Success"),
    MEMBER_API_UPDATE("0102", "Member Update Success"),
}