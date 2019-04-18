package io.domain.favorite.service.mapper

import io.domain.favorite.domain.User
import io.domain.favorite.domain.request.AddUserRequest
import io.domain.favorite.domain.request.UpdateUserRequest
import io.domain.favorite.dto.AddUserRequestDto
import io.domain.favorite.dto.UpdateUserRequestDto
import io.domain.favorite.dto.UserDto
import io.domain.favorite.service.entity.OrganizationService
import io.domain.favorite.service.entity.RoleService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author esuyorkulov
 */
interface UserMapper {
    fun toUserDto(user: User): UserDto
    fun toAddUserRequest(request: AddUserRequestDto): AddUserRequest
    fun toUpdateUserRequest(request: UpdateUserRequestDto): UpdateUserRequest
}

@Service
internal class DefaultUserMapper(
        private val roleService: RoleService,
        private val organizationService: OrganizationService,
        private val roleMapper: RoleMapper,
        private val organizationMapper: OrganizationMapper
) : UserMapper {

    @Transactional(readOnly = true)
    override fun toAddUserRequest(request: AddUserRequestDto): AddUserRequest = AddUserRequest(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            phoneNumber = request.phoneNumber,
            organization = organizationService.get(request.organizationId),
            role = roleService.getRole(request.roleId)
    )

    @Transactional(readOnly = true)
    override fun toUpdateUserRequest(request: UpdateUserRequestDto): UpdateUserRequest = UpdateUserRequest(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            phoneNumber = request.phoneNumber,
            organization = organizationService.get(request.organizationId),
            role = roleService.getRole(request.roleId)
    )

    override fun toUserDto(user: User): UserDto = UserDto(
            id = user.id!!,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            phoneNumber = user.phoneNumber,
            status = user.status,
            role = roleMapper.toRoleDto(user.role),
            organization = organizationMapper.toOrganizationDto(user.organization)
    )

}