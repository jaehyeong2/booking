package jjfactory.reservation.domain.shop.item

import jakarta.persistence.*
import jjfactory.reservation.domain.shop.Shop
import java.time.LocalDate

@Entity
class ShopItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val shop: Shop,
    val name: String,
    @Column(columnDefinition = "TEXT")
    var description: String
) {

    fun validateBookable(date: LocalDate){
        if (isShopHoliday(date)) throw IllegalArgumentException("휴일에는 예약할 수 없습니다")
    }

    private fun isShopHoliday(date: LocalDate): Boolean {
        return shop.isHoliday(date)
    }
}