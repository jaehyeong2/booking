package jjfactory.reservation.shop.domain.coupon

import jjfactory.reservation.shop.domain.ShopAddress

class ShopCouponCommand {
    data class Create(
        val shopId: Long,
        val name: String,
        val type: ShopCoupon.Type
    ){
        fun toEntity(shopId: Long): ShopCoupon {
            return ShopCoupon(
                name = name,
                shopId = shopId,
                type = type
            )
        }
    }

    data class Update(
        val name: String,
        val phone: String,
        val address: ShopAddress
    )
}