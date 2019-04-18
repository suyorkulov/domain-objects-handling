package io.domain.classic.service

import io.domain.classic.domain.Organization
import io.domain.classic.exceptions.NotFoundException
import io.domain.classic.repository.OrganizationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author esuyorkulov
 */
interface OrganizationService {
    fun add(organization: Organization): Organization
    fun get(organizationId: Long): Organization
    fun findAll(): List<Organization>
}

@Service
internal class DefaultOrganizationService(
        private val organizationRepository: OrganizationRepository
) : OrganizationService {

    @Transactional
    override fun add(organization: Organization): Organization {
        return organizationRepository.save(organization)
    }

//    @Transactional
//    override fun add(request: AddOrganizationRequestDto): Organization {
//        val organization = Organization(
//                name = request.name,
//                address = request.address
//        )
//
//        return organizationRepository.save(organization)
//    }

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