package io.domain.favorite.repository

import io.domain.favorite.domain.Role
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author esuyorkulov
 */
interface RoleRepository : JpaRepository<Role, Long>