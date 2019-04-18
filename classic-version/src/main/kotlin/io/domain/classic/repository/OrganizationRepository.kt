package io.domain.classic.repository

import io.domain.classic.domain.Organization
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author esuyorkulov
 */
interface OrganizationRepository : JpaRepository<Organization, Long>