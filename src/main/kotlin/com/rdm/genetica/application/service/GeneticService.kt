package com.rdm.genetica.application.service

import com.rdm.genetica.application.domain.GeneticExecutor
import com.rdm.genetica.application.domain.Activity
import java.math.BigDecimal
import java.math.RoundingMode

class GeneticService {

    fun applay() {

        val listActivity = mutableListOf<Activity>()
        listActivity.add(Activity("01 - Develop a stronger mind", 0.121, BigDecimal(999.90)))
        listActivity.add(Activity("02 - Learn professional skills", 1.89, BigDecimal(2911.12)))
        listActivity.add(Activity("03 - Create a wider social circle", 1.400, BigDecimal(4346.99)))
        listActivity.add(Activity("04 - Create a Environmental Club", 0.290, BigDecimal(3999.90)))
        listActivity.add(Activity("05 - Push Notifications", 0.200, BigDecimal(2999.00)))
        listActivity.add(Activity("06 - Push Notifications SMS, Email, In-App Messaging", 0.00350, BigDecimal(2499.90)))
        listActivity.add(Activity("07 - Face recognition", 0.496, BigDecimal(199.90)))
        listActivity.add(Activity("08 - Face recognition search", 0.0424, BigDecimal(308.66)))
        listActivity.add(Activity("09 - Frontend API GeneticService", 0.0544, BigDecimal(429.90)))
        listActivity.add(Activity("10 - Swagger api documentation", 0.0319, BigDecimal(299.29)))
        listActivity.add(Activity("11 - Security alert API", 0.635, BigDecimal(849.00)))
        listActivity.add(Activity("12 - LDAP Integration API", 0.870, BigDecimal(1199.89)))
        listActivity.add(Activity("13 - Create Repository and DB Integrations", 0.498, BigDecimal(1999.90)))
        listActivity.add(Activity("14 - Integration Services Gateway Billing API", 0.527, BigDecimal(3999.00)))


        val listPoint = mutableListOf<Double>()
        val listValue = mutableListOf<BigDecimal>()

        listActivity.forEach {
            listPoint.add(it.point)
            it.value.let { it1 -> listValue.add(it1) }
        }

        val limit = 5.0
        val maxPopulation = listActivity.size
        val ratioMutation = 0.05
        val numGeneration = 50
        val ge = GeneticExecutor(maxPopulation, listPoint, listValue, limit)
        val solved = ge.solve(ratioMutation, numGeneration)

        var total = 0.0

        listActivity.forEachIndexed { index, activity ->
            if(solved?.chromosome?.get(index) == 1) {
                println("Name ${activity.name} Point ${activity.point} Value ${activity.value.setScale(5, RoundingMode.CEILING)}")

                total += activity.point
            }
        }
        println("Total: ${BigDecimal.valueOf(total).setScale(5, RoundingMode.CEILING)}")
        println("Value: ${solved?.score?.setScale(5, RoundingMode.CEILING)}")
    }
}