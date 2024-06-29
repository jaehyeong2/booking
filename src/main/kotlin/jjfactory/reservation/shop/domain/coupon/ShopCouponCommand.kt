package jjfactory.reservation.shop.domain.coupon

import jjfactory.reservation.shop.domain.ShopAddress
import java.time.LocalDateTime

class ShopCouponCommand {
    data class Create(
        val shopId: Long,
        val name: String,
        val type: ShopCoupon.Type,
        val useStartAt: LocalDateTime,
        val useEndAt: LocalDateTime,
    ){
        fun toEntity(shopId: Long): ShopCoupon {
            return ShopCoupon(
                name = name,
                shopId = shopId,
                type = type,
                useStartAt = useStartAt,
                useEndAt = useEndAt
            )
        }
    }

    data class Update(
        val name: String,
        val phone: String,
        val address: ShopAddress
    )
}