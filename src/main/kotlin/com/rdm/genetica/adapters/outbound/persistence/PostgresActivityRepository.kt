package com.rdm.genetica.adapters.outbound.persistence

import com.rdm.genetica.adapters.outbound.entities.ActivityEntity
import com.rdm.genetica.adapters.outbound.persistence.repository.SpringDataPostgresActivityRepository
import com.rdm.genetica.application.domain.Activity
import com.rdm.genetica.application.ports.ActivityRepositoryPort
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import org.modelmapper.ModelMapper
import java.util.*

@Component
@Primary
class PostgresActivityRepository(val repository: SpringDataPostgresActivityRepository,
                                 val modelmapper: ModelMapper): ActivityRepositoryPort {
    override fun findAll(): MutableList<Activity> {
        val list = mutableListOf<Activity>()
        repository.findAll().forEach { list.add(convertToActivity(it)) }
        return list
    }

    override fun findById(id: UUID): Optional<Activity> {
        val entity = repository.findById(id)
        return if (entity.isPresent) {
            Optional.of(convertToActivity(entity.get()))
        } else {
            Optional.empty()
        }
    }

    override fun save(activity: Activity): Activity {
        val entity = modelmapper.map(activity, ActivityEntity::class.java)
        repository.save(entity)
        return activity
    }

    fun convertToActivity(entity: ActivityEntity): Activity {
        return Activity(name = entity.name,
                point = entity.point,
                value = entity.value!!)
    }
}