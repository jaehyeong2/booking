package jjfactory.reservation.book.domain

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
    var status: BookStatus = BookStatus.NORMAL,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null
) {

    fun cancel(){
        status = BookStatus.CANCELED
    }
}