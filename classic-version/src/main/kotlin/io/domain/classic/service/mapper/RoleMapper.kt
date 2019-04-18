package io.domain.classic.service.mapper

import io.domain.classic.domain.Role
import io.domain.classic.dto.RoleDto
import org.springframework.stereotype.Service

/**
 * @author esuyorkulov
 */
interface RoleMapper {
    fun toRoleDto(role: Role): RoleDto
}

@Service
internal class DefaultRoleMapper : RoleMapper {

    override fun toRoleDto(role: Role): RoleDto = RoleDto(
            id = role.id!!,
            name = role.name
    )

}