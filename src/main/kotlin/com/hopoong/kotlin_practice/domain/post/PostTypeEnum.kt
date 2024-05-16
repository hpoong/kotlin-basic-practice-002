package com.hopoong.kotlin_practice.domain.post

import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

enum class PostTypeEnum(
    val info: String
) {
    C_001("의류"),
    C_002("가구"),
    C_003("가전"),
    C_004("없음"),
    ;


    companion object {
        private val CODE_MAP: Map<String, PostTypeEnum> = Collections.unmodifiableMap(
            Stream.of(*PostTypeEnum.values()).collect(Collectors.toMap(PostTypeEnum::info) { it }))

        fun of(code: String): PostTypeEnum {
            return CODE_MAP[code] ?: PostTypeEnum.C_004
        }
    }
}

