package io.domain.classic.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author esuyorkulov
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @CreatedDate
    @Column(name = "created_timestamp", updatable = false)
    var createdTimestamp: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "modified_timestamp")
    var modifiedTimestamp: LocalDateTime? = null

    val isPersisted: Boolean get() = id != null
}
