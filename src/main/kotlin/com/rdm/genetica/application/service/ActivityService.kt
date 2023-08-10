package com.rdm.genetica.application.service

import com.rdm.genetica.application.domain.Activity
import com.rdm.genetica.application.domain.PageInfo
import com.rdm.genetica.application.ports.ActivityRepositoryPort
import com.rdm.genetica.application.ports.ActivityServicePort
import java.util.*

class ActivityService(private val repository: ActivityRepositoryPort): ActivityServicePort {
     override fun findAll(pageInfo: PageInfo): List<Activity> {
       return repository.findAll()
    }

    override fun findById(id: UUID) = repository.findById(id)
    override fun save(activity: Activity) = repository.save(activity)
    override fun delete(activity: Activity) {
        repository.delete(activity)
    }
}