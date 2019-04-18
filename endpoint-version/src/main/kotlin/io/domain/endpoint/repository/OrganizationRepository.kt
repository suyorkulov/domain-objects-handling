package io.domain.endpoint.repository

import io.domain.endpoint.domain.Organization
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author esuyorkulov
 */
interface OrganizationRepository : JpaRepository<Organization, Long>