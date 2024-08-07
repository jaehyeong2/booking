package jjfactory.reservation.domain.shop.holiday

import jakarta.persistence.*
import jjfactory.reservation.domain.shop.Shop
import java.time.LocalDate

@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["shopId", "date"])
    ]
)
@Entity
class Holiday(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val shop: Shop,
    val date: LocalDate
) {
}