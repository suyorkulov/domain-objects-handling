package io.domain.classic.dto

/**
 * @author esuyorkulov
 */

data class UserDto(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val email: String,
        val phoneNumber: String?,
        val role: RoleDto,
        val organization: OrganizationDto
)