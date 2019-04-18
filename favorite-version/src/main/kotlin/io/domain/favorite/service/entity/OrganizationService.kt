package io.domain.favorite.service.entity

import io.domain.favorite.domain.Organization
import io.domain.favorite.domain.request.AddOrganizationRequest
import io.domain.favorite.exceptions.NotFoundException
import io.domain.favorite.repository.OrganizationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author esuyorkulov
 */
interface OrganizationService {
    fun saveOrUpdate(organization: Organization): Organization
    fun add(request: AddOrganizationRequest): Organization
    fun get(organizationId: Long): Organization
    fun findAll(): List<Organization>
}

@Service
internal class DefaultOrganizationService(
        private val organizationRepository: OrganizationRepository
) : OrganizationService {

    @Transactional
    override fun saveOrUpdate(organization: Organization): Organization {
        return organizationRepository.save(organization)
    }

    @Transactional
    override fun add(request: AddOrganizationRequest): Organization {
        val organization = Organization(
                name = request.name,
                address = request.address
        )

        return organizationRepository.save(organization)
    }

    @Transactional(readOnly = true)
    override fun get(organizationId: Long): Organization {
        return organizationRepository.findById(organizationId)
                .orElseThrow { NotFoundException("Organization with id: $organizationId not found") }
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<Organization> {
        return organizationRepository.findAll()
    }

}