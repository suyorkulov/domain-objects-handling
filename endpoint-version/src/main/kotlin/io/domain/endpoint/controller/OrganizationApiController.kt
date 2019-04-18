package io.domain.endpoint.controller

import io.domain.endpoint.dto.AddOrganizationRequestDto
import io.domain.endpoint.dto.OrganizationDto
import io.domain.endpoint.service.endpoint.OrganizationEndpoint
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author esuyorkulov
 */

@RestController
@RequestMapping("/api/organizations")
class OrganizationApiController(
        private val organizationEndpoint: OrganizationEndpoint
) {

    @GetMapping("/{organizationId}")
    fun get(@PathVariable organizationId: Long): OrganizationDto {
        return organizationEndpoint.get(organizationId)
    }

    @PostMapping
    fun add(@Valid @RequestBody request: AddOrganizationRequestDto): OrganizationDto {
        return organizationEndpoint.add(request)
    }

}