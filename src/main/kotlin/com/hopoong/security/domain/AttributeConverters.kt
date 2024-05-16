package com.hopoong.security.domain

import com.hopoong.security.domain.post.PostTypeEnum
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class PostTypeConverter: AttributeConverter<PostTypeEnum, String>{

    // DB in
    override fun convertToDatabaseColumn(attribute: PostTypeEnum): String {
        println("convertToDatabaseColumn ::: ${attribute.name}")
        return attribute.name
    }

    // DB out
    override fun convertToEntityAttribute(dbData: String): PostTypeEnum {
        println("convertToEntityAttribute ::: ${PostTypeEnum.of(dbData).info}")
        return PostTypeEnum.of(dbData)
    }
}