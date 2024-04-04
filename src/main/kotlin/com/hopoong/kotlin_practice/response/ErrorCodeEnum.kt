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
    ERROR_MEMBER_SAVE("T00", "C01", "Save Error"),
    ERROR_MEMBER_UPDATE("T00", "C02", "Update Error"),
    ERROR_MEMBER_DELETE("T00", "C03", "Delete Error"),
    ERROR_MEMBER("T00", "C04", "Error"),


    // ************ T01 :Post Error
    ERROR_POST_SAVE("T01", "F01", "Save Error"),
    ERROR_POST_UPDATE("T01", "F02", "Update Error"),
    ERROR_POST_DELETE("T01", "F03", "Delete Error"),
    ERROR_POST("T01", "F04", "Error"),


    // ************ 4xxx : Server Error
    SERVER_ERROR("F-4000", "", "Server Error")
}