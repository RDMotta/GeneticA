package com.rdm.genetica.application.ports

import org.springframework.security.core.userdetails.UserDetails

interface UserRepositoryPort {
    fun findByLogin(login: String): UserDetails
}