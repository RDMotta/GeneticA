package com.rdm.genetica.application.domain

import java.math.BigDecimal
import kotlin.math.roundToInt

class Individual(val listPoints: List<Double>, private val listValues: List<BigDecimal>, private val limit: Double): Comparable<Individual> {
    var chromosome = mutableListOf<Int>()
    private var generation = 0
    private var usedPoint = 0.0
    var score = BigDecimal.ZERO

    init {
        listPoints.forEach { _ ->
            if (Math.random() < 0.5) this.chromosome.add(0) else this.chromosome.add(1)
        }
    }

    fun evaluate() {
        var grade = BigDecimal.ZERO
        var totalPoint = 0.0

        this.chromosome.forEachIndexed { index, chromosome ->
            if (chromosome == 1) {
                grade += this.listValues[index]
                totalPoint += this.listPoints[index]
            }
        }

        if (totalPoint > this.limit) {
            grade = BigDecimal.ONE
        }

        this.score = grade
        this.usedPoint = totalPoint
    }

    fun crossover(other: Individual): MutableList<Individual> {
       val section = (Math.random() * this.chromosome.size).roundToInt()

        val child1 = mutableListOf<Int>()
        child1.addAll(other.chromosome.subList(0, section))
        child1.addAll(this.chromosome.subList(section, this.chromosome.size))

        val child2 = mutableListOf<Int>()
        child2.addAll(this.chromosome.subList(0, section))
        child2.addAll(other.chromosome.subList(section, this.chromosome.size))

        val children = mutableListOf<Individual>()
        children.add(Individual(this.listPoints, this.listValues, this.limit))
        children.add(Individual(this.listPoints, this.listValues, this.limit))
        children[0].chromosome = child1
        children[0].generation = this.generation + 1
        children[1].chromosome = child2
        children[1].generation = this.generation + 1

        return children
    }

    fun mutation(ratio: Double): Individual {
        this.chromosome.forEachIndexed{index, byte ->
            if (Math.random() < ratio) {
                if (byte.equals(1)) {
                  this.chromosome[index] = 0
                } else {
                  this.chromosome[index] = 1
                }
            }
        }
        return this
    }

    override fun compareTo(other: Individual): Int {
        if (this.score > other.score) return -1
        if (this.score < other.score) return 1
        return 0
    }
}