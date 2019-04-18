package io.domain.favorite.service.endpoint

import io.domain.favorite.domain.User
import io.domain.favorite.domain.UserStatus
import io.domain.favorite.domain.request.AddUserRequest
import io.domain.favorite.domain.request.UpdateUserRequest
import io.domain.favorite.dto.AddUserRequestDto
import io.domain.favorite.dto.UpdateUserRequestDto
import io.domain.favorite.dto.UserDto
import io.domain.favorite.service.entity.UserService
import io.domain.favorite.service.mapper.UserMapper
import io.domain.favorite.service.notification.UserNotificationService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
        private val userNotificationService: UserNotificationService,
        private val userMapper: UserMapper
) : UserEndpoint {

    @Transactional
    override fun add(request: AddUserRequestDto): UserDto {
        val addUserRequest: AddUserRequest = userMapper.toAddUserRequest(request)

        val createdUser: User = userService.add(addUserRequest)

        userNotificationService.sendInvite(createdUser)

        return userMapper.toUserDto(createdUser)
    }

    @Transactional
    override fun update(userId: Long, request: UpdateUserRequestDto): UserDto {
        val targetUser: User = userService.get(userId)
        val updateUserRequest: UpdateUserRequest = userMapper.toUpdateUserRequest(request)

        val updatedUser: User = userService.update(targetUser, updateUserRequest)

        return userMapper.toUserDto(updatedUser)
    }

    @Transactional
    override fun activate(userId: Long): UserDto {
        val targetUser: User = userService.get(userId)
                .takeIf { it.status != UserStatus.ACTIVE }
                ?: throw IllegalStateException("User is already active")

        val activatedUser: User = userService.activate(targetUser)

        return userMapper.toUserDto(activatedUser)
    }

    @Transactional
    override fun deactivate(userId: Long): UserDto {
        val targetUser: User = userService.get(userId)
                .takeIf { it.status != UserStatus.DEACTIVATED }
                ?: throw java.lang.IllegalStateException("User already deactivated")

        val deactivatedUser: User = userService.deactivate(targetUser)

        return userMapper.toUserDto(deactivatedUser)
    }

    @Transactional
    override fun get(userId: Long): UserDto {
        val user: User = userService.get(userId)

        return userMapper.toUserDto(user)
    }

}