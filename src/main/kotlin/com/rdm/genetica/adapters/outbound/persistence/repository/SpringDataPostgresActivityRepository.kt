package com.rdm.genetica.adapters.outbound.persistence.repository

import com.rdm.genetica.adapters.outbound.entities.ActivityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID
@Repository
interface SpringDataPostgresActivityRepository: JpaRepository<ActivityEntity, UUID> {
}