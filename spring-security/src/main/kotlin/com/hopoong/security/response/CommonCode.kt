package com.hopoong.security.response

import com.fasterxml.jackson.annotation.JsonFormat


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class CommonCode(
    val code: String,
    val type: String,
) {
    MEMBER("C01", "Member"),
    AUTH("C02", "Auth"),
    SERVER("C50", "Server"),
}