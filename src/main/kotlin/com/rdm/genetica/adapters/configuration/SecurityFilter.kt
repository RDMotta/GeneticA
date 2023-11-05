package com.rdm.genetica.adapters.configuration

import com.rdm.genetica.adapters.outbound.persistence.UserRepository
import com.rdm.genetica.application.service.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(val tokenService: TokenService, val userRepository: UserRepository): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = this.recover(request)
        if (token.isNotBlank()) {
            val login = tokenService.validateToken(token)
            val user = userRepository.findByLogin(login)

            val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

   fun recover(request: HttpServletRequest): String {
       var authHeader = request.getHeader("Authorization") ?: return ""
       return authHeader.replace("Bearer ", "")
   }
}