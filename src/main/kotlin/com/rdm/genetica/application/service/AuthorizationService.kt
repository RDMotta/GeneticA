package com.rdm.genetica.application.service

import com.rdm.genetica.application.ports.UserRepositoryPort
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorizationService(val repository: UserRepositoryPort): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return repository.findByLogin(username)
    }
}