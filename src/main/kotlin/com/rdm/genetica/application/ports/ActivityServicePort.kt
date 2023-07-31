package com.rdm.genetica.application.ports

import com.rdm.genetica.application.domain.Activity
import java.util.*

interface ActivityServicePort {
    fun findAll(): List<Activity>
    fun findById(id: UUID): Optional<Activity>
    fun save(activity: Activity): Activity
}