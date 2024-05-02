package com.hopoong.kotlin_practice.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class CommonCode(
    val code: String,
    val type: String,
) {
    MEMBER("C01", "Member"),
    AUTH("C02", "Auth"),
    SERVER("C50", "Server"),
}