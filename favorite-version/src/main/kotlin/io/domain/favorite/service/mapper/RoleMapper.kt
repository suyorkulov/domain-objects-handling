package io.domain.favorite.service.mapper

import io.domain.favorite.domain.Role
import io.domain.favorite.dto.RoleDto
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