package io.domain.favorite.controller

import io.domain.favorite.dto.AddOrganizationRequestDto
import io.domain.favorite.dto.OrganizationDto
import io.domain.favorite.service.endpoint.OrganizationEndpoint
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