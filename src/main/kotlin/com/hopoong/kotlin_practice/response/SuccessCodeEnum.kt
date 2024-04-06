package com.hopoong.kotlin_practice.response

import com.fasterxml.jackson.annotation.JsonFormat


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class SuccessCodeEnum(
    val type: String,
    val code: String,
    val message: String,
) {

    // ************ T00 : Member Success
    SUCCESS_MEMBER_SEARCH("T00", "C01", "Member Search Success"),
    SUCCESS_MEMBER_SAVE("T00", "C02", "Member Save Success"),
    SUCCESS_MEMBER_UPDATE("T00", "C02", "Member Update Success"),
    SUCCESS_MEMBER_DELETE("T00", "C02", "Member Delete Success"),
}