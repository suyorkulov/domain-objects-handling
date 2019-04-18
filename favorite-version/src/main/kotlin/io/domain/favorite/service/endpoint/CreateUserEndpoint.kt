package io.domain.favorite.service.endpoint

import io.domain.favorite.domain.User
import io.domain.favorite.dto.AddUserRequestDto
import io.domain.favorite.dto.UserDto
import io.domain.favorite.service.entity.CreateUserService
import io.domain.favorite.service.mapper.UserMapper
import io.domain.favorite.service.notification.UserNotificationService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author esuyorkulov
 */
interface CreateUserEndpoint {
    fun create(request: AddUserRequestDto): UserDto
}

@Service
internal class DefaultCreateUserEndpoint(
        private val createUserService: CreateUserService,
        private val userNotificationService: UserNotificationService,
        private val userMapper: UserMapper
) : CreateUserEndpoint {

    @Transactional
    override fun create(request: AddUserRequestDto): UserDto {
        val createdUser: User = createUserService.create(request)

        userNotificationService.sendInvite(createdUser)

        return userMapper.toUserDto(createdUser)
    }

}