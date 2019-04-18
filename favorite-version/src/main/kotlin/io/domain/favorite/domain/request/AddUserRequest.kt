package io.domain.favorite.domain.request

import io.domain.favorite.domain.Organization
import io.domain.favorite.domain.Role

/**
 * @author esuyorkulov
 */
data class AddUserRequest(
        val firstName: String,
        val lastName: String,
        val email: String,
        val phoneNumber: String?,
        val role: Role,
        val organization: Organization
)