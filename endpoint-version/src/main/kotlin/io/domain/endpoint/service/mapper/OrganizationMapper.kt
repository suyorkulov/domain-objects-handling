package io.domain.endpoint.service.mapper

import io.domain.endpoint.domain.Organization
import io.domain.endpoint.dto.AddOrganizationRequestDto
import io.domain.endpoint.dto.OrganizationDto
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