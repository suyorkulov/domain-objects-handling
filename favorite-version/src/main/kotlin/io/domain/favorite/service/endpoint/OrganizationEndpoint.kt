package io.domain.favorite.service.endpoint

import io.domain.favorite.domain.Organization
import io.domain.favorite.domain.request.AddOrganizationRequest
import io.domain.favorite.dto.AddOrganizationRequestDto
import io.domain.favorite.dto.OrganizationDto
import io.domain.favorite.service.entity.OrganizationService
import io.domain.favorite.service.mapper.OrganizationMapper
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
        val addRequest = AddOrganizationRequest(
                name = request.name,
                address = request.address
        )

        val createdOrganization: Organization = organizationService.add(addRequest)

        return organizationMapper.toOrganizationDto(createdOrganization)
    }

    @Transactional(readOnly = true)
    override fun get(organizationId: Long): OrganizationDto {
        val organization: Organization = organizationService.get(organizationId)

        return organizationMapper.toOrganizationDto(organization)
    }

}