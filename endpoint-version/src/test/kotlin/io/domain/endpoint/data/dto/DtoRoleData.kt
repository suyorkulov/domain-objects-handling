package io.domain.endpoint.data.dto

import io.domain.endpoint.dto.RoleDto

/**
 * @author esuyorkulov
 */

object DtoRoleData {

    fun getRoleDto(id: Long = 1,
                   name: String = "ADMIN") = RoleDto(
            id = id,
            name = name
    )

}