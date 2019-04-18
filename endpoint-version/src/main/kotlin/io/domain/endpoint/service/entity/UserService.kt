package io.domain.endpoint.service.entity

import io.domain.endpoint.domain.User
import io.domain.endpoint.exceptions.NotFoundException
import io.domain.endpoint.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author esuyorkulov
 */
interface UserService {
    fun saveOrUpdate(user: User): User // Bad idea
    fun get(userId: Long): User
}

@Service
internal class DefaultUserService(
        private val userRepository: UserRepository
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

}