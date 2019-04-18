package io.domain.endpoint.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * @author esuyorkulov
 */
data class UpdateUserRequestDto(
        @field:NotBlank
        val firstName: String,

        @field:NotBlank
        val lastName: String,

        @field:Email
        val email: String,

        val roleId: Long,

        val organizationId: Long,

        @field: Size(min = 6)
        val phoneNumber: String?
)