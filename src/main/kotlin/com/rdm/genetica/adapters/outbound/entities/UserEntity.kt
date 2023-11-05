package com.rdm.genetica.adapters.outbound.entities

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
@Entity
@Table(name = "tb_users")
class UserEntity: UserDetails {

    @Id
    private val login: String = ""
    private val password: String = ""
    private var isEnabled: Boolean = true
    private var isCredentialsNonExpired: Boolean = true
    private var isAccountNonExpired: Boolean = true
    private var isAccountNonLocked: Boolean = true
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "tb_roles",
        joinColumns = [JoinColumn(name = "tb_users_id", referencedColumnName = "login")],
        inverseJoinColumns = [JoinColumn(name = "tb_roles_id", referencedColumnName = "roleName")]
    )
    private val roles: List<RoleEntity> = listOf()
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = ArrayList<SimpleGrantedAuthority>()
        roles.map { authorities.add(SimpleGrantedAuthority(it.authority)) }
        return authorities
    }

    override fun getPassword() = this.password

    override fun getUsername() = this.login

    override fun isAccountNonExpired() = this.isAccountNonExpired

    override fun isAccountNonLocked() = this.isAccountNonLocked

    override fun isCredentialsNonExpired() = this.isCredentialsNonExpired

    override fun isEnabled() = this.isEnabled

}