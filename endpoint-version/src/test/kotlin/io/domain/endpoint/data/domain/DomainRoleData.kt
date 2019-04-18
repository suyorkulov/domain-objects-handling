package io.domain.endpoint.data.domain

import io.domain.endpoint.domain.Role

/**
 * @author esuyorkulov
 */
object DomainRoleData {

    fun getRoleData(id: Long? = null,
                    name: String = "ADMIN") = Role(
            name = name
    ).apply { this.id = id }

}