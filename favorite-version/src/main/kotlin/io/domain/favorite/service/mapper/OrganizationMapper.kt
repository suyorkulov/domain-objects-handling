package io.domain.favorite.service.mapper

import io.domain.favorite.domain.Organization
import io.domain.favorite.dto.AddOrganizationRequestDto
import io.domain.favorite.dto.OrganizationDto
import org.springframework.stereotype.Service

/**
 * @author esuyorkulov
 */
interface OrganizationMapper {
    fun toOrganization(request: AddOrganizationRequestDto): Organization
    fun toOrganizationDto(organization: Organization): OrganizationDto
}

@Service
internal class DefaultOrganizationMapper : OrganizationMapper {

    override fun toOrganization(request: AddOrganizationRequestDto): Organization = Organization(
            name = request.name,
            address = request.address
    )

    override fun toOrganizationDto(organization: Organization): OrganizationDto = OrganizationDto(
            id = organization.id!!,
            name = organization.name,
            address = organization.address
    )

}