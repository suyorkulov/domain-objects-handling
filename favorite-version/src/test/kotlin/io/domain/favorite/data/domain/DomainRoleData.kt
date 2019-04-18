package io.domain.favorite.data.domain

import io.domain.favorite.domain.Role

/**
 * @author esuyorkulov
 */
object DomainRoleData {

    fun getRoleData(id: Long? = null,
                    name: String = "ADMIN") = Role(
            name = name
    ).apply { this.id = id }

}