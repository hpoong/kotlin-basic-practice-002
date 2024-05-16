package com.hopoong.security.common

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class S3FileUploadService(
    private val amazonS3Client: AmazonS3Client
) {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null


    fun uploadFile(fileList: MutableList<MultipartFile>): String {

        try {

            // https://innovation123.tistory.com/197#build.gradle%20implementation-1

            var file: MultipartFile? = null
            var fileName = file!!.getOriginalFilename()
            var fileUrl = "https://" + bucket + "/test" + fileName

            var metadata = ObjectMetadata()
            metadata.setContentType(file.getContentType())
            metadata.setContentLength(file.getSize())
            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata)

            return fileUrl
        } catch (e : Exception) {
            e.printStackTrace()
            throw e
        }
    }

}