package io.domain.favorite.data.dto

import io.domain.favorite.dto.OrganizationDto

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