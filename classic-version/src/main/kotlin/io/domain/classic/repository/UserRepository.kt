package io.domain.classic.repository

import io.domain.classic.domain.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author esuyorkulov
 */
interface UserRepository : JpaRepository<User, Long>