package io.domain.endpoint.service.notification

import io.domain.endpoint.domain.User
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