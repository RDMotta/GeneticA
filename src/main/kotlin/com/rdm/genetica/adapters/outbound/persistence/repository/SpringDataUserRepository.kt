package com.rdm.genetica.adapters.outbound.persistence.repository

import com.rdm.genetica.adapters.outbound.entities.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
interface SpringDataUserRepository: CrudRepository<UserEntity, String> {
    fun findByLogin(login: String): UserDetails
}