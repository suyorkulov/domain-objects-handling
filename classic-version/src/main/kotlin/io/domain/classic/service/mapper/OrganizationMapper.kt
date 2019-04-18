package io.domain.classic.service.mapper

import io.domain.classic.domain.Organization
import io.domain.classic.dto.AddOrganizationRequestDto
import io.domain.classic.dto.OrganizationDto
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