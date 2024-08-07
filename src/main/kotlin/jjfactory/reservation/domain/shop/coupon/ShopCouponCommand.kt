package jjfactory.reservation.domain.shop.coupon

import jjfactory.reservation.domain.shop.ShopAddress
import java.time.LocalDateTime

class ShopCouponCommand {
    data class Create(
        val shopId: Long,
        val name: String,
        val type: ShopCoupon.Type,
        val useStartAt: LocalDateTime,
        val useEndAt: LocalDateTime,
        val qty: Int,
        val validSecond: Long
    ){
        fun toEntity(shopId: Long): ShopCoupon {
            return ShopCoupon(
                name = name,
                shopId = shopId,
                type = type,
                useStartAt = useStartAt,
                useEndAt = useEndAt,
                qty = qty,
                validSecond = validSecond
            )
        }
    }

    data class Update(
        val name: String,
        val phone: String,
        val address: ShopAddress
    )
}