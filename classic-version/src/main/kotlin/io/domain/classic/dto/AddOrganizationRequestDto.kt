package io.domain.classic.dto

import javax.validation.constraints.NotBlank

/**
 * @author esuyorkulov
 */
data class AddOrganizationRequestDto(

        @field:NotBlank
        val name: String,

        @field:NotBlank
        val address: String
)