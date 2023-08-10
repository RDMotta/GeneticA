package com.rdm.genetica.application.ports

import com.rdm.genetica.application.domain.Activity
import com.rdm.genetica.application.domain.PageInfo
import java.util.*

interface ActivityServicePort {
    fun findAll(pageInfo: PageInfo): List<Activity>
    fun findById(id: UUID): Optional<Activity>
    fun save(activity: Activity): Activity
    fun delete(activity: Activity)
}