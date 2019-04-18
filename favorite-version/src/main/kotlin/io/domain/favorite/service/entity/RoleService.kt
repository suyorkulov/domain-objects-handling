package io.domain.favorite.service.entity

import io.domain.favorite.domain.Role
import io.domain.favorite.exceptions.NotFoundException
import io.domain.favorite.repository.RoleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author esuyorkulov
 */

interface RoleService {
    fun getRole(roleId: Long): Role
    fun findAll(): List<Role>
}

@Service
internal class DefaultRoleService(
        private val roleRepository: RoleRepository
) : RoleService {

    @Transactional(readOnly = true)
    override fun getRole(roleId: Long): Role {
        return roleRepository.findById(roleId)
                .orElseThrow { NotFoundException("Role with id: $roleId not found") }
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<Role> {
        return roleRepository.findAll()
    }

}
