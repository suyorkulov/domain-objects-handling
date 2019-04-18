package io.domain.endpoint.repository

import io.domain.endpoint.domain.Role
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author esuyorkulov
 */
interface RoleRepository : JpaRepository<Role, Long>