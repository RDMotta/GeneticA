package com.rdm.genetica.domain

import com.rdm.genetica.model.Individual
import java.math.BigDecimal

class GeneticExecutor(val maxPopulation: Int, val listPoint: List<Double>, val listValues: List<BigDecimal>, val limit: Double) {

    private var populationList = mutableListOf<Individual>()
    private val generation: Int = 0
    private var bestSolution: Individual? = null
    private val bestChromosome = mutableListOf<Individual>()

    fun initPopulation() {
        for (i in 0..this.maxPopulation) {
            this.populationList.add(Individual(listPoint, listValues, limit))
        }
        this.bestSolution = this.populationList[0]
    }

    fun sortPopulation() { this.populationList.sort() }

    fun bestIndividual(individual: Individual) {
        if (individual.score > this.bestSolution?.score) {
            this.bestSolution = individual
        }
    }

    fun sumScore() = this.populationList.sumOf { it.score }

    fun selectedOwner(totalScore: BigDecimal): Int {
        var owner = -1
        val randomScore = Math.random() * totalScore.toDouble()
        var total = BigDecimal.ZERO
        var i = 0
        while (i < this.populationList.size && total.toDouble() < randomScore) {
            total += this.populationList[i].score
            owner += 1
            i += 1
        }
        return owner
    }

    fun applyBestGeneration(){
        val bestIndividual = this.populationList[0]
        this.bestChromosome.add(bestIndividual)
    }

    fun solve(ratioMutation: Double, numGeneration: Int): Individual? {
        this.initPopulation()
        this.populationList.map { it.evaluate() }
        this.sortPopulation()

        for(geracao in 0..numGeneration) {
           val newPopulation = mutableListOf<Individual>()
           val totalScore = this.sumScore()
           for (i in 0..(this.populationList.size/2)) {
             val owner1 = this.selectedOwner(totalScore)
             val owner2 = this.selectedOwner(totalScore)
             val children = this.populationList[owner1].crossover(this.populationList[owner2])
               newPopulation.add(children[0].mutation(ratioMutation))
               newPopulation.add(children[1].mutation(ratioMutation))
           }

           this.populationList = newPopulation
           this.populationList.map { it.evaluate() }
           this.sortPopulation()
           this.applyBestGeneration()
           this.bestIndividual(this.populationList[0])
        }

       return this.bestSolution
    }
}