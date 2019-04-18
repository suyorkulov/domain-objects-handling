package io.domain.endpoint.data.dto

import io.domain.endpoint.dto.OrganizationDto

/**
 * @author esuyorkulov
 */

object DtoOrganizationData {

    fun getOrganizationDto(id: Long = 1,
                           name: String = "Organization",
                           address: String = "Address") = OrganizationDto(
            id = id,
            name = name,
            address = address
    )

}