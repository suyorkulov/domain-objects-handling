package io.domain.favorite.data.domain

import io.domain.favorite.domain.Organization

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