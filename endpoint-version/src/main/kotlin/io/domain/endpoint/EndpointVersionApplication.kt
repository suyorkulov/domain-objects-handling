package io.domain.endpoint

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EndpointVersionApplication

fun main(args: Array<String>) {
    runApplication<EndpointVersionApplication>(*args)
}
