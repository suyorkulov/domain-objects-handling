package io.domain.endpoint.service.endpoint

import io.domain.endpoint.domain.Organization
import io.domain.endpoint.dto.AddOrganizationRequestDto
import io.domain.endpoint.dto.OrganizationDto
import io.domain.endpoint.service.entity.OrganizationService
import io.domain.endpoint.service.mapper.OrganizationMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author esuyorkulov
 */

interface OrganizationEndpoint {
    fun add(request: AddOrganizationRequestDto): OrganizationDto
    fun get(organizationId: Long): OrganizationDto
}

@Service
internal class DefaultOrganizationEndpoint(
        private val organizationService: OrganizationService,
        private val organizationMapper: OrganizationMapper
) : OrganizationEndpoint {

    @Transactional
    override fun add(request: AddOrganizationRequestDto): OrganizationDto {
        val organization = Organization(
                name = request.name,
                address = request.address
        )

        val createdOrganization: Organization = organizationService.saveOrUpdate(organization)

        return organizationMapper.toOrganizationDto(createdOrganization)
    }

    @Transactional(readOnly = true)
    override fun get(organizationId: Long): OrganizationDto {
        val organization: Organization = organizationService.get(organizationId)

        return organizationMapper.toOrganizationDto(organization)
    }

}