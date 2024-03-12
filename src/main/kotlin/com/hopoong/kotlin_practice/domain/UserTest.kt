package com.hopoong.kotlin_practice.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.persistence.*
import javax.transaction.Transactional


class UserTest {

    @Component
    class UserListeners {


        // https://ldevlog.tistory.com/13

        @PostPersist
        fun preUpdate(user: User) {
            println("======= postInsert =======")
            println("user: $user")
        }

        @PostUpdate
        fun postUpdate(user: User) {
            println("======= postUpdate =======")
            println("user: $user")
        }
    }

    @Entity
    @EntityListeners(value= [UserListeners::class])
    @Table(name = "acc_user_test")
    data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var name: String,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var updatedAt: LocalDateTime? = null
    )

    @Component
    @Transactional
    class StartTest {

        @Autowired
        private lateinit var userRepository: UserRepository

//        @EventListener
//        fun onApplicationEvent(event: ApplicationStartedEvent) {
//            val user1 = User(name = "user-name")
//            val savedUser = userRepository.save(user1)
//
//            val user = userRepository.findById(savedUser.id!!).orElseThrow { throw Exception("user not found") }
//            user.name = "EntitytListener"
//            userRepository.save(user)
//        }
    }

}