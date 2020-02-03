package ru.samolovov.soran

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SoranApplication

fun main(args: Array<String>) {
    runApplication<SoranApplication>(*args)
}
