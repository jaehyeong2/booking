package jjfactory.reservation.domain.user

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class UserActionLog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: Long,
    @Enumerated(EnumType.STRING)
    val type: Type,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
) {
    enum class Type {
        WITHDRAW
    }
}