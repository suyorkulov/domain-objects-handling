package io.domain.favorite.domain.request

import io.domain.favorite.domain.Organization
import io.domain.favorite.domain.Role

/**
 * @author esuyorkulov
 */
data class UpdateUserRequest(
        val firstName: String,
        val lastName: String,
        val email: String,
        val role: Role,
        val organization: Organization,
        val phoneNumber: String?
)