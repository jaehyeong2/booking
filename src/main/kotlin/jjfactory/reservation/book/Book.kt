package jjfactory.reservation.book

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
class Book(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val userId: Long,
    val itemId: Long,

    val bookStartAt: LocalDateTime,
    val bookEndAt: LocalDateTime,

    @Enumerated(EnumType.STRING)
    val status: BookStatus = BookStatus.NORMAL,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null
) {
}