package io.domain.favorite.dto

import io.domain.favorite.domain.UserStatus

/**
 * @author esuyorkulov
 */

data class UserDto(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val email: String,
        val phoneNumber: String?,
        val status: UserStatus,
        val role: RoleDto,
        val organization: OrganizationDto
)