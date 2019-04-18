package io.domain.endpoint.repository

import io.domain.endpoint.domain.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author esuyorkulov
 */
interface UserRepository : JpaRepository<User, Long>