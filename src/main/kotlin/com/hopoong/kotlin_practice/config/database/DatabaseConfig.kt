package com.hopoong.kotlin_practice.config.database

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory


@Configuration
@ComponentScan(basePackages = ["com.hopoong.kotlin_practice"])
@EnableTransactionManagement
class DatabaseConfig {

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory?): JpaTransactionManager? {
        return JpaTransactionManager(entityManagerFactory!!)
    }
}