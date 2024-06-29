package jjfactory.reservation.shop.domain.coupon

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class UserShopCoupon(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val couponId: Long,
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