package io.domain.endpoint.data.domain

import io.domain.endpoint.domain.Organization

/**
 * @author esuyorkulov
 */

object DomainOrganizationData {

    fun getOrganizationData(id: Long? = null,
                            name: String = "Organization",
                            address: String = "Address") = Organization(
            name = name,
            address = address
    ).apply { this.id = id }

}