package jjfactory.reservation.book

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class BookCancel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    val book: Book,
    val canceledAt: LocalDateTime = LocalDateTime.now(),
    @Column(columnDefinition = "TEXT")
    val reason: String,
) {
}