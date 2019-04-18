package io.domain.favorite

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OtherVersionApplication

fun main(args: Array<String>) {
    runApplication<OtherVersionApplication>(*args)
}
