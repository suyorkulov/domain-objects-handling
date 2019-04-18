package io.domain.classic.service

import io.domain.classic.domain.User
import io.domain.classic.dto.UpdateUserRequestDto
import io.domain.classic.exceptions.NotFoundException
import io.domain.classic.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author esuyorkulov
 */
interface UserService {
    fun add(user: User): User
    fun update(userId: Long, request: UpdateUserRequestDto): User
    fun saveOrUpdate(user: User): User
    fun get(userId: Long): User
}

@Service
internal class DefaultUserService(
        private val userRepository: UserRepository,
        private val roleService: RoleService,
        private val organizationService: OrganizationService,
        private val userNotificationService: UserNotificationService
) : UserService {

    @Transactional
    override fun add(user: User): User {
        val savedUser: User = userRepository.save(user)

        userNotificationService.sendInvite(savedUser)

        return savedUser
    }

    @Transactional
    override fun update(userId: Long, request: UpdateUserRequestDto): User {
        val targetUser: User = get(userId)

        val updatedUser: User = targetUser.apply {
            this.firstName = request.firstName
            this.lastName = request.lastName
            this.email = request.email
            this.phoneNumber = request.phoneNumber
            this.role = roleService.getRole(request.roleId)
            this.organization = organizationService.get(request.organizationId)
        }

        return userRepository.save(updatedUser)
    }

    @Transactional
    override fun saveOrUpdate(user: User): User { //Bad idea
        return userRepository.save(user)
    }

    @Transactional
    override fun get(userId: Long): User {
        return userRepository.findById(userId)
                .orElseThrow { NotFoundException("User with given id: $userId not found") }
    }

}