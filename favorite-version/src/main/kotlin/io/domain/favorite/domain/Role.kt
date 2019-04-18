package io.domain.favorite.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * @author esuyorkulov
 */

@Entity
@Table(name = "roles")
class Role(

        @Column(name = "name", nullable = false, updatable = false)
        val name: String
) : BaseEntity()