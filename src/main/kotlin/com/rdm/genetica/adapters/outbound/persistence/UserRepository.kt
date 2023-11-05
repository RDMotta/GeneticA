package com.rdm.genetica.adapters.outbound.persistence

import com.rdm.genetica.adapters.outbound.persistence.repository.SpringDataUserRepository
import com.rdm.genetica.application.ports.UserRepositoryPort
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
@Primary
class UserRepository(val repository: SpringDataUserRepository): UserRepositoryPort {
    override fun findByLogin(login: String): UserDetails {
       return repository.findByLogin(login)
    }
}