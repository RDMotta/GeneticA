package com.rdm.genetica

import com.rdm.genetica.service.GeneticService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GeneticAApplication

fun main(args: Array<String>) {
    runApplication<GeneticAApplication>(*args)
    val ge = GeneticService()
    ge.applay()
}