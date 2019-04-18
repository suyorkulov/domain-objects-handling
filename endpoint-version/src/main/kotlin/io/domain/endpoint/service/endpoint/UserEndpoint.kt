package io.domain.endpoint.service.endpoint

import io.domain.endpoint.domain.User
import io.domain.endpoint.domain.UserStatus
import io.domain.endpoint.dto.AddUserRequestDto
import io.domain.endpoint.dto.UpdateUserRequestDto
import io.domain.endpoint.dto.UserDto
import io.domain.endpoint.service.entity.OrganizationService
import io.domain.endpoint.service.entity.RoleService
import io.domain.endpoint.service.entity.UserService
import io.domain.endpoint.service.mapper.UserMapper
import io.domain.endpoint.service.notification.UserNotificationService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * @author esuyorkulov
 */

interface UserEndpoint {
    fun add(request: AddUserRequestDto): UserDto
    fun update(userId: Long, request: UpdateUserRequestDto): UserDto
    fun activate(userId: Long): UserDto
    fun deactivate(userId: Long): UserDto
    fun get(userId: Long): UserDto
}

@Service
internal class DefaultUserEndpoint(
        private val userService: UserService,
        private val roleService: RoleService,
        private val organizationService: OrganizationService,
        private val userNotificationService: UserNotificationService,
        private val userMapper: UserMapper
) : UserEndpoint {

    @Transactional
    override fun add(request: AddUserRequestDto): UserDto {
        val user = User(
                firstName = request.firstName,
                lastName = request.lastName,
                email = request.email,
                password = UUID.randomUUID().toString(),
                status = UserStatus.PENDING,
                phoneNumber = request.phoneNumber,
                role = roleService.getRole(request.roleId),
                organization = organizationService.get(request.organizationId)
        )

        val createdUser: User = userService.saveOrUpdate(user)

        userNotificationService.sendInvite(createdUser)

        return userMapper.toUserDto(createdUser)
    }


    // POLUCHWE BUDET UJE
//    @Transactional
//    override fun add(request: AddUserRequestDto): UserDto {
//        val user: User = userMapper.toUser(request)
//
//        val createdUser: User = userService.saveOrUpdate(user)
//
//        userNotificationService.sendInvite(createdUser)
//
//        return userMapper.toUserDto(createdUser)
//    }

    @Transactional
    override fun update(userId: Long, request: UpdateUserRequestDto): UserDto {
        val user: User = userService.get(userId)

        val updatedUser: User = user.apply {
            this.firstName = request.firstName
            this.lastName = request.lastName
            this.email = request.email
            this.phoneNumber = request.phoneNumber
            this.role = roleService.getRole(request.roleId)
            this.organization = organizationService.get(request.organizationId)

            userService.saveOrUpdate(this)
        }

        return userMapper.toUserDto(updatedUser)
    }

    @Transactional
    override fun activate(userId: Long): UserDto {
        val targetUser: User = userService.get(userId)

        val deactivatedUser: User = targetUser.apply {
            this.status = UserStatus.ACTIVE

            userService.saveOrUpdate(this)
        }

        return userMapper.toUserDto(deactivatedUser)
    }

    @Transactional
    override fun deactivate(userId: Long): UserDto {
        val targetUser: User = userService.get(userId)
        println(targetUser.status.name)
        if (targetUser.status == UserStatus.ACTIVE) {
            throw IllegalStateException("User already active")
        }

        val deactivatedUser: User = targetUser.apply {
            this.status = UserStatus.DEACTIVATED

            userService.saveOrUpdate(this)
        }

        return userMapper.toUserDto(deactivatedUser)
    }

    @Transactional
    override fun get(userId: Long): UserDto {
        val user: User = userService.get(userId)

        return userMapper.toUserDto(user)
    }

}