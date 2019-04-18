package io.domain.classic.controller

import io.domain.classic.domain.Role
import io.domain.classic.dto.RoleDto
import io.domain.classic.service.RoleService
import io.domain.classic.service.mapper.RoleMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author esuyorkulov
 */
@RestController
@RequestMapping("/api/roles")
class RoleApiController(
        private val roleService: RoleService,
        private val roleMapper: RoleMapper
) {

    @GetMapping("/{roleId}")
    fun get(@PathVariable roleId: Long): RoleDto {
        val role: Role = roleService.getRole(roleId)

        return roleMapper.toRoleDto(role)
    }

}