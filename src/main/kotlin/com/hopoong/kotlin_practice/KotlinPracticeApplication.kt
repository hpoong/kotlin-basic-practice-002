package com.hopoong.kotlin_practice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@PropertySource("file:./pwd.ini")
class KotlinPracticeApplication

fun main(args: Array<String>) {
	runApplication<KotlinPracticeApplication>(*args)
}
