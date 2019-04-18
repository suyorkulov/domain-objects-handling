package io.domain.endpoint.service.endpoint

import io.domain.endpoint.domain.Role
import io.domain.endpoint.dto.RoleDto
import io.domain.endpoint.service.entity.RoleService
import io.domain.endpoint.service.mapper.RoleMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author esuyorkulov
 */
interface RoleEndpoint {
    fun getRole(roleId: Long): RoleDto
}

@Service
internal class DefaultRoleEndpoint(
        private val roleService: RoleService,
        private val roleMapper: RoleMapper
) : RoleEndpoint {

    @Transactional(readOnly = true)
    override fun getRole(roleId: Long): RoleDto {
        val role: Role = roleService.getRole(roleId)

        return roleMapper.toRoleDto(role)
    }

}