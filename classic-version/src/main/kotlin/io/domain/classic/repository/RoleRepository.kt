package io.domain.classic.repository

import io.domain.classic.domain.Role
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author esuyorkulov
 */
interface RoleRepository : JpaRepository<Role, Long>