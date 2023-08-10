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
                                 val modelMapper: ModelMapper): ActivityRepositoryPort {
    override fun findAll(): MutableList<Activity> {
        return repository.findAll().map { convertToActivity(it) }.toMutableList()
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
        val entity = modelMapper.map(activity, ActivityEntity::class.java)
        repository.save(entity)
        return activity
    }

    override fun delete(activity: Activity) {
        val entity = modelMapper.map(activity, ActivityEntity::class.java)
        repository.delete(entity)
    }

    fun convertToActivity(entity: ActivityEntity): Activity {
        return Activity(name = entity.name,
                point = entity.point,
                value = entity.value!!)
    }
}