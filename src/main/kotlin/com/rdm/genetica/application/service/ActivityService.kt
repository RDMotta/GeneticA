package com.rdm.genetica.application.service

import com.rdm.genetica.application.domain.Activity
import com.rdm.genetica.application.ports.ActivityRepositoryPort
import com.rdm.genetica.application.ports.ActivityServicePort
import java.util.*

class ActivityService(private val repository: ActivityRepositoryPort): ActivityServicePort {
    override fun findAll() = repository.findAll()
    override fun findById(id: UUID) = repository.findById(id)
    override fun save(activity: Activity) = repository.save(activity)
}