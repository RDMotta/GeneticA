package com.rdm.genetica.application.ports

import com.rdm.genetica.application.domain.Activity
import java.util.*
interface ActivityRepositoryPort {
    fun findAll(): MutableList<Activity>
    fun findById(id: UUID): Optional<Activity>
    fun save(activity: Activity): Activity
    fun delete(activity: Activity)
}
