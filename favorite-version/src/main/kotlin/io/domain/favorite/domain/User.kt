package io.domain.favorite.domain

import javax.persistence.*

/**
 * @author esuyorkulov
 */

@Entity
@Table(name = "users")
class User(

        @Column(name = "first_name", nullable = false)
        var firstName: String,

        @Column(name = "last_name", nullable = false)
        var lastName: String,

        @Column(name = "email", nullable = false)
        var email: String,

        @Column(name = "password", nullable = false)
        var password: String,

        @Enumerated(value = EnumType.STRING)
        @Column(name = "status", nullable = false)
        var status: UserStatus,

        @Column(name = "phone_number")
        var phoneNumber: String?,

        @ManyToOne
        @JoinColumn(name = "role_id", nullable = false)
        var role: Role,

        @ManyToOne
        @JoinColumn(name = "organization_id", nullable = false)
        var organization: Organization
) : BaseEntity()


enum class UserStatus {
    PENDING,
    ACTIVE,
    DEACTIVATED
}