package io.domain.favorite.data.domain

import io.domain.favorite.domain.Organization
import io.domain.favorite.domain.Role
import io.domain.favorite.domain.User
import io.domain.favorite.domain.UserStatus
import io.domain.favorite.domain.request.AddUserRequest
import io.domain.favorite.domain.request.UpdateUserRequest

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
    ).apply { this.id = id }

    fun getAddUserRequest(firstName: String = "First",
                          lastName: String = "Last",
                          email: String = "email@email.com",
                          phoneNumber: String? = null,
                          role: Role = DomainRoleData.getRoleData(),
                          organization: Organization = DomainOrganizationData.getOrganizationData()) = AddUserRequest(
            firstName = firstName,
            lastName = lastName,
            email = email,
            phoneNumber = phoneNumber,
            role = role,
            organization = organization
    )

    fun getUpdateUserRequest(firstName: String = "First",
                             lastName: String = "Last",
                             email: String = "email@email.com",
                             phoneNumber: String? = null,
                             role: Role = DomainRoleData.getRoleData(),
                             organization: Organization = DomainOrganizationData.getOrganizationData()) = UpdateUserRequest(
            firstName = firstName,
            lastName = lastName,
            email = email,
            phoneNumber = phoneNumber,
            role = role,
            organization = organization
    )

}