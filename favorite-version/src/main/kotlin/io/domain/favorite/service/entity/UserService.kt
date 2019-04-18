package io.domain.favorite.service.entity

import io.domain.favorite.domain.User
import io.domain.favorite.domain.UserStatus
import io.domain.favorite.domain.request.AddUserRequest
import io.domain.favorite.domain.request.UpdateUserRequest
import io.domain.favorite.exceptions.NotFoundException
import io.domain.favorite.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * @author esuyorkulov
 */
interface UserService {
    fun saveOrUpdate(user: User): User // Bad idea
    fun get(userId: Long): User
    fun add(request: AddUserRequest): User
    fun update(user: User, request: UpdateUserRequest): User
    fun activate(user: User): User
    fun deactivate(user: User): User
}

@Service
internal class DefaultUserService(
        private val userRepository: UserRepository,
        private val uuidGenerator: () -> UUID = { UUID.randomUUID() }
) : UserService {

    @Transactional
    override fun saveOrUpdate(user: User): User { //Bad idea
        return userRepository.save(user)
    }

    @Transactional
    override fun get(userId: Long): User {
        return userRepository.findById(userId)
                .orElseThrow { NotFoundException("User with given id: $userId not found") }
    }

    @Transactional
    override fun add(request: AddUserRequest): User {
        val user = User(
                firstName = request.firstName,
                lastName = request.lastName,
                role = request.role,
                email = request.email,
                phoneNumber = request.phoneNumber,
                organization = request.organization,
                status = UserStatus.PENDING,
                password = uuidGenerator.invoke().toString()
        )

        return userRepository.save(user)
    }

    @Transactional
    override fun update(user: User, request: UpdateUserRequest): User {
        assert(user.isPersisted)

        val updatedUser: User = user.apply {
            this.firstName = request.firstName
            this.lastName = request.lastName
            this.email = request.email
            this.phoneNumber = request.phoneNumber
            this.organization = request.organization
            this.role = request.role
        }

        return userRepository.save(updatedUser)
    }

    @Transactional
    override fun activate(user: User): User {
        user.status = UserStatus.ACTIVE

        return userRepository.save(user)
    }

    @Transactional
    override fun deactivate(user: User): User {
        user.status = UserStatus.DEACTIVATED

        return userRepository.save(user)
    }

}