package com.rdm.genetica.application.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.rdm.genetica.adapters.outbound.entities.UserEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


@Service
class TokenService {

    @Value("\${api.security.token}")
    private val secret: String? = null

    fun generateToken(user: UserEntity): String {
        return try {
            val algorithm: Algorithm = Algorithm.HMAC256(secret)
            JWT.create()
                .withIssuer(TAG_API)
                .withSubject(user.username)
                .withExpiresAt(genExpirationDate())
                .sign(algorithm)
        } catch (exception: JWTCreationException) {
            throw RuntimeException("Error while generating token", exception)
        }
    }

    fun validateToken(token: String) : String {
        return try {
            val algorithm = Algorithm.HMAC256(secret)
            JWT.require(algorithm)
                .withIssuer(TAG_API)
                .build()
                .verify(token)
                .subject
        } catch (exception: JWTVerificationException) {
            throw RuntimeException("Error while validate token", exception)
        }
    }
    private fun genExpirationDate(): Instant? {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
    }

    companion object {
        val TAG_API = "auth-ga-api"
    }

}