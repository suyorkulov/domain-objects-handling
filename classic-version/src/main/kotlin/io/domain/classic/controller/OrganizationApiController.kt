package io.domain.classic.controller

import io.domain.classic.domain.Organization
import io.domain.classic.dto.AddOrganizationRequestDto
import io.domain.classic.dto.OrganizationDto
import io.domain.classic.service.OrganizationService
import io.domain.classic.service.mapper.OrganizationMapper
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author esuyorkulov
 */

@RestController
@RequestMapping("/api/organizations")
class OrganizationApiController(
        private val organizationService: OrganizationService,
        private val organizationMapper: OrganizationMapper
) {

    @GetMapping("/{organizationId}")
    fun get(@PathVariable organizationId: Long): OrganizationDto {
        val organization: Organization = organizationService.get(organizationId)

        return organizationMapper.toOrganizationDto(organization)
    }

    @PostMapping
    fun add(@Valid @RequestBody request: AddOrganizationRequestDto): OrganizationDto {
        val organization: Organization = organizationMapper.toOrganization(request)

        val createdOrganization: Organization = organizationService.add(organization)

        return organizationMapper.toOrganizationDto(createdOrganization)
    }

}