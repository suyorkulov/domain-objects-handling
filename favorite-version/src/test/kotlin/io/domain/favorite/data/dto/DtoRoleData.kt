package io.domain.favorite.data.dto

import io.domain.favorite.dto.RoleDto

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