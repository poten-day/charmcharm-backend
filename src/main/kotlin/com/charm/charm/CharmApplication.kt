package com.charm.charm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CharmApplication

fun main(args: Array<String>) {
    runApplication<CharmApplication>(*args)
}
