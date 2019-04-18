package io.domain.favorite.repository

import io.domain.favorite.domain.Organization
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author esuyorkulov
 */
interface OrganizationRepository : JpaRepository<Organization, Long>