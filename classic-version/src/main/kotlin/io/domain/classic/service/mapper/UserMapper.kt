package io.domain.classic.service.mapper

import io.domain.classic.domain.User
import io.domain.classic.domain.UserStatus
import io.domain.classic.dto.AddUserRequestDto
import io.domain.classic.dto.UserDto
import io.domain.classic.service.OrganizationService
import io.domain.classic.service.RoleService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * @author esuyorkulov
 */
interface UserMapper {
    fun toUser(request: AddUserRequestDto): User
    fun toUserDto(user: User): UserDto
}

@Service
internal class DefaultUserMapper(
        private val roleService: RoleService,
        private val organizationService: OrganizationService,
        private val roleMapper: RoleMapper,
        private val organizationMapper: OrganizationMapper
) : UserMapper {

    @Transactional(readOnly = true)
    override fun toUser(request: AddUserRequestDto): User = User(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            password = UUID.randomUUID().toString(),
            phoneNumber = request.phoneNumber,
            role = roleService.getRole(request.roleId),
            organization = organizationService.get(request.organizationId),
            status = UserStatus.PENDING
    )

    override fun toUserDto(user: User): UserDto = UserDto(
            id = user.id!!,
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email,
            phoneNumber = user.phoneNumber,
            role = roleMapper.toRoleDto(user.role),
            organization = organizationMapper.toOrganizationDto(user.organization)
    )

}