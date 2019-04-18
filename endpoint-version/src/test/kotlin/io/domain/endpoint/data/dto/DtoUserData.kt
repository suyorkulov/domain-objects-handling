package io.domain.endpoint.data.dto

import io.domain.endpoint.domain.UserStatus
import io.domain.endpoint.dto.*

/**
 * @author esuyorkulov
 */
object DtoUserData {

    fun getAddUserRequestDto(firstName: String = "First",
                             lastName: String = "Last",
                             email: String = "email@email.com",
                             phoneNumber: String? = null,
                             roleId: Long = 1L,
                             organizationId: Long = 1L) = AddUserRequestDto(
            firstName = firstName,
            lastName = lastName,
            email = email,
            phoneNumber = phoneNumber,
            roleId = roleId,
            organizationId = organizationId
    )

    fun getUpdateUserRequestDto(firstName: String = "First",
                                lastName: String = "Last",
                                email: String = "email@email.com",
                                phoneNumber: String? = null,
                                roleId: Long = 1L,
                                organizationId: Long = 1L) = UpdateUserRequestDto(
            firstName = firstName,
            lastName = lastName,
            email = email,
            phoneNumber = phoneNumber,
            roleId = roleId,
            organizationId = organizationId
    )

    fun getUserDto(id: Long = 1,
                   firstName: String = "First",
                   lastName: String = "Last",
                   email: String = "email@email.com",
                   status: UserStatus = UserStatus.ACTIVE,
                   phoneNumber: String? = null,
                   role: RoleDto = DtoRoleData.getRoleDto(),
                   organization: OrganizationDto = DtoOrganizationData.getOrganizationDto()) = UserDto(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            phoneNumber = phoneNumber,
            role = role,
            organization = organization,
            status = status
    )

}