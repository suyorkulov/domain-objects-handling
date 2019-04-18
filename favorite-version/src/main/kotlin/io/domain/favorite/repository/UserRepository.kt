package io.domain.favorite.repository

import io.domain.favorite.domain.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author esuyorkulov
 */
interface UserRepository : JpaRepository<User, Long>