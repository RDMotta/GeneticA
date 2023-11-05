package com.rdm.genetica.adapters.inbound

import com.rdm.genetica.adapters.dto.AuthenticationDto
import com.rdm.genetica.adapters.dto.LoginResponseDto
import com.rdm.genetica.adapters.outbound.entities.UserEntity
import com.rdm.genetica.adapters.outbound.persistence.UserRepository
import com.rdm.genetica.application.service.TokenService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("auth")
class LoginResource(val authenticationManager: AuthenticationManager, val userRepository: UserRepository, val tokenService: TokenService){
    @PostMapping("/login")
    fun login(@RequestBody data: AuthenticationDto): ResponseEntity<*>? {
        val usernamePassword = UsernamePasswordAuthenticationToken(data.login, data.password)
        val auth = authenticationManager.authenticate(usernamePassword)

        val token = tokenService.generateToken(auth.principal as UserEntity)
        return ResponseEntity.ok<Any>(LoginResponseDto(token))
    }
}