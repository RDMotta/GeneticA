package com.rdm.genetica.adapters.outbound.entities

import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "tb_activity")
class ActivityEntity(): Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        lateinit var id: UUID
        @Column
        lateinit var name: String
        @Column
        var point: Double = 0.0
        @Column(name = "value_activity")
        var value: BigDecimal? = null
        @Column
        var createdAt: LocalDateTime = LocalDateTime.now()
        @Column
        var updatedAt: LocalDateTime = LocalDateTime.now()
}