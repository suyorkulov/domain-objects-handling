package io.domain.endpoint.dto

import io.domain.endpoint.domain.UserStatus

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