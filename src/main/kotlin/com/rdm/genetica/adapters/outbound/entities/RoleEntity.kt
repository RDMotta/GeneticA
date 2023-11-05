package com.rdm.genetica.adapters.outbound.entities

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "tb_roles")
class RoleEntity(): GrantedAuthority {
    @Id
    private val roleName: String = ""

    @ManyToMany(mappedBy = "roles")
    private val users: List<UserEntity>? = null
    override fun getAuthority(): String {
        return this.roleName
    }
}