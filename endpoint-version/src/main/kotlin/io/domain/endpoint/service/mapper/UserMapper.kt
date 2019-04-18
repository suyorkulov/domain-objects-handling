package io.domain.endpoint.service.mapper

import io.domain.endpoint.domain.User
import io.domain.endpoint.domain.UserStatus
import io.domain.endpoint.dto.AddUserRequestDto
import io.domain.endpoint.dto.UserDto
import io.domain.endpoint.service.entity.OrganizationService
import io.domain.endpoint.service.entity.RoleService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * @author esuyorkulov
 */
interface UserMapper {
    fun toUserDto(user: User): UserDto
    fun toUser(request: AddUserRequestDto): User // NE OCHEN HOROWAYA IDEYA OTDAVAT MAPPERU SOZDANIE DOMAIN SUWNOSTI
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
            status = user.status,
            role = roleMapper.toRoleDto(user.role),
            organization = organizationMapper.toOrganizationDto(user.organization)
    )

}