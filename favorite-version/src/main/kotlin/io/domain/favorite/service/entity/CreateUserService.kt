package io.domain.favorite.service.entity

import io.domain.favorite.domain.Organization
import io.domain.favorite.domain.Role
import io.domain.favorite.domain.User
import io.domain.favorite.domain.request.AddUserRequest
import io.domain.favorite.dto.AddUserRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author esuyorkulov
 */
interface CreateUserService {
    fun create(request: AddUserRequestDto): User
}

@Service
internal class DefaultCreateUserService(
        private val userService: UserService,
        private val roleService: RoleService,
        private val organizationService: OrganizationService
) : CreateUserService {

    @Transactional
    override fun create(request: AddUserRequestDto): User {
        val role: Role = roleService.getRole(request.roleId)
        val organization: Organization = organizationService.get(request.organizationId)

        val addRequest = AddUserRequest(
                firstName = request.firstName,
                lastName = request.lastName,
                email = request.email,
                phoneNumber = request.phoneNumber,
                role = role,
                organization = organization
        )

        return userService.add(addRequest)
    }

}