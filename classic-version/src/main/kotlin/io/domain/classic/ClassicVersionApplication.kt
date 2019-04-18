package io.domain.classic

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ClassicVersionApplication

fun main(args: Array<String>) {
    runApplication<ClassicVersionApplication>(*args)
}
