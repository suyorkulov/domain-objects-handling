package io.domain.classic.service

import io.domain.classic.domain.User
import org.springframework.stereotype.Service

/**
 * @author esuyorkulov
 */
interface UserNotificationService {
    fun sendInvite(user: User)
}

@Service
internal class DefaultUserNotificationService : UserNotificationService {

    override fun sendInvite(user: User) {
        println("Sending invite to: ${user.email}...")
    }

}