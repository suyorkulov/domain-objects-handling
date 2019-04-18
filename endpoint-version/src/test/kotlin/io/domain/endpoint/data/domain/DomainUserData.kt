package io.domain.endpoint.data.domain

import io.domain.endpoint.domain.Organization
import io.domain.endpoint.domain.Role
import io.domain.endpoint.domain.User
import io.domain.endpoint.domain.UserStatus

/**
 * @author esuyorkulov
 */
object DomainUserData {

    fun getUserData(id: Long? = null,
                    firstName: String = "First",
                    lastName: String = "Last",
                    email: String = "email@email.com",
                    status: UserStatus = UserStatus.ACTIVE,
                    password: String = "password",
                    phoneNumber: String? = null,
                    role: Role = DomainRoleData.getRoleData(),
                    organization: Organization = DomainOrganizationData.getOrganizationData()) = User(
            firstName = firstName,
            lastName = lastName,
            email = email,
            phoneNumber = phoneNumber,
            role = role,
            organization = organization,
            status = status,
            password = password
    ).apply { this.id = 1 }

}