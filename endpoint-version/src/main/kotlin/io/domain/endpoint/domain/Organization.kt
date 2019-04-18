package io.domain.endpoint.domain

import javax.persistence.Column
import javax.persistence.Entity

/**
 * @author esuyorkulov
 */

@Entity(name = "organizations")
class Organization(

        @Column(name = "name", nullable = false)
        var name: String,

        @Column(name = "address", nullable = false)
        var address: String
) : BaseEntity()