package jjfactory.reservation.domain.shop.coupon

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class UserShopCoupon(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    val coupon: ShopCoupon,
    val userId: Long,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    val expiredAt: LocalDateTime,

    var isUsed: Boolean = false,
    var usedAt: LocalDateTime? = null
) {
    private fun expiredCheck(){
        if (LocalDateTime.now().isAfter(expiredAt)) throw ExpiredCouponException()
    }

    private fun usedCheck(){
        if (isUsed) throw AlreadyUsedCouponException()
    }

    fun use(){
        expiredCheck()
        usedCheck()

        isUsed = true
        usedAt = LocalDateTime.now()
    }
}