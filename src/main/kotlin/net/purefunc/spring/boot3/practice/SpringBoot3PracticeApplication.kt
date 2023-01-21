package net.purefunc.spring.boot3.practice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class SpringBoot3PracticeApplication

fun main(args: Array<String>) {
    runApplication<SpringBoot3PracticeApplication>(*args)
}
